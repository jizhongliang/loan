package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.base.sdk.core.Client;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.common.DateUtil;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.common.ParamUtil;
import com.hwc.framework.modules.dao.ClBorrowMapper;
import com.hwc.framework.modules.dao.ClBorrowRepayLogMapper;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.CLWContactService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClBorrowProgressService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClCreditBorrowService;
import com.hwc.framework.modules.service.PayService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 * 信用借款
 */
@Service
public class ClCreditBorrowServiceImpl extends AbstractService<ClBorrowMapper, ClBorrow>
                                       implements ClCreditBorrowService {
    private static final Logger     logger = LoggerFactory
        .getLogger(ClCreditBorrowServiceImpl.class);

    @Autowired
    private ArcCreditService        arcCreditService;
    @Autowired
    private ClBorrowService         clBorrowService;
    @Autowired
    private ClBorrowRepayService    clBorrowRepayService;
    @Autowired
    private ClBorrowProgressService clBorrowProgressService;
    @Autowired
    private ClBankCardService       bankCardService;
    @Autowired
    private ArcSysConfigService     configService;

    @Autowired
    private PayService              payService;

    @Autowired
    private UserService             userService;
    @Autowired
    private BorrowNotifyService     notifyService;
    @Autowired
    private ClBorrowRepayLogMapper  clBorrowRepayLogMapper;
    @Autowired
    private CLWContactService       cLWContactService;
    @Autowired
    private Client                  client;

    public Response borrow(BorrowBean bean) {
        try {
            boolean b = isCanBorrow(bean);
            if (b) {
                List<ClBorrow> borrows = clBorrowService.getByUserIds(bean.getUserId(), "X");
                ClBorrow borrow = new ClBorrow();
                borrow.setUserId(bean.getUserId());
                borrow.setAddress(bean.getAddress());
                borrow.setClient(bean.getClient());
                borrow.setContractId(bean.getContractId());
                DCloanUserDomain userInfo = userService.getUserInfo(bean.getUserId());
                borrow.setMobile(userInfo.getPhone());
                borrow.setName(userInfo.getRealName());
                borrow.setScenes(bean.getScenes());
                borrow.setAmount(FsUtils.bigdec(bean.getAmount()));
                borrow.setRealAmount(borrow.getAmount());
                borrow.setBorrowType(BorrowTypeConstant.CREDIT);
                borrow.setCoordinate(bean.getCoordinate());
                borrow.setExpireTime(
                    FsUtils.date(FsUtils.formatDate(FsUtils.addMonth(new Date(), bean.getPeriods()))
                                 + " 23:59:59"));
                borrow.setPeriods(bean.getPeriods());
                borrow.setCreateTime(new Date());
                borrow.setIp(bean.getIp());

                //设置费率
                Map<String, String> map = clBorrowService.findRate(bean.getUserId());
                borrow.setRate(new BigDecimal(map.get("rate")));
                borrow.setOrderNo(NidGenerator.getOrderNo());
                borrow.setIsnotify("F");
                borrow.setInfoAuthFee(FsUtils.bigdec(0));
                Double days = FsUtils.getDays(FsUtils.getInstanceOfDay(new Date()),
                    FsUtils.getInstanceOfDay(borrow.getExpireTime()));
                borrow.setTimeLimit(days.intValue() + "");
                Double interest = clBorrowRepayService.calcInterest(
                    borrow.getAmount().doubleValue(), borrow.getPeriods(),
                    borrow.getRate().doubleValue());
                borrow.setInterest(FsUtils.bigdec(interest));

                if (null != borrows && borrows.size() > 0) {
                    BorrowAuditBean beans = new BorrowAuditBean();
                    beans.setState(BorrowStateConstant.STATE_PASS);
                    borrow.setState(BorrowStateConstant.STATE_PASS);
                    this.insert(borrow);
                    beans.setId(clBorrowService.findByUserIdDesc(bean.getUserId()).getId());
                    beans.setRemark("审核成功");
                    arcCreditService.subtractQuota(bean.getUserId(),
                        borrow.getAmount().doubleValue());
                    clBorrowProgressService.borrowProgress(borrow);
                    auditBorrows(beans);
                    return Response.success("你有一笔分期申请审核成功");
                } else {
                    borrow.setState(BorrowStateConstant.STATE_NEED_REVIEW);
                    this.insert(borrow);
                    arcCreditService.subtractQuota(bean.getUserId(),
                        borrow.getAmount().doubleValue());
                    clBorrowProgressService.borrowProgress(borrow);
                    notifyService.applyBorrowNotify(borrow);
                    return Response.success("你有一笔分期申请正在审核中，请保持手机畅通，稍后可能有审核专员联系您");
                }

            }

        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("借款异常", ex);
            return Response.fail("申请分期异常!");
        }
        return Response.fail("申请分期异常!");
    }

    private boolean isCanBorrow(BorrowBean bean) {
        //判断额度是否够
        if (!userService.userIsAuth(bean.getUserId()).isAuth()) {
            throw new ServiceException("认证未完成!");
        }
        Response response = userService.checkPwd(bean.getUserId(), bean.getTradePassword());
        if (!response.getSuccess()) {
            throw new ServiceException("交易密码不正确");
        }
        List<ClBorrow> borrow = clBorrowService.getByUserId(bean.getUserId(), "30", "X");
        if (null != borrow && borrow.size() > 0) {
            for (int i = 0; i < borrow.size(); i++) {
                List<ClBorrowRepay> list = clBorrowRepayService.getList(borrow.get(i).getId());
                if (null != list && list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getPenaltyDay() > 0) {
                            throw new ServiceException("您有逾期未还的借款!");
                        }
                    }
                }
            }

        }
        //黑名单
        DCloanUserDomain userInfo = userService.getUserInfoByUserId(bean.getUserId());
        if (!"20".equals(userInfo.getState())) {
            throw new ServiceException("该账号无法分期,请联系客服人员!");
        }
        if (FsUtils.strsEmpty(userInfo.getIdNo())) {
            throw new ServiceException("认证未完成!");
        }
        ClBankCard card = bankCardService.getBankCard(bean.getUserId());
        if (FsUtils.strsEmpty(card)) {
            throw new ServiceException("认证未完成!");
        }
        boolean bo = arcCreditService.isQuota(bean.getUserId(), bean.getAmount().doubleValue());
        if (!bo) {
            throw new ServiceException("额度不够,无法分期");
        }
        //检查是否是黑名单

        return true;
    }

    /**
     * 信用借款订单审核放款
     *
     * @param bean
     * @param
     * @param
     * @param sysUserId
     */
    public Response auditBorrow(BorrowAuditBean bean, Long sysUserId) {
        //        if (!FsUtils.ArrayContains(
        //            new String[] { BorrowStateConstant.STATE_PASS, BorrowStateConstant.STATE_AUTO_REFUSED },
        //            bean.getState())) {
        //            return Response.fail("状态不正确不能审核");
        //        }
        ClBorrow borrow = this.mapper.selectByPrimaryKey(bean.getId());
        //若用户在黑名单，则提示该用户为黑名单用户不可通过审核
        if (bean.getState().equals(BorrowStateConstant.STATE_PASS)) {
            DCloanUserDomain userInfo = userService.getUserInfoByUserId(borrow.getUserId());
            if (!"20".equals(userInfo.getState())) {
                return Response.fail("该用户为黑名单用户不可通过审核!");
            }
        }
        if (FsUtils.strsNotEmpty(borrow)) {
            if (borrow.getState().equals(BorrowStateConstant.STATE_NEED_REVIEW)
                || borrow.getState().equals(BorrowStateConstant.STATE_REPAY_FAIL)) {
                ClBorrow borrow2 = new ClBorrow();
                borrow2.setState(bean.getState());
                borrow2.setExpireTime(
                    FsUtils.getInstanceOfDay(FsUtils.addMonth(new Date(), borrow.getPeriods())));
                borrow2.setRemark(bean.getRemark());
                borrow2.setId(bean.getId());

                if (bean.getState().equals(BorrowStateConstant.STATE_PASS)) {
                    notifyService.borrowPassNotify(borrow);
                    //宝付直接放款加判断，读取全局配置，是否可以宝付放款
                    boolean result = isBaoFuCanLoan();
                    if (result) {
                        //放款
                        Response<PayRespBean> response = payService.loan(borrow);
                        if (!response.getSuccess()) {
                            borrow2.setState(BorrowStateConstant.STATE_REPAY_FAIL);
                        }
                    }
                } else {
                    //审核拒绝
                    //释放额度
                    arcCreditService.increaseQuota(borrow.getUserId(),
                        borrow.getAmount().doubleValue());
                    notifyService.refuseBorrowNotify(borrow);
                }
                this.update(borrow2);
                //todo 异步通知放款
                return Response.success("审核成功");
            } else {
                return Response.fail("订单状态不正确，不能审核!");
            }
        } else {
            return Response.fail("未找到订单信息，请检查!");
        }

    }

    /**
     * 信用借款订单审核放款
     *
     * @param bean
     * @param
     * @param
     */
    public Response auditBorrows(BorrowAuditBean bean) {
        //        if (!FsUtils.ArrayContains(
        //            new String[] { BorrowStateConstant.STATE_PASS, BorrowStateConstant.STATE_AUTO_REFUSED },
        //            bean.getState())) {
        //            return Response.fail("状态不正确不能审核");
        //        }
        ClBorrow borrow = this.mapper.selectByPrimaryKey(bean.getId());
        if (FsUtils.strsNotEmpty(borrow)) {
            ClBorrow borrow2 = new ClBorrow();
            borrow2.setState(bean.getState());
            borrow2.setExpireTime(
                FsUtils.getInstanceOfDay(FsUtils.addMonth(new Date(), borrow.getPeriods())));
            borrow2.setRemark(bean.getRemark());
            borrow2.setId(bean.getId());

            if (bean.getState().equals(BorrowStateConstant.STATE_PASS)) {
                //宝付直接放款加判断，读取全局配置，是否可以宝付放款
                boolean result = isBaoFuCanLoan();
                if (result) {
                    //放款
                    Response<PayRespBean> response = payService.loan(borrow);
                    if (!response.getSuccess()) {
                        borrow2.setState(BorrowStateConstant.STATE_REPAY_FAIL);
                    }
                }
            } else {
                //审核拒绝
                //释放额度
                arcCreditService.increaseQuota(borrow.getUserId(),
                    borrow.getAmount().doubleValue());
                notifyService.refuseBorrowNotify(borrow);
            }
            this.update(borrow2);
            //todo 异步通知放款
            return Response.success("审核成功");

        } else {
            return Response.fail("未找到订单信息，请检查!");
        }

    }

    /**
     * 宝付能否直接放款的判断
     * @return
     */
    private boolean isBaoFuCanLoan() {
        ArcSysConfig config = configService.getSysConfigByCode("pay_channel");
        if (ParamUtil.isEmpty(config)) {
            return false;
        }

        //10:baofu 20:xinhuajindian
        if ("10".equals(config.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    private ClBorrow getBorrowLately(Long userId) {
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        List<String> list = new ArrayList<>();
        list.add(BorrowStateConstant.STATE_AUTO_REFUSED);
        list.add(BorrowStateConstant.STATE_NEED_REVIEW);
        list.add(BorrowStateConstant.STATE_REPAY);
        list.add(BorrowStateConstant.STATE_TRIAL_REFUSED);
        list.add(BorrowStateConstant.STATE_REFUSED);
        list.add(BorrowStateConstant.STATE_AUTO_REFUSED);
        list.add(BorrowStateConstant.STATE_FINISH);
        map.put("stateList", list);
        return mapper.findCreditByUserIdAndState(map);

    }

    public BorrowBean getIndex(Long userId) {
        BorrowBean bean = new BorrowBean();
        ArcCredit credit = arcCreditService.getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            credit = new ArcCredit();
            credit.setUnuse(FsUtils.bigdec(0));
            credit.setTotal(credit.getUnuse());
        }
        DUserStateDomian domian = userService.userIsAuth(userId);
        bean.setAuth(domian.isAuth());
        bean.setPwd(domian.isTrade_pwd());
        if (credit.getUnuse().doubleValue() <= 0 && bean.isAuth()) {
            bean.setCanBorrow(false);
        } else {
            bean.setCanBorrow(true);
        }
        if (domian.isAuth()){
            bean.setAmount(credit.getUnuse().doubleValue());
        } else {
            bean.setAmount(0.0);
        }
        bean.setTotal_amount(credit.getTotal().doubleValue());
        String periods = configService.getConfigDefault("credit_default_periods", "5");
        Map<String, String> map = clBorrowService.findRate(userId);
        bean.setPeriods(FsUtils.i(periods));
        bean.setRemark("");
        /**
         * 只要额度够 就可以借款
         */
        ClBankCard card = bankCardService.getBankCard(userId);
        if (FsUtils.strsNotEmpty(card)) {
            bean.setCardno(card.getCardNo());
        }
        bean.setRate(Double.valueOf(map.get("rate")));
        bean.setBorrow_rate_descript(
            "日费率万" + map.get("rates") + "（1000元用1天息费只需" + map.get("ratess") + "元）");

        //bean.setRepay(false);"
        bean.setUserId(userId);
        ClBorrow borrow = getBorrowLately(userId);
        String remark;
        if (FsUtils.strsNotEmpty(borrow)) {
            if (BorrowStateConstant.STATE_NEED_REVIEW.equals(borrow.getState())) {
                bean.setCanBorrow(false);
                remark = String.format("您于%s提交的分期申请正在审核中，请保持手机畅通，稍后可能有" + "审核专员联系您",
                    DateUtil.getAllDate(borrow.getCreateTime()));
                bean.setRemark(remark);
            } else if (BorrowStateConstant.STATE_REPAY.equals(borrow.getState())) {
                remark = String.format("恭喜您于%s提交的分期申请已放款成功，请及时查看您的收款" + "银行卡！",
                    DateUtil.getAllDate(borrow.getCreateTime()));
                try {
                    ClBorrowRepayLog log = clBorrowRepayLogMapper.loadRepayLately(borrow.getId());
                    if (log != null) {
                        remark = String.format("恭喜您于%s成功归还一笔分期！",
                            DateUtil.getAllDate(log.getCreateTime()));
                    }
                } catch (Throwable throwable) {
                    logger.info("查询已还记录出错{}", throwable.getMessage());
                }
                bean.setRemark(remark);
                bean.setOverdue_hint("逾期时按日息0.1%计算");
            } else if (BorrowStateConstant.STATE_TRIAL_REFUSED.equals(borrow.getState())
                       || BorrowStateConstant.STATE_REFUSED.equals(borrow.getState())
                       || BorrowStateConstant.STATE_AUTO_REFUSED.equals(borrow.getState())) {
                remark = String.format("您于%s提交的分期申请因风控原因未通过审核，请完善资料" + "后重新提交申请 ！",
                    DateUtil.getAllDate(borrow.getCreateTime()));
                bean.setRemark(remark);
            }
        }
        //增加一个参数，用来表示该用户成功借过一笔款
        int count = getSuccessBorrowCountByUid(userId);
        if (count > 0) {
            bean.setEverSucceedBorrow("1");
        } else {
            bean.setEverSucceedBorrow("0");
        }

        return bean;

    }

    /**
     * 查询用户成功放款的订单数
     * @param userId
     * @return
     */
    private int getSuccessBorrowCountByUid(Long userId) {
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);

        return mapper.getSuccessBorrowCountByUid(map);
    }

}
