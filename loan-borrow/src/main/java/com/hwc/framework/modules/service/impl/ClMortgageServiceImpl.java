package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsFileUtils;
import cn.freesoft.utils.FsUtils;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.base.sdk.core.Client;
import com.hwc.framework.common.*;
import com.hwc.framework.modules.dao.ClMortgageMapper;


import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.domain.MortgageBean;

import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.framework.modules.service.*;
import com.hwc.framework.modules.third.MortgageNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserAuthDomain;
import com.hwc.loan.sdk.user.domain.DUserBaseInfoDomain;
import com.hwc.loan.sdk.user.domain.DWContactsDomain;
import com.hwc.loan.sdk.user.request.UserBaseInfoByPhoneRequest;
import com.hwc.loan.sdk.user.request.UserBaseInfoGetOneRequest;
import com.hwc.loan.sdk.user.response.UserBaseInfoByPhoneResponse;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.DataObjectConverter;
import com.hwc.mybatis.util.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/10/23.
 */
@Service
public class ClMortgageServiceImpl extends AbstractService<ClMortgageMapper, ClMortgage> implements ClMortgageService {
    private static final Logger logger = LoggerFactory.getLogger(ClMortgageServiceImpl.class);

    @Autowired
    private ClMortgageImgService clMortgageImgService;
    @Autowired
    private ArcCreditService arcCreditService;
    @Autowired
    private ArcSysConfigService sysConfigService;
    @Autowired
    private UserService userService;

    @Autowired
    private MortgageNotifyService notifyService;
    @Autowired
    private CLWContactService clwContactService;
    @Autowired
    private Client   userClient;

    @Autowired
    private ClMortgageMapper clMortgageMapper;
    /**
     * 抵押申请
     *
     * @param bean
     */
    public Response mortgageApply(MortgageBean bean) {
        //检查是否已经申请过 状态 有效的
        //  未申请
        //  已申请
        ClMortgage clMortgage = getMortgage(bean.getUserId());
        if (FsUtils.strsEmpty(clMortgage) || clMortgage.getHalt().equals("T")) {
            if (isCanMortgage(bean)) {
                clMortgage = new ClMortgage();
                clMortgage.setApplyDate(FsUtils.getInstanceOfDay(new Date()));
                clMortgage.setBorrowAmount(FsUtils.bigdec(FsUtils.mulDouble(bean.getBorrowAmount(), 10000D)));
                clMortgage.setCreated(new Date());
                clMortgage.setDyAddress(FsUtils.replaceEmoji(bean.getDyAddress()));
                clMortgage.setDyArea(FsUtils.bigdec(bean.getDyArea()));
                clMortgage.setDyBuyPrice(FsUtils.bigdec(FsUtils.mulDouble(bean.getDyBuyPrice(), 10000D)));
                clMortgage.setDyBuyYear(bean.getDyBuyYear());
                clMortgage.setDyCity(bean.getDyCity());
                clMortgage.setDyCommunity(bean.getDyCommunity());
                clMortgage.setDescript("车库");
                clMortgage.setDyType("10");
                clMortgage.setOrderNo(NidGenerator.getOrderNo());
                clMortgage.setName(bean.getUserName());
                clMortgage.setMobile(bean.getMobile());
                Double rateDefault = userService.getBorrowRateByUserId(bean.getUserId());
                DCloanUserDomain userInfo = userService.getUserInfo(bean.getUserId());
                if (FsUtils.strsNotEmpty(userInfo)) {
                    clMortgage.setName(userInfo.getRealName());
                    clMortgage.setMobile(userInfo.getPhone());
                }
                clMortgage.setMarketValue(FsUtils.bigdec(FsUtils.mulDouble(bean.getNewPrice(), 10000D)));
                clMortgage.setRealQuota(FsUtils.bigdec(0));
                clMortgage.setState(ClMortgageOrderState.STATE_PRE);
                clMortgage.setHalt("F");
                clMortgage.setUserId(bean.getUserId());
                clMortgage.setRealRate(FsUtils.bigdec(rateDefault));
                insert(clMortgage);
                if (FsUtils.strsNotEmpty(bean.getAssetsImg()) && !bean.getAssetsImg().isEmpty())
                    clMortgageImgService.batchUploadImg(bean.getAssetsImg(), bean.getUserId(), clMortgage.getId(), MortgageImgTypeConstant.ASSETS);
                if (FsUtils.strsNotEmpty(bean.getUserImg()) && !bean.getUserImg().isEmpty())
                    clMortgageImgService.batchUploadImg(bean.getUserImg(), bean.getUserId(), clMortgage.getId(), MortgageImgTypeConstant.USERIMG);
                if (FsUtils.strsNotEmpty(bean.getOtherImg()) && !bean.getOtherImg().isEmpty())
                    clMortgageImgService.batchUploadImg(bean.getOtherImg(), bean.getUserId(), clMortgage.getId(), MortgageImgTypeConstant.OTHER);

                notifyService.mortgageApplyNotify(clMortgage);
                return Response.success(clMortgage);
            } else {
                return Response.fail("未通过认证，不能申请");
            }
        } else {
            if (!clMortgage.getState().equals(ClMortgageOrderState.STATE_PRE)) {
                return Response.fail("你的申请已经在处理中，请勿重复申请");
            }


            clMortgage.setBorrowAmount(FsUtils.bigdec(FsUtils.mulDouble(bean.getBorrowAmount(), 10000D)));
            clMortgage.setMarketValue(FsUtils.bigdec(FsUtils.mulDouble(bean.getNewPrice(), 10000D)));
            clMortgage.setDyAddress(bean.getDyAddress());
            clMortgage.setDyArea(FsUtils.bigdec(bean.getDyArea()));
            clMortgage.setDyBuyPrice(FsUtils.bigdec(FsUtils.mulDouble(bean.getDyBuyPrice(), 10000D)));
            clMortgage.setDyBuyYear(bean.getDyBuyYear());
            clMortgage.setDyCity(bean.getDyCity());
            clMortgage.setDyCommunity(bean.getDyCommunity());
            clMortgage.setDescript("车库");
            clMortgage.setDyType("10");
            clMortgage.setOrderNo(NidGenerator.getOrderNo());
            clMortgage.setRealQuota(FsUtils.bigdec(0));
            clMortgage.setState(ClMortgageOrderState.STATE_PRE);
            clMortgage.setHalt("F");
            clMortgage.setUserId(bean.getUserId());

            this.update(clMortgage);
            notifyService.mortgageApplyNotify(clMortgage);
            return Response.success(clMortgage);
        }


    }

    private boolean isCanMortgage(MortgageBean bean) {
        if (FsUtils.strsEmpty(bean.getUserId())) {
            throw new ServiceException("未找到用户信息");
        }
        DCloanUserDomain userDomain = userService.getUserInfo(bean.getUserId());
        if (FsUtils.strsEmpty(userDomain.getIdNo())) {
            throw new ServiceException("未完成实名认证");
        }
        if (!userService.userIsAuth(bean.getUserId()).isAuth()) {
            throw new ServiceException("认证未完成!");
        }
        //黑名单
        DCloanUserDomain userInfo = userService.getUserInfoByUserId(bean.getUserId());
        /*if (!"20".equals(userInfo.getState())) {
            throw new ServiceException("该账号无法分期,请联系客服人员!");
        }*/
        if (FsUtils.strsEmpty(userInfo.getIdNo())) {
            throw new ServiceException("认证未完成!");
        }
        return true;

    }

    public ClMortgage getMortgage(Long userId) {
        //获取抵押物申请信息
        ClMortgage clMortgage = new ClMortgage();
        clMortgage.setUserId(userId);
        clMortgage.setHalt("F");
        List<ClMortgage> clMortgages = mapper.select(clMortgage);
        if (FsUtils.strsEmpty(clMortgages) || clMortgages.isEmpty()) {
            return null;
        } else {
            return clMortgages.get(0);
        }
    }

    public ClMortgage getMortgageHome(Long userId) {
        //获取抵押物申请信息
        ClMortgage clMortgage = new ClMortgage();
        clMortgage.setUserId(userId);
        List<ClMortgage> clMortgages = mapper.select(clMortgage);
        if (FsUtils.strsEmpty(clMortgages) || clMortgages.isEmpty()) {
            return null;
        } else {
            return clMortgages.get(clMortgages.size()-1);
        }
    }

    public ClMortgage getMortgageById(Long id) {
        return this.findById(id);
    }

    public MortgageBean getMortgageBeanInfo(Long userId) {

        ClMortgage clMortgage = getMortgage(userId);
        if (FsUtils.strsNotEmpty(clMortgage)) {
            return getMortgagBean(clMortgage);

        }
        return null;
    }

    /**
     * 订单初审
     *
     * @param state
     * @param mid
     * @param sysUserId
     */
    public Response trialAudit(String state, Long mid, String remark, Long sysUserId) {
        if (!FsUtils.ArrayContains(new String[]{ClMortgageOrderState.STATE_REFUSED, ClMortgageOrderState.STATE_TRIAL}, state)) {
            return Response.fail("状态不正确不能审核");
        }
        ClMortgage clMortgage = getMortgageById(mid);
        if (FsUtils.strsNotEmpty(clMortgage)) {
            //若用户在黑名单，则提示该用户为黑名单用户不可通过审核
            if (!state.equals(ClMortgageOrderState.STATE_REFUSED)) {
                DCloanUserDomain userInfo = userService.getUserInfoByUserId(clMortgage.getUserId());
                if (!"20".equals(userInfo.getState())) {
                    return Response.fail("该用户为黑名单用户不可通过审核!");
                }
            }
            if (clMortgage.getState().equals(ClMortgageOrderState.STATE_PRE)) {
                ClMortgage clMortgageforUpdate = new ClMortgage();
                clMortgageforUpdate.setId(clMortgage.getId());
                clMortgageforUpdate.setState(state);
                if (state.equals(ClMortgageOrderState.STATE_REFUSED)) {
                    clMortgageforUpdate.setHalt("T");
                    clMortgage.setHalt("T");
                    clMortgage.setState(ClMortgageOrderState.STATE_REFUSED);
                    notifyService.mortgageRefuseNotify(clMortgage);
                } else {
                    clMortgage.setState(state);
                    notifyService.mortgageTrailNotify(clMortgage);
                }
                clMortgageforUpdate.setRemark(remark);
                clMortgageforUpdate.setUpdated(new Date());
                clMortgageforUpdate.setApplyby(sysUserId);
                this.update(clMortgageforUpdate);

                return Response.success();
            } else {
                return Response.fail("该审核已经在处理中，请勿重复操作");
            }
        } else {
            return Response.fail("未找到抵押申请,请检查");
        }

    }

    /**
     * 初审后上传补充资料
     *
     * @param mid
     * @param url
     * @param cat
     * @return
     */
    public Response uploadOtherImg(Long mid, List<String> url, String cat) {
        ClMortgage mortgage = getMortgageById(mid);
        if (FsUtils.strsEmpty(mortgage)) {
            return Response.fail("未找到申请信息,请检查!");
        }
//        if (!mortgage.getState().equals(ClMortgageOrderState.STATE_TRIAL)) {
//            return Response.fail("非初审状态下面不允许上传图片资料");
//        }
        return clMortgageImgService.batchUploadImg(url, mortgage.getUserId(), mortgage.getId(), cat);
    }

    /**
     * 设置 抵押物额度
     *
     * @param bean
     */
    public Response setMortgageQuota(MortgageBean bean) {

        ClMortgage clMortgage = this.findById(bean.getId());
        if (FsUtils.strsNotEmpty(clMortgage)) {
            if (clMortgage.getState().equals(ClMortgageOrderState.STATE_TRIAL)||clMortgage.getState().equals(ClMortgageOrderState.STATE_V_PASS)) {
                ClMortgage clMortgageforUpdate = new ClMortgage();
                clMortgageforUpdate.setId(bean.getId());
                clMortgageforUpdate.setRealQuota(FsUtils.bigdec(bean.getRealQuota()));
                logger.info("------------------>bean.getRealRate()==="+bean.getRealRate());
                logger.info("------------------>bean.getRealRate==="+String.valueOf(bean.getRealRate()));
                clMortgageforUpdate.setRealRate(bean.getRealRate());
                clMortgage.setUpdated(new Date());
                arcCreditService.setUserQuota(clMortgage.getUserId(), bean.getRealQuota());
                this.update(clMortgageforUpdate);
                return Response.success();
            } else {
                return Response.fail("该审核已经在处理中，请勿重复操作");
            }
        } else {
            return Response.fail("未找到抵押申请,请检查");
        }
    }

    /**
     * 审核抵押物
     */
    @Override
    public Response auditMortgage(BorrowAuditBean bean) {
        if (!FsUtils.ArrayContains(new String[]{ClMortgageOrderState.STATE_REFUSED, ClMortgageOrderState.STATE_V_PASS}, bean.getState())) {
            return Response.fail("状态不正确不能审核");
        }
        ClMortgage clMortgage = this.findById(bean.getId());
        if (FsUtils.strsNotEmpty(clMortgage)) {
            //若用户在黑名单，则提示该用户为黑名单用户不可通过审核
            if (!bean.getState().equals(ClMortgageOrderState.STATE_REFUSED)) {
                DCloanUserDomain userInfo = userService.getUserInfoByUserId(clMortgage.getUserId());
                if (!"20".equals(userInfo.getState())) {
                    return Response.fail("该用户为黑名单用户不可通过审核!");
                }
            }
            if (clMortgage.getState().equals(ClMortgageOrderState.STATE_TRIAL)) {
                if (clMortgage.getRealQuota().doubleValue() == 0D || clMortgage.getRealRate().doubleValue() == 0D) {
                    return Response.fail("未确定最终的额度或利率，请先设置");
                }
                ClMortgage clMortgageforUpdate = new ClMortgage();
                clMortgageforUpdate.setId(bean.getId());
                if (bean.getState().equals(ClMortgageOrderState.STATE_REFUSED)) {
                    clMortgageforUpdate.setHalt("T");
                    clMortgage.setHalt("T");
                    clMortgage.setState(ClMortgageOrderState.STATE_REFUSED);
                    notifyService.mortgageRefuseNotify(clMortgage);
                } else {
                    clMortgage.setState(bean.getState());
                    // arcCreditService.setUserQuota(clMortgage.getUserId(), clMortgage.getRealQuota().doubleValue());
                    notifyService.mortgageReviewNotify(clMortgage);
                }
                clMortgageforUpdate.setState(bean.getState());
                clMortgageforUpdate.setPassBy(null);
                clMortgage.setUpdated(new Date());
                this.update(clMortgageforUpdate);
                return Response.success();
            }
        }
        return Response.fail("未找到抵押申请,请检查");

    }

    /**
     * 合同签署完毕 确认可以提现
     */
    @Override
    public Response auditFinalMortgage(BorrowAuditBean bean) {
        if (!FsUtils.ArrayContains(new String[]{ClMortgageOrderState.STATE_REFUSED, ClMortgageOrderState.STATE_PASS}, bean.getState())) {
            return Response.fail("状态不正确不能审核");
        }
        ClMortgage clMortgage = this.findById(bean.getId());
        if (FsUtils.strsNotEmpty(clMortgage)) {
            //若用户在黑名单，则提示该用户为黑名单用户不可通过审核
            if (!bean.getState().equals(ClMortgageOrderState.STATE_REFUSED)) {
                DCloanUserDomain userInfo = userService.getUserInfoByUserId(clMortgage.getUserId());
                if (!"20".equals(userInfo.getState())) {
                    return Response.fail("该用户为黑名单用户不可通过审核!");
                }
            }
            if (clMortgage.getState().equals(ClMortgageOrderState.STATE_V_PASS)) {
                if (clMortgage.getRealQuota().doubleValue() == 0D || clMortgage.getRealRate().doubleValue() == 0D) {
                    return Response.fail("未确定最终的额度或利率，请先设置");
                }
                ClMortgage clMortgageforUpdate = new ClMortgage();
                clMortgageforUpdate.setId(bean.getId());
                String month = sysConfigService.getConfigDefault("mortgage_month", "60");
                if (FsUtils.strsEmpty(month)) {
                    month = "60";
                }
                clMortgageforUpdate.setExpireDate(FsUtils.addYear(new Date(), FsUtils.i(month)));
                if (bean.getState().equals(ClMortgageOrderState.STATE_REFUSED)) {
                    clMortgageforUpdate.setHalt("T");
                    clMortgage.setHalt("T");
                    clMortgage.setState(ClMortgageOrderState.STATE_REFUSED);
                    notifyService.mortgageRefuseNotify(clMortgage);
                } else {
                    clMortgage.setState(bean.getState());
                    arcCreditService.setUserQuota(clMortgage.getUserId(), clMortgage.getRealQuota().doubleValue());
                    notifyService.mortgageAuthNotify(clMortgage);
                    String phone=clMortgage.getMobile();
                    logger.info("------------->phone="+phone);
                    CLWContacts wcontacts=clwContactService.getByPhones(phone);
                    UserBaseInfoByPhoneRequest infore=new UserBaseInfoByPhoneRequest();
                    infore.setPhone(phone);
                    UserBaseInfoByPhoneResponse response=userClient.invoke(infore);
                    logger.info("------------->response="+response);
                    logger.info("------------->data="+response.getData());
                    String idNo=response.getData().getIdNo();
                    String name=response.getData().getRealName();
                    String education=response.getData().getEducation();
                    String liveAddr=response.getData().getLiveAddr();
                    if(null!=wcontacts){
                    	if("1".equals(wcontacts.getIsAvailability())){
                    		wcontacts.setBorrowQuota(new BigDecimal(bean.getRealQuota()));
                    		wcontacts.setId(wcontacts.getId());
                    		wcontacts.setBorrowRate(bean.getRealRate());
                    		wcontacts.setBorrowId(bean.getBorrowId());
                    		wcontacts.setCityCode(bean.getCityCode());
                    		wcontacts.setDeclarationPeople(bean.getDeclarationPeople());
                    		wcontacts.setDeclarationCompany(bean.getDeclarationCompany());
                    		wcontacts.setDyValue(new BigDecimal(bean.getDyValue()));
                    		wcontacts.setParkingPosition(bean.getParkingPosition());
                    		wcontacts.setIdNo(idNo);
                    		wcontacts.setLiveAddr(liveAddr);
                    		wcontacts.setEducation(education);
                    		wcontacts.setName(name);
                    		wcontacts.setUpdated(new Date());
                    		clwContactService.update(wcontacts);
                    		
                    	}else{
                    		CLWContacts clwc=new CLWContacts();	
                    		clwc.setBorrowQuota(new BigDecimal(bean.getRealQuota()));
                    		clwc.setBorrowRate(bean.getRealRate());
                    		clwc.setPhone(phone);
                    		clwc.setIdNo(idNo);
                    		clwc.setName(name);
                    		clwc.setState("T");
                    		clwc.setIsCredit("F");
                    		clwc.setIsDy("T");
                    		clwc.setIsBorrow("F");
                    		clwc.setEducation(education);
                    		clwc.setLiveAddr(liveAddr);
                    		clwc.setDyValue(new BigDecimal(bean.getDyValue()));
                    		clwc.setDyValueDiscount(new BigDecimal(bean.getRealQuota()/bean.getDyValue()));
                    		clwc.setQuotaExpire(36);
                    		clwc.setBorrowId(bean.getBorrowId());
                    		clwc.setOriginalLimit(new BigDecimal(bean.getRealQuota()));
                    		clwc.setSurplusLimit(new BigDecimal(bean.getRealQuota()));
                    		clwc.setCityCode(bean.getCityCode());
                    		clwc.setIsAvailability("1");
                    		clwc.setCreated(new Date());
                    		clwc.setDeclarationCompany(bean.getDeclarationCompany());
                    		clwc.setDeclarationPeople(bean.getDeclarationPeople());
                    		clwc.setParkingPosition((null==bean.getParkingPosition()?"":bean.getParkingPosition()));
                    		clwContactService.add(clwc);
                    	}
                    }else{
                    	CLWContacts clwc=new CLWContacts();	
                		clwc.setBorrowQuota(new BigDecimal(bean.getRealQuota()));
                		clwc.setBorrowRate(bean.getRealRate());
                		clwc.setPhone(phone);
                		clwc.setIdNo(idNo);
                		clwc.setName(name);
                		clwc.setState("T");
                		clwc.setIsCredit("F");
                		clwc.setIsDy("T");
                		clwc.setIsBorrow("F");
                		clwc.setEducation(education);
                		clwc.setLiveAddr(liveAddr);
                		clwc.setDyValue(new BigDecimal(bean.getDyValue()));
                		clwc.setDyValueDiscount(new BigDecimal(bean.getRealQuota()/bean.getDyValue()));
                		clwc.setQuotaExpire(36);
                		clwc.setBorrowId(bean.getBorrowId());
                		clwc.setOriginalLimit(new BigDecimal(bean.getRealQuota()));
                		clwc.setSurplusLimit(new BigDecimal(bean.getRealQuota()));
                		clwc.setCityCode(bean.getCityCode());
                		clwc.setIsAvailability("1");
                		clwc.setCreated(new Date());
                		clwc.setDeclarationCompany(bean.getDeclarationCompany());
                		clwc.setDeclarationPeople(bean.getDeclarationPeople());
                		clwc.setParkingPosition((null==bean.getParkingPosition()?"":bean.getParkingPosition()));
                		clwContactService.add(clwc);
                    }
                    clMortgageforUpdate.setRealQuota(new BigDecimal(bean.getRealQuota()));
                    clMortgageforUpdate.setRealRate(bean.getRealRate());
                }
                clMortgageforUpdate.setState(bean.getState());
                clMortgageforUpdate.setPassBy(null);
                clMortgage.setUpdated(new Date());
                this.update(clMortgageforUpdate);
                //复审通过 短信通知 用户提款
                return Response.success();
            } else return Response.fail("抵押申请状态不能，请先进行复审");
        }
        return Response.fail("未找到抵押申请,请检查");
    }

    /**
     * 查询抵押申请列表
     *
     * @return
     */
    @Override
    public Response getMortgageList(BorrowQueryRequest bean) {
        PageHelper.startPage(bean.getPage(), bean.getPageSize());
        Condition condition = new Condition(ClMortgage.class);
        Example.Criteria criteria = condition.createCriteria();
        if (FsUtils.strsNotEmpty(bean.getOrderNo()))
            criteria.andEqualTo("orderNo", bean.getOrderNo());
        if (FsUtils.strsNotEmpty(bean.getName()))
            criteria.andLike("name", bean.getName() + "%");
        if (FsUtils.strsNotEmpty(bean.getMobile()))
            criteria.andEqualTo("mobile", bean.getMobile());
        if (FsUtils.strsNotEmpty(bean.getState()))
            criteria.andEqualTo("state", bean.getState());
        if (FsUtils.strsNotEmpty(bean.getStart()))
            criteria.andGreaterThanOrEqualTo("apply_date", bean.getStart());
        if (FsUtils.strsNotEmpty(bean.getEnd()))
            criteria.andLessThanOrEqualTo("apply_date", bean.getEnd());
        condition.setOrderByClause("apply_date");
        List<ClMortgage> clMortgages = this.mapper.selectByCondition(condition);
        List<MortgageBean> beans = PageUtils.convert(clMortgages, new DataObjectConverter<ClMortgage, MortgageBean>() {
            @Override
            public MortgageBean convert(ClMortgage mortgage) {
                return getMortgagBean(mortgage);
            }
        });

        return Response.success(beans);
    }

    public MortgageBean getMortgagBean(Long id) {
        ClMortgage clMortgage = getMortgageById(id);
        return getMortgagBean(clMortgage);
    }

    /**
     * 关联白名单
     * @param uid
     */
    @Override
    public void handleWhiteListForMortgage(Long uid) {
        ClMortgage clMortgage = getMortgage(uid);
        if (!ParamUtil.isEmpty(clMortgage)) {
            return;
        }

        DCloanUserDomain user = userService.getUserInfo(uid);
        if (null == user) {
            return;
        }

        List<CLWContacts> contacts =clwContactService.getContactsByPhone(user.getPhone());
        if (null == contacts || contacts.isEmpty()) {
            return;
        }
        //开始增加车位信息
        ClMortgage mortgage = fillMortgageByCLWContacts(contacts.get(0), uid);
        this.mapper.insert(mortgage);
        return;

    }

    @Override
    public Response updateMortgageByMobile(MortgageBean bean) {
        Map<String, Object> map = new HashMap();
        map.put("realQuota",bean.getRealQuota());
        map.put("realRate",bean.getRealRate());
        map.put("mobile",bean.getMobile());
        clMortgageMapper.updateByMobile(map);
        return Response.success();
    }




    private ClMortgage fillMortgageByCLWContacts(CLWContacts contacts, Long uid) {
        ClMortgage mortgage = new ClMortgage();
        mortgage.setUserId(uid);
        mortgage.setMobile(contacts.getPhone());
        mortgage.setName(contacts.getName());
        mortgage.setDyCity(contacts.getDyCity());
        mortgage.setDyAddress(contacts.getLiveAddr());
        mortgage.setDyCommunity(contacts.getLiveCommunity());
        mortgage.setMarketValue(contacts.getDyValue());
        mortgage.setBorrowAmount(contacts.getBorrowQuota());
        mortgage.setExpireDate(DateUtil.getSomeDay(new Date(), contacts.getQuotaExpire() * 30));
        mortgage.setState("40");    //终审通过
        mortgage.setHalt("F");      //有效
        mortgage.setRemark("白名单");
        mortgage.setApplyDate(new Date());
        mortgage.setRealQuota(contacts.getBorrowQuota());
        mortgage.setRealRate(contacts.getBorrowRate());
        mortgage.setCreated(new Date());
        mortgage.setUpdated(new Date());

        return mortgage;
    }

    private MortgageBean getMortgagBean(ClMortgage clMortgage) {
        MortgageBean bean = new MortgageBean();
        if (FsUtils.strsEmpty(clMortgage)) {
            return bean;
        }
        bean.setApplyDate(clMortgage.getApplyDate());
        bean.setBorrowAmount(FsUtils.d(clMortgage.getBorrowAmount()));
        bean.setCreated(clMortgage.getCreated());
        bean.setUserName(clMortgage.getName());
        bean.setId(clMortgage.getId());
        bean.setMobile(clMortgage.getMobile());
        bean.setOrderNo(clMortgage.getOrderNo());
        bean.setDyAddress(clMortgage.getDyAddress());
        bean.setDyArea(FsUtils.d(clMortgage.getDyArea()));
        bean.setDyBuyPrice(FsUtils.d(clMortgage.getDyBuyPrice()));
        bean.setDyBuyYear(clMortgage.getDyBuyYear());
        bean.setDyCity(clMortgage.getDyCity());
        bean.setDyCommunity(clMortgage.getDyCommunity());
        bean.setDescript(clMortgage.getDescript());
        bean.setDyType(clMortgage.getDyType());
        bean.setNewPrice(clMortgage.getMarketValue() == null ? 0D : clMortgage.getMarketValue().doubleValue());
        bean.setRealQuota(FsUtils.d(clMortgage.getRealQuota()));
        bean.setStatus(clMortgage.getState());
        bean.setUserId(clMortgage.getUserId());
        bean.setRealRate(clMortgage.getRealRate());
        List<String> assetsImgs = clMortgageImgService.getImg(MortgageImgTypeConstant.ASSETS, clMortgage.getId());
        bean.setAssetsImg(assetsImgs);
        List<String> userImgs = clMortgageImgService.getImg(MortgageImgTypeConstant.USERIMG, clMortgage.getId());
        bean.setUserImg(userImgs);
        List<String> otherImgs = clMortgageImgService.getImg(MortgageImgTypeConstant.OTHER, clMortgage.getId());
        bean.setOtherImg(otherImgs);
        return bean;
    }

	
}
