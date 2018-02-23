package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.CLBorrowRepayLog;

public interface CLBorrowRepayLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLBorrowRepayLog record);

    int insertSelective(CLBorrowRepayLog record);

    CLBorrowRepayLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLBorrowRepayLog record);

    int updateByPrimaryKey(CLBorrowRepayLog record);

    void updateRepayLogInfo(CLBorrowRepayLog repayLog);

    void deleteRepayLogByRepayId(Long repayId);
}