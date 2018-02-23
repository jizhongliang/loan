package com.hwc.framework.modules.service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.domain.PreRepayInterestAndBreakFine;
import com.hwc.framework.modules.model.CLBorrow;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.ClQuartzInfo;

/**
 * Created by jzl on 2017/12/25.
 */
public interface BorrowRepayService {
    /**
     * 处理还款计划
     * @param borrowModel
     */
    void handleBorrowRepayPlan(CLBorrow borrowModel);

	/**
	 * 计算逾期
	 */
	ClQuartzInfo clacOverdue();

	/**
	 * 快到期提醒
	 * @return
	 */
	ClQuartzInfo getSoonExpireRepayPlan();

	/**
	 * 查询还款计划集合
	 * @param repayIds
	 * @return
	 */
    List<CLBorrowRepay> findBorrowRepayList(String repayIds);

	/**
	 * 校验还款状态 未还款返回true
	 * @param repayList
	 * @return
	 */
	boolean checkBorrowRepayUnRepay(List<CLBorrowRepay> repayList);

	/**
	 * 更新还款信息
	 * @param resultBoList
	 * @return
	 */
    boolean updateRepayInfos(List<RepayDataResultBo> resultBoList);

	/**
	 * 更新还款计划信息
	 * @param resultBo
	 */
	void updateBorrowRepayInfo(RepayDataResultBo resultBo);

	/**
	 * 查询还款计划实体
	 * @param repayId
	 * @return
	 */
	CLBorrowRepay findBorrowRepayById(Long repayId);

	/**
	 * 获取已还款的数量，分期数
	 * @param borrowId
	 * @return
	 */
	int getAlreadyRepayCountsByBorrowId(Long borrowId);

	/**
	 * 查询还款中的订单
	 * @return
	 */
    List<CLBorrowRepay> queryNeedQueryOrders();

	/**
	 * 查询还款状态
	 * @param repay
	 */
	boolean queryRepay(CLBorrowRepay repay) throws Exception;

    /**
     * 修改为还款中
     * @param repayList
     */
    void updateRepayInProgress(List<CLBorrowRepay> repayList);

	/**
	 * 计算该笔利息和违约金
	 * @param repay
	 * @param borrow
	 * @return
	 */
    PreRepayInterestAndBreakFine calcuPreRepayInterestAndBreakFine(CLBorrowRepay repay, CLBorrow borrow);

	/**
	 * 组装需要还款的repayIds
	 * @param userId
	 * @param type 1：当期还款 2：提前还款所有剩余的
	 * @return
	 */
    String structureRepayIds(Long userId, int type, Long borrowId);

	/**
	 * 计算利息
	 * @param amount 本金
	 * @param rate 利率
	 * @param timeLimit 天数
	 * @return
	 */
	BigDecimal caculateInterest(BigDecimal amount, BigDecimal rate, String timeLimit);

	/**
	 * 使用宝付支付
	 * @param pwd
	 * @param borrowId
	 * @param repayIds
	 * @return
	 */
    Response toBaoFuPay(String type, String pwd, String borrowId, String repayIds);

	List<CLBorrowRepay> queryCanPayOrder(Map<String, Object> param);

	String repay(CLBorrowRepay clBorrowRepay);
}
