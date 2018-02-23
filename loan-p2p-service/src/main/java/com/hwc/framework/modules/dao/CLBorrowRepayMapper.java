package com.hwc.framework.modules.dao;


import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.model.CLBorrowRepay;
import org.apache.ibatis.annotations.Param;

public interface CLBorrowRepayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLBorrowRepay record);

    int insertSelective(CLBorrowRepay record);

    CLBorrowRepay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLBorrowRepay record);

    int updateByPrimaryKey(CLBorrowRepay record);
    /**
     *  获取还款记录
     * @param map
     * @return
     */
	List<CLBorrowRepay> getUnRepayList(Map<String, Object> map);

    /**
     * 获取逾期记录
     * @param map
     * @return
     */
    List<Long> getOverdueList(Map<String, Object> map);

    int getAlreadyRepayCountsByBorrowId(Long borrowId);

    List<CLBorrowRepay> queryNeedQueryOrders();

    void updateRepayInProgress(Long repayId);

    List<CLBorrowRepay> findNextBorrowRepay(@Param("seq") Integer seq, @Param("borrowId") Long borrowId);

    List<CLBorrowRepay> findBorrowRepaysByUserIdAndBorrowId(@Param("userId") Long userId, @Param("borrowId") Long borrowId);

    CLBorrowRepay loadOverdue(Map<String, Object> map);

    CLBorrowRepay getOverdue(Map<String, Object> map);
    /** 
     * 查询可以扣款的记录
     * @param param 
     * @return
     */
	List<CLBorrowRepay> queryCanPayOrder(Map<String, Object> param);
}