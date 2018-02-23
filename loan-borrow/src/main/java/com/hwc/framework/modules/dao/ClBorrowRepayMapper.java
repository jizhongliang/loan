package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.domain.BorrowRepayBean;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.mybatis.core.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ClBorrowRepayMapper extends Mapper<ClBorrowRepay> {

    /**
     * 计算已还金额
     * @param borrowId
     * @return
     */
    BigDecimal clacRepayAmount(Long borrowId);
    List<ClBorrowRepay> getList(Long borrow_id);
    /**
     * 获取最近的一条还款计划
     *
     * @param borrowId
     * @return
     */
    ClBorrowRepay getLatelyRepayPlan(Long borrowId);

    /**
     * 计算未未还款金额
     * @param borrowId
     * @return
     */
    ClBorrowRepay clacUnRepayAmount(Long borrowId);
    /**
     * 获取上一个已经还款的 还款计划
     *
     * @param borrowId
     * @return
     */
    ClBorrowRepay getLatelyHasRepayPlan(Long borrowId);

    /**
     * 获取最后一个 未还款计划
     *
     * @param borrowId
     * @return
     */
    ClBorrowRepay getMaxUnRepayPlan(Long borrowId);

    List<BorrowRepayBean> getRepayList(Map<String, Object> map);

    /**
     *  获取未还款记录
     * @param map
     * @return
     */
    List<ClBorrowRepay> getUnRepayList(Map<String, Object> map);

    /**
     * 计算逾期期数
     * @param map
     * @return
     */
    Integer countOverdue(Map<String, Object> map);
}