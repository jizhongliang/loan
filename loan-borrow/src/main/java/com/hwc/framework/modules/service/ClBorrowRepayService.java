package com.hwc.framework.modules.service;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.*;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.ClBorrowRepay;

import java.math.BigDecimal;
import java.util.List;

/**
 * 2017/10/31.
 */
public interface ClBorrowRepayService extends Service<ClBorrowRepay> {
    /**
     * 获取还款计划
     *
     * @param borrowId
     * @return
     */
    public RepayPlanBean getRepayPlan(Long borrowId);

    /**
     * 计算利息
     *
     * @param amount
     * @param periods
     * @return
     */
    Double calcInterest(Double amount, Integer periods, Double rate);

    Response getTempRepayPlanList(BorrowBeanBigDecimal borrow);

    BorrowRepayBean calcFirstRepay(BorrowBean borrow);

    Response<BorrowRepayBean> getBorrowRepayDetail(IdRequest<Long> request);

    Response<RepayPlanBean> genCreditRepayPlan(BorrowBeanBigDecimal borrow);

    /**
     * 获取临时的还款计划
     *
     * @param borrow
     * @return
     */
    RepayPlanBean getTempRepayPlan(BorrowBean borrow);

    void createRepayPlan(ClBorrow borrow);

    /**
     * 获取一个人的应还款记录
     * @param userId
     * @return
     */
    public List<RepayPlanBean> getRepayPlans(Long userId);

    Response prePaymentCheck(Long borrowId);

    /**
     * 获取提前还款的金额
     * @param borrowId
     * @return
     */
    public Response getPrepaymentAmount(Long borrowId);

    Response repaymentSuccessCallback(Long borrow_id, ChargeRespBean bean);

    Response comfigPreRepayment(Long borrow_id, PreRepayBean repayBean, ChargeRespBean data);


    Response repayList(RepayQueryRequest request);


    List<ClBorrowRepay> getSoonExpireRepayPlan(int days);
    List<ClBorrowRepay> getList(Long borrow_id);
    void clacOverdue();

    void batchDebitRepay();

    /**
     * 计算利息
     * @param amount 本金
     * @param rate 利率
     * @param timeLimit 天数
     * @return
     */
    BigDecimal caculateInterest(BigDecimal amount, BigDecimal rate, String timeLimit);

    /**
     * 计算该笔利息和违约金
     * @param repay
     * @param borrow
     * @return
     */
    PreRepayInterestAndBreakFine calcuPreRepayInterestAndBreakFine(ClBorrowRepay repay, ClBorrow borrow);

	public List<RepayPlanBeanBigDecimal> getRepayPlanBig(Long id);
}
