package com.hwc.framework.modules.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponsePage;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.common.PayConstant;
import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.service.PayGatewayService;
import com.hwc.framework.modules.service.PayService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;

import cn.freesoft.utils.FsUtils;

/**
 * Created by   on 2017/11/1.
 * 连连支付 放款 处理
 */
@Service
public class PayServiceImpl implements PayService {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplyServiceImpl.class);
    @Autowired
    private ClBorrowService     borrowService;
    @Autowired
    private ClBankCardService   bankCardService;
    @Autowired
    private UserService         userService;
    @Autowired
    private PayGatewayService   payGatewayService;
    @Autowired
    private ClPayLogService     payLogService;
    @Autowired
    private ArcSysConfigService configService;
    @Autowired
    private ArcCreditService    arcCreditService;
    @Autowired
    private BorrowNotifyService borrowNotifyService;

    @Override
    public Response manualLoan(Long borrowId) {
        ClBorrow borrow = borrowService.getBorrow(borrowId);
        if (FsUtils.strsEmpty(borrow)) {
            return Response.fail("未找到订单记录");
        }
        if (borrow.getState().equals(BorrowStateConstant.STATE_REPAY_FAIL)) {
            PayDataDomain dataDomain = new PayDataDomain();
            ClBankCard card = bankCardService.getBankCard(borrow.getUserId());
            DCloanUserDomain userInfo = userService.getUserInfo(borrow.getUserId());
            dataDomain.setReal_name(userInfo.getRealName());
            dataDomain.setBank_name(card.getBank());
            dataDomain.setBank_card_no(card.getCardNo());
            dataDomain.setAgree_no(card.getAgreeNo());
            dataDomain.setPay_channel(card.getPayCode());
            String switchStr = configService.getConfigDefault("pay_switch", "dev");
            //测试环境
            if (switchStr.equals("dev")) {
                dataDomain.setAmount(0.01);
            } else {
            dataDomain.setAmount(borrow.getRealAmount().doubleValue());
            }
            dataDomain.setId_no(userInfo.getIdNo());
            dataDomain.setMobile(userInfo.getPhone());
            dataDomain.setOrder_no(NidGenerator.getPayOrderNo());
            dataDomain.setBorrow_id(borrow.getId());
            dataDomain.setUser_id(borrow.getUserId());
            dataDomain.setService_name("loan");
            Response response = payGatewayService.doPay(dataDomain);
            if (!response.getSuccess()) {
                ClBorrow borrow1 = new ClBorrow();
                borrow1.setId(borrow.getId());
                borrow1.setRemark(response.getMessage());
                borrowService.update(borrow1);
            }
            response = payLogService.manualLoan(borrow, card, dataDomain.getOrder_no(), response);
            return response;
        } else {
            return Response.fail("状态不正确，不能手工放款");
        }
    }

    public Response loan(ClBorrow borrow) {
        PayDataDomain dataDomain = new PayDataDomain();
        ClBankCard card = bankCardService.getBankCard(borrow.getUserId());
        DCloanUserDomain userInfo = userService.getUserInfo(borrow.getUserId());
        dataDomain.setReal_name(userInfo.getRealName());
        dataDomain.setBank_name(card.getBank());
        dataDomain.setBank_card_no(card.getCardNo());
        dataDomain.setAgree_no(card.getAgreeNo());
        dataDomain.setPay_channel(card.getPayCode());
        String switchStr = configService.getConfigDefault("pay_switch", "dev");
        //测试环境
                if (switchStr.equals("dev")) {
                    dataDomain.setAmount(0.01);
                } else {
        dataDomain.setAmount(borrow.getRealAmount().doubleValue());
                }

        dataDomain.setId_no(userInfo.getIdNo());
        dataDomain.setMobile(userInfo.getPhone());
        dataDomain.setOrder_no(NidGenerator.getPayOrderNo());
        dataDomain.setBorrow_id(borrow.getId());
        dataDomain.setUser_id(borrow.getUserId());
        dataDomain.setService_name("loan");
        Response response = payGatewayService.doPay(dataDomain);
        if (response.getSuccess()) {

        }
        payLogService.autoLoan(borrow, card, dataDomain.getOrder_no(), response);
        return response;
    }

    public Response queryPayMent(String orderNo) {
        PayQueryDomain domain = new PayQueryDomain();
        domain.setOrder_no(orderNo);
        Response response = payGatewayService.doPayQuery(domain);
        if (response.getSuccess()) {
            return loanQueryCallback((ResponsePage) response.getData());
        } else {
            ClPayLog log = payLogService.getPayLog(orderNo);
            if("10".equals(log.getScenes())&&"40".equals(log.getState())){
                return response;
            }
            payLogService.updatePayLogState(null, orderNo, PayConstant.STATE_PAYMENT_FAILED,
                response.getMessage(), "");
            if (FsUtils.strsNotEmpty(log))
                borrowService.loanCallback(log.getBorrowId(), BorrowStateConstant.STATE_REPAY_FAIL);
            return response;
        }
    }

    public String loanCallback(HttpServletRequest request) {
        Response response = payGatewayService.payCallback(request);
        logger.info("异步放款回调,data=>" + JSONObject.toJSONString(response));
        if (response.getSuccess()) {
            ResponsePage page = (ResponsePage) response.getData();

            Response response1 = loanQueryCallback(page);
            if (response1.getSuccess())
                return "OK";
            else {
                return response1.getMessage();
            }
        } else {
            return response.getMessage();
        }

    }

    public Response loanQueryCallback(List<String> orders) {
        PayQueryDomain domain = new PayQueryDomain();
        String orderNo = "";
        for (String order : orders) {
            orderNo += "," + order;
        }
        return payGatewayService.doPayQuery(domain);
    }

    public Response loanQueryCallback(ResponsePage page) {
        for (Object obj : page.getItems()) {
            try {
                PayRespBean bean = (PayRespBean) obj;
                logger.info("loanQueryCallback,orderNo=>" + bean.getOrder_no() + ",data=>"
                            + JSONObject.toJSONString(bean));
                ClPayLog log = payLogService.getPayLog(bean.getOrder_no());
                if (FsUtils.strsNotEmpty(log)) {
                    ClBorrow borrow = borrowService.getBorrow(log.getBorrowId());
                    if (FsUtils.strsEmpty(borrow)) {
                        continue;
                    } else {
                        //如果已经放款成功，后面不做处理
                        if (borrow.getState().equals(BorrowStateConstant.STATE_REPAY)) {
                            continue;
                        }
                    }
                    if (bean.getState().equals("O")) {
                        payLogService.updatePayLogState(borrow, log.getOrderNo(),
                            PayConstant.STATE_PAYMENT_SUCCESS, "放款成功", bean.getResponse_id());
                        borrowService.loanCallback(log.getBorrowId(),
                            BorrowStateConstant.STATE_REPAY);
                        try {
                        	//放款成功加推送
							
                        	borrowNotifyService.paySuccessNotify(borrow);
						} catch (Exception e) {
							logger.error("放款成功加推送错误");
						}
                        
                    } else if ("F".equals(bean.getState())) {
                        payLogService.updatePayLogState(borrow, log.getOrderNo(),
                            PayConstant.STATE_PAYMENT_FAILED, bean.getRemark(), "");
                        borrowService.loanCallback(log.getBorrowId(),
                            BorrowStateConstant.STATE_REPAY_FAIL);
                        //回复额度
                        arcCreditService.increaseQuota(borrow.getUserId(),
                            borrow.getAmount().doubleValue());
                    } else if ("Q".equals(bean.getState())) {
                        logger.info("继续等待回调结果: {}", JSON.toJSONString(bean));
                    }
                }

            } catch (Exception ex) {
                logger.error("代付回调失败", ex);
                return Response.fail(ex.getMessage());
            }
        }
        return Response.success();
    }
}
