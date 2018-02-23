package com.hwc.framework.modules.service;

import java.util.List;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.PayQueryRequest;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/31.
 */
public interface ClPayLogService extends Service<ClPayLog> {


    ClPayLog getPayLogByRepayId(Long repayId);

    /**
     * 自动放款记录
     *
     * @param borrow
     * @param card
     * @param orderNo
     */
    Response autoLoan(ClBorrow borrow, ClBankCard card, String orderNo, Response response);
    List<ClPayLog> getList(Long borrowId);
    /**
     * 手工放款
     */
    Response manualLoan(ClBorrow borrow, ClBankCard card, String order_no, Response response);

    /**
     * 系统扣款
     */
    void autoDebit(ClBorrowRepay borrow, ClBankCard card, String orderNo);

    // void autoDebit(Long borrow_id, Double amount, Long userId, ClBankCard card, String orderNo);


    /**
     * 支付 回调后 记录支付状态
     *
     * @param orderNo
     * @param state
     * @param remark
     */
    ClPayLog updatePayLogState(ClBorrow borrow, String orderNo, String state, String remark,String responseId);

    ClPayLog updatChargeLogState(String orderNo, Response response);

    ClPayLog getPayLog(String orderNo);
    ClPayLog getBydesc(Long borrowId);
    /**
     * 支付记录
     *
     * @param request
     * @return
     */
    Response payLogList(PayQueryRequest request);

	ClPayLog getLastRepayLog(Long repayId);
}
