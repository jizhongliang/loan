package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.BaofuUtils;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.common.ClMortgageOrderState;
import com.hwc.framework.common.MortgageImgTypeConstant;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.dao.ClBorrowMapper;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.MortgageIndex;
import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.CLWContactService;
import com.hwc.framework.modules.service.ClBorrowProgressService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClMortgageBorrowService;
import com.hwc.framework.modules.service.ClMortgageImgService;
import com.hwc.framework.modules.service.ClMortgageService;
import com.hwc.framework.modules.service.PayService;
import com.hwc.framework.modules.third.MortgageNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 * 抵押借款
 */
@Service
public class ClMortgageBorrowServiceImpl extends AbstractService<ClBorrowMapper, ClBorrow>
                                         implements ClMortgageBorrowService {
    private static final Logger     logger = LoggerFactory
        .getLogger(ClMortgageBorrowServiceImpl.class);

    @Autowired
    private ArcCreditService        arcCreditService;
    @Autowired
    private ClBorrowProgressService clBorrowProgressService;
    @Autowired
    private ClMortgageService       clMortgageService;
    @Autowired
    private ClBorrowRepayService    clBorrowRepayService;
    @Autowired
    private ArcSysConfigService     sysConfigService;
    @Autowired
    private PayService              payService;
    @Autowired
    private UserService             userService;
    @Autowired
    private MortgageNotifyService   mortgageNotifyService;
    @Autowired
    private ArcBasecodeService      basecodeService;

    @Autowired
    private ClMortgageImgService    clMortgageImgService;
    @Autowired
    private IHwcCache               cache;
    @Autowired
    private ClBorrowService         clBorrowService;
    @Autowired
    private CLWContactService         cLWContactService;

    public Response borrow(BorrowBean bean) {
        BaofuUtils.isCredit.set(false);
        ClMortgage clMortgage = clMortgageService.getMortgage(bean.getUserId());
        boolean b = isCanBorrow(bean, clMortgage);
        if (b) {
            ClBorrow borrow = new ClBorrow();
            borrow.setUserId(bean.getUserId());
            borrow.setAddress(bean.getAddress());
            borrow.setClient(bean.getClient());
            borrow.setAmount(FsUtils.bigdec(bean.getAmount()));
            borrow.setBorrowType(BorrowTypeConstant.MORTGAGE);
            DCloanUserDomain userInfo = userService.getUserInfo(bean.getUserId());
            borrow.setMobile(userInfo.getPhone());
            borrow.setName(userInfo.getRealName());
            borrow.setRealAmount(borrow.getAmount());
            borrow.setScenes(bean.getScenes());
            borrow.setCoordinate(bean.getCoordinate());
            borrow.setExpireTime(FsUtils.addMonth(new Date(), bean.getPeriods()));
            borrow.setCreateTime(new Date());
            borrow.setIp(bean.getIp());
            borrow.setPeriods(bean.getPeriods());
            borrow.setMid(clMortgage.getId());
            borrow.setRate(clMortgage.getRealRate());
            borrow.setState(BorrowStateConstant.STATE_PASS);
            borrow.setOrderNo(NidGenerator.getOrderNo());
            borrow.setIsnotify("F");
            borrow.setContractId(bean.getContractId());
            Double days = FsUtils.getDays(FsUtils.getInstanceOfDay(new Date()),
                FsUtils.getInstanceOfDay(borrow.getExpireTime()));
            borrow.setTimeLimit(days.intValue() + "");
            //计算利息
            Double interest = clBorrowRepayService.calcInterest(borrow.getAmount().doubleValue(),
                borrow.getPeriods(), borrow.getRate().doubleValue());
            borrow.setInterest(FsUtils.bigdec(interest));
            logger.info("车位申请放款,userId=" + borrow.getUserId() + ",data=>"
                        + JSONObject.toJSONString(borrow));
            this.insert(borrow);
            mortgageNotifyService.mortgageWithdrawalsNotify(borrow);

            try {
                String n = "H" + getbaseCode(clMortgage.getDyCity()) + "{num}ZF-1";
                String userKey = "bestMNum:users";
                if (cache.hexists(userKey, borrow.getUserId() + "")) {
                    String num = cache.hget(userKey, borrow.getUserId() + "") + "";
                    String[] nums = num.split("-");
                    int numx = FsUtils.i(nums[1]) + 1;
                    cache.hset(userKey, borrow.getUserId() + "", nums[0] + "-" + numx);
                } else {
                    String key = "bestMNum" + FsUtils.formatDateTime(new Date(), "yyyyMM");
                    if (cache.exists(key)) {
                        cache.incr(key);
                    } else {
                        String num = FsUtils.formatDateTime(new Date(), "yyyyMM") + "001";
                        cache.incrby(key, FsUtils.l(num));
                    }
                    String xx = n.replace("{num}", cache.get(key));
                    cache.hset(userKey, borrow.getUserId() + "", xx);
                }
            } catch (Exception ex) {
                logger.error("序号错误", ex);
            }

            arcCreditService.subtractQuota(bean.getUserId(), borrow.getAmount().doubleValue());
            clBorrowProgressService.borrowProgress(borrow);
            //todo 异步 通知 放款
            Response payResponse = payService.loan(borrow);
            logger.info("车位放款,userId=>" + JSONObject.toJSONString(payResponse));
            if (!payResponse.getSuccess()) {
                borrow.setState(BorrowStateConstant.STATE_REPAY_FAIL);
                borrow.setRemark(payResponse.getMessage());
                update(borrow);
                //arcCreditService.increaseQuota(bean.getUserId(), borrow.getAmount().doubleValue());
            }

            return Response.success(borrow);
        }
        return Response.fail("额度不够,无法借款");
    }

    private String getbaseCode(String descript) {
        List<BaseCode> list = basecodeService.getBasecodeList("dy_city");
        for (BaseCode code : list) {
            if (code.getDescript().trim().equalsIgnoreCase(descript.trim())) {
                return code.getCode();
            }
        }
        return "";
    }

    private boolean isCanBorrow(BorrowBean bean, ClMortgage clMortgage) {
        //判断额度是否够
        boolean bo = arcCreditService.isQuota(bean.getUserId(), bean.getAmount().doubleValue());
        if (!bo) {
            throw new ServiceException("额度不够,无法借款");
        }
        Response response = userService.checkPwd(bean.getUserId(), bean.getTradePassword());
        if (!response.getSuccess()) {
            throw new ServiceException("交易密码不正确");
        }
        //黑名单
        DCloanUserDomain userInfo = userService.getUserInfoByUserId(bean.getUserId());
        if (!"20".equals(userInfo.getState())) {
            throw new ServiceException("很抱歉，您暂不可申请，请联系客服!");
        }
        //判断抵押物是否在有效期内
        Date maxDate = FsUtils.addMonth(new Date(), bean.getPeriods());
        if (FsUtils.strsEmpty(clMortgage)) {
            throw new ServiceException("未找到抵押申请");
        }
        if (clMortgage.getState().equals(ClMortgageOrderState.STATE_DRAWING)
            || clMortgage.getState().equals(ClMortgageOrderState.STATE_PASS)) {
            int day = FsUtils.compare_Day(clMortgage.getExpireDate(), maxDate);
            //有效期 不够
            if (day < 0) {
                throw new ServiceException("借款有效期超长");
            }
        } else {
            //抵押物状态不正确
            throw new ServiceException("抵押申请未通过!");
        }
        return true;
    }

    public Response<MortgageIndex> homePage(Long userId) {
        Long uid = userId;//// FsUtils.l(RequestUtils.getUserId());
        //关联白名单
        clMortgageService.handleWhiteListForMortgage(uid);
        ClMortgage clMortgage = clMortgageService.getMortgageHome(uid);
        MortgageIndex index = new MortgageIndex();
        index.setAlt_message("");
        DUserStateDomian domian = userService.userIsAuth(uid);
        index.setAuth(domian.isAuth());
        index.setPwd(domian.isTrade_pwd());
        if (FsUtils.strsNotEmpty(clMortgage)) {
            index.setMid(clMortgage.getId());
            //有抵押物 10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
            if (ClMortgageOrderState.STATE_PRE.equals(clMortgage.getState())
                || ClMortgageOrderState.STATE_TRIAL.equals(clMortgage.getState())) {
                List<String> images = clMortgageImgService.getImg(MortgageImgTypeConstant.ASSETS,
                    clMortgage.getId());
                if (FsUtils.strsEmpty(images) || images.isEmpty()) {
                    index.setPage("apply");
                    index.setState("1");
                    index.setApply(false);
                } else {
                    index.setPage("apply");
                    index.setState("0");
                    index.setApply(true);
                    index.setAlt_message("你的申请我们正在审核中，请耐心等待，稍后会有审核专员联系你！");
                }
                if (ClMortgageOrderState.STATE_TRIAL.equals(clMortgage.getState())) {
                    index.setAlt_message("您申请的车位分期申请已经通过初审，我们会安排专员上门审核，请耐心等待！");
                }
                return Response.success(index);
            } else {
                if (ClMortgageOrderState.STATE_V_PASS.equals(clMortgage.getState())) {
                    index.setPage("apply");
                    index.setState("0");
                    index.setApply(true);
                    index.setAlt_message("您申请的车位分期申请已经通过复审，我们会安排专员与您联系，请耐心等待！");
                    index.setTotal_quota(clMortgage.getRealQuota().doubleValue());
                    index.setQuota(clMortgage.getRealQuota().doubleValue());
                    Map<String,String> map=clBorrowService.findRate(userId);
                    logger.info("map1---------------------------->rates=="+map.get("rates"));
                    logger.info("map1---------------------------->rates=="+map.get("ratess"));
                    index.setRate_descript("日费率万"+map.get("rates")+"（1000元用1天息费只需" +map.get("ratess") + "元）");
                    return Response.success(index);
                } else if (ClMortgageOrderState.STATE_PASS.equals(clMortgage.getState())) {
                    index.setPage("withdrawals");
                    index.setState("1");
                    index.setApply(true);
                    //zq
                    if(clMortgage.getState().equals("40")&&!clMortgage.getRemark().equals("白名单")){
                    	index.setAlt_message("您申请的车位分期已通过终审，可随时申请分期用款！");
                    }
                    String msg = clBorrowService.findBorrowlastTenM(uid);
                    logger.info("homeIndex >>> msg"+msg);
                    if (msg != null && msg != "") {
                        index.setAlt_message(msg);
                    }
                    ArcCredit credit = arcCreditService.getCreditByUserid(uid);

                    if (FsUtils.strsNotEmpty(credit)) {
                        index.setQuota(credit.getUnuse().doubleValue());
                        index.setTotal_quota(credit.getTotal().doubleValue());
                    } else {
                        index.setQuota(0D);
                        index.setTotal_quota(0D);
                    }
                    Map<String,String> map=clBorrowService.findRate(userId);
                    logger.info("map---------------------------->rates=="+map.get("rates"));
                    logger.info("map---------------------------->rates=="+map.get("ratess"));
                    index.setRate_descript("日费率万"+map.get("rates")+"（1000元用1天息费只需" +map.get("ratess") + "元）");
                    return Response.success(index);
                } else  if (ClMortgageOrderState.STATE_REFUSED.equals(clMortgage.getState())) {
                        index.setPage("apply");
                        index.setState("1");
                        index.setApply(false);
                        index.setMid(null);
                        index.setAlt_message("您在车位分期申请未通过审核，请完善资料后重新提交申请");
                        return Response.success(index);
                } else {
                    return Response.fail("申请分期失败，请过10天后再借!");
                }
            }

        } else {

            index.setPage("apply");
            index.setState("1");
            index.setAlt_message("");
            return Response.success(index);
        }
    }

    public Response getBanner() {
        String url = sysConfigService.getConfigDefault("home_image", "");
        String jump = sysConfigService.getConfigDefault("home_image_jump", "");
        JSONObject object = new JSONObject();
        object.put("image", url);
        object.put("url", jump);
        return Response.success(object);
    }

    @Override
    public List<Double> getQuotaRang(Long userId) {
        String min = sysConfigService.getConfigDefault("mortgage_min_quota", "5000");
        String step = sysConfigService.getConfigDefault("mortgage_quota_step", "5000");
        return arcCreditService.getQuotaRang(userId, FsUtils.d(min), FsUtils.d(step));
    }

}
