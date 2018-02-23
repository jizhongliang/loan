package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.CLBorrowRepayThird;

public interface CLBorrowRepayThirdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLBorrowRepayThird record);

    int insertSelective(CLBorrowRepayThird record);

    CLBorrowRepayThird selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLBorrowRepayThird record);

    int updateByPrimaryKey(CLBorrowRepayThird record);

    void updateBorrowRepayThirdInfo(CLBorrowRepayThird repayThird);

    CLBorrowRepayThird findOneByBorrowRepayId(Long repayId);

    void deleteRepayThirdByRepayId(Long repayId);
}