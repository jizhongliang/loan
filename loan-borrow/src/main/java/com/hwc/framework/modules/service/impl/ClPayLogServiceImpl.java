package com.hwc.framework.modules.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.framework.common.PayConstant;
import com.hwc.framework.modules.dao.ClPayLogMapper;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.domain.PayQueryRequest;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.service.PayGatewayService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.DataObjectConverter;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/31.
 * 支付记录
 */
@Service
public class ClPayLogServiceImpl extends AbstractService<ClPayLogMapper, ClPayLog>
                                 implements ClPayLogService {
    private static final Logger  logger = LoggerFactory.getLogger(ClPayLogServiceImpl.class);

    @Autowired
    private ClBankCardService    bankCardService;
    @Autowired
    private PayGatewayService    payGatewayService;

    @Autowired
    private UserService          userService;
    @Autowired
    private ClBorrowRepayService borrowRepayService;

    @Autowired
    private ClBorrowService      borrowService;

    public ClPayLog getPayLogByRepayId(Long repayId) {
        ClPayLog log = new ClPayLog();
        log.setRepayId(repayId);
        log.setState(PayConstant.STATE_PAYMENT_WAIT);
        List<ClPayLog> payLogs = mapper.select(log);
        if (FsUtils.strsNotEmpty(payLogs) && !payLogs.isEmpty()) {
            return payLogs.get(0);
        } else {
            return null;
        }

    }

    private void doSavePayLog(PayLogBean bean) {
        ClPayLog payLog = new ClPayLog();
        payLog.setOrderNo(bean.getOrderNo());
        payLog.setUserId(bean.getUserId());
        payLog.setBorrowId(bean.getBorrowId());
        payLog.setAmount(FsUtils.bigdec(bean.getAmount()));
        payLog.setCardNo(bean.getBankCardNo());
        DCloanUserDomain userInfo = userService.getUserInfo(bean.getUserId());
        payLog.setMobile(userInfo.getPhone());
        payLog.setName(userInfo.getRealName());
        //等0 为提前还款
        payLog.setRepayId(bean.getRepayId() == null ? 0 : bean.getRepayId());
        payLog.setBank(bean.getBankCardName());
        payLog.setSource(bean.getSource());
        payLog.setType(bean.getType());
        payLog.setScenes(bean.getScenes());
        payLog.setState(bean.getState());
        payLog.setRemark(bean.getRemark());
        payLog.setPayReqTime(bean.getPayTime());
        payLog.setCreateTime(new Date());
        this.insert(payLog);
    }

    /**
     * 自动放款
     */
    public Response autoLoan(ClBorrow borrow, ClBankCard card, String orderNo, Response response) {

        PayLogBean bean = new PayLogBean();
        if (response.getSuccess()) {
            bean.setState(PayConstant.STATE_PAYMENT_WAIT);
            bean.setRemark("待放款");
        } else {
            bean.setState(PayConstant.STATE_PAYMENT_FAILED);
            bean.setRemark(response.getMessage());
        }
        bean.setBorrowId(borrow.getId());
        bean.setOrderNo(orderNo);
        bean.setAmount(borrow.getRealAmount().doubleValue());
        bean.setRepayId(0L);
        // ClBankCard card = bankCardService.getBankCard(borrow.getUserId());
        bean.setBankCardName(card.getBank());
        bean.setBankCardNo(card.getCardNo());
        bean.setSource(PayConstant.SOURCE_FUNDS_OWN);
        bean.setType(PayConstant.TYPE_PAYMENT);
        bean.setScenes(PayConstant.SCENES_LOANS);
        bean.setUserId(borrow.getUserId());
        bean.setPayTime(new Date());

        doSavePayLog(bean);
        // bean.setState();
        return Response.success(bean);
    }

    public ClPayLog updatePayLogState(ClBorrow borrow, String orderNo, String state, String remark,
                                      String serialNumber) {
        ClPayLog payLog = getPayLog(orderNo);
        if (FsUtils.strsNotEmpty(payLog)) {
            payLog.setState(state);
            payLog.setRemark(remark);
            payLog.setUpdateTime(new Date());
            payLog.setSerialNumber(serialNumber);
            this.update(payLog);
        } else {
            logger.error("未找不到付款请求记录", orderNo);
            return null;
        }
        if (payLog.getState().equals(PayConstant.STATE_PAYMENT_SUCCESS)) {
            //代付成功 生成还款计划
            borrowRepayService.createRepayPlan(borrow);
            return payLog;
        }
        return null;
    }

    public ClPayLog updatChargeLogState(String orderNo, Response response) {
        ClPayLog payLog = getPayLog(orderNo);
        if (FsUtils.strsNotEmpty(payLog)) {
            if (response.getSuccess()) {
                ChargeRespBean bean = (ChargeRespBean) response.getData();
                if ("O".equals(bean.getState())) {
                    payLog.setState(PayConstant.STATE_PAYMENT_SUCCESS);
                    payLog.setSerialNumber(bean.getOrder_no());
                    payLog.setRemark("还款成功");
                } else {
                    payLog.setState(PayConstant.STATE_PAYMENT_FAILED);
                    payLog
                        .setRemark(FsUtils.strsEmpty(bean.getRemark()) ? "还款失败" : bean.getRemark());
                }
            } else {
                payLog.setState(PayConstant.STATE_PAYMENT_FAILED);
                payLog.setRemark(response.getMessage());
            }
            payLog.setPayReqTime(new Date());
            payLog.setUpdateTime(new Date());
            logger.info("updatChargeLogState更新,borrowId=>" + payLog.getBorrowId() + ",data=>"
                        + JSONObject.toJSONString(payLog));
            this.update(payLog);
            return payLog;
        } else {
            logger.error("未找不到付款请求记录", orderNo);
            return null;
        }
    }

    /**
     * 手工放款
     */
    public Response manualLoan(ClBorrow borrow, ClBankCard card, String orderNo,
                               Response response) {
        PayLogBean bean = new PayLogBean();
        if (response.getSuccess()) {
            bean.setState(PayConstant.STATE_PAYMENT_WAIT);
            bean.setRemark("线下待放款");
        } else {
            bean.setState(PayConstant.STATE_PAYMENT_FAILED);
            bean.setRemark(response.getMessage());
        }

        bean.setBorrowId(borrow.getId());
        bean.setOrderNo(orderNo);
        bean.setAmount(borrow.getRealAmount().doubleValue());
        bean.setRepayId(0L);
        //ClBankCard card = bankCardService.getBankCard(borrow.getUserId());
        bean.setBankCardName(card.getBank());
        bean.setBankCardNo(card.getCardNo());
        bean.setSource(PayConstant.SOURCE_FUNDS_OWN);
        bean.setType(PayConstant.TYPE_OFFLINE_PAYMENT);
        bean.setScenes(PayConstant.SCENES_LOANS);
        bean.setUserId(borrow.getUserId());
        doSavePayLog(bean);
        return Response.success(bean);
    }

    /**
     * 系统扣款
     */
    public void autoDebit(ClBorrowRepay borrow, ClBankCard card, String orderNo) {
        PayLogBean bean = new PayLogBean();
        bean.setState(PayConstant.STATE_PAYMENT_WAIT);
        bean.setBorrowId(borrow.getBorrowId());
        bean.setOrderNo(orderNo);
        bean.setRepayId(borrow.getId());
        bean.setAmount(borrow.getTotalAmount());
        bean.setBankCardName(card.getBank());
        bean.setBankCardNo(card.getCardNo());
        bean.setSource(PayConstant.SOURCE_FUNDS_OWN);
        bean.setType(PayConstant.TYPE_COLLECT);
        bean.setScenes(PayConstant.SCENES_REPAYMENT);
        bean.setUserId(borrow.getUserId());
        logger.info(
            "系统扣款,borrowId=>" + borrow.getBorrowId() + ",data=>" + JSONObject.toJSONString(bean));
        doSavePayLog(bean);
    }

    //    /**
    //     * 系统扣款
    //     */
    //    public void autoDebit(Long borrow_id, Double amount, Long userId, ClBankCard card, String orderNo) {
    //        PayLogBean bean = new PayLogBean();
    //        bean.setState(PayConstant.STATE_PAYMENT_WAIT);
    //        bean.setBorrowId(borrow_id);
    //        bean.setOrderNo(orderNo);
    //        bean.setRepayId(0L);
    //        bean.setAmount(amount);
    //        bean.setBankCardName(card.getBank());
    //        bean.setBankCardNo(card.getCardNo());
    //        bean.setSource(PayConstant.SOURCE_FUNDS_OWN);
    //        bean.setType(PayConstant.TYPE_COLLECT);
    //        bean.setScenes(PayConstant.SCENES_REPAYMENT);
    //        bean.setUserId(userId);
    //        bean.setRemark("待还款");
    //        doSavePayLog(bean);
    //    }

    //    /**
    //     * 手工扣款
    //     */
    //    public void manualDebit(ClBorrowRepay borrow, ClBankCard card) {
    //        PayLogBean bean = new PayLogBean();
    //        bean.setState(PayConstant.STATE_PAYMENT_WAIT);
    //        bean.setBorrowId(borrow.getId());
    //        bean.setOrderNo(NidGenerator.getPayOrderNo() + "02");
    //        bean.setAmount(borrow.getTotalAmount());
    //        //ClBankCard card = bankCardService.getBankCard(borrow.getUserId());
    //        bean.setBankCardName(card.getBank());
    //        bean.setBankCardNo(card.getCardNo());
    //        bean.setSource(PayConstant.SOURCE_FUNDS_OWN);
    //        bean.setType(PayConstant.TYPE_OFFLINE_COLLECT);
    //        bean.setScenes(PayConstant.SCENES_REPAYMENT);
    //        bean.setUserId(borrow.getUserId());
    //        bean.setRemark("线下待扣款");
    //        doSavePayLog(bean);
    //    }

    public ClPayLog getPayLog(String orderNo) {
        ClPayLog payLog = new ClPayLog();

        payLog.setOrderNo(orderNo);
        return this.mapper.selectOne(payLog);
    }

    public List<ClPayLog> getPayLogList(PayQueryRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String, Object> map = new HashMap();
        map.put("name", request.getName());
        map.put("state", request.getState());
        map.put("start", request.getStart());
        map.put("end", request.getEnd());
        map.put("scenes", request.getScenes());
        map.put("orderNo", request.getOrder_no());
        map.put("borrowType", request.getBorrow_type());
        map.put("type", request.getType());
        map.put("mobile", request.getMobile());
        return mapper.paylogList(map);

    }

    /**
     * 支付记录
     *
     * @param request
     * @return
     */
    public Response payLogList(PayQueryRequest request) {
        List<ClPayLog> payLogs = getPayLogList(request);
        List<PayLogBean> beans = PageUtils.convert(payLogs,
            new DataObjectConverter<ClPayLog, PayLogBean>() {
                @Override
                public PayLogBean convert(ClPayLog payLog) {
                    PayLogBean bean = new PayLogBean();
                    bean.setBorrowId(payLog.getBorrowId());
                    bean.setRepayId(payLog.getRepayId());

                    bean.setRemark(payLog.getRemark());
                    bean.setUserId(payLog.getUserId());
                    bean.setAmount(payLog.getAmount().doubleValue());
                    bean.setBankCardNo(payLog.getCardNo());
                    bean.setPayTime(payLog.getPayReqTime());
                    bean.setOrderNo(payLog.getOrderNo());
                    ClBorrow borrow = borrowService.getBorrow(payLog.getBorrowId());
                    bean.setBorrowTime(borrow.getCreateTime());
                    bean.setName(borrow.getName());
                    bean.setMobile(borrow.getMobile());
                    bean.setState(payLog.getState());
                    bean.setScenes(payLog.getScenes());
                    return bean;
                }
            });

        return Response.success(beans);
    }

	@Override
	public ClPayLog getBydesc(Long borrowId) {
		
		return mapper.getBydesc(borrowId);
	}

	@Override
	public List<ClPayLog> getList(Long borrowId) {
		
		return mapper.getList(borrowId);
	}

	@Override
	public ClPayLog getLastRepayLog(Long repayId) {
		// TODO Auto-generated method stub
		
		
		return mapper.getLastRepayLog(repayId);
	}
}
