package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.CLBorrowThird;

public interface CLBorrowThirdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLBorrowThird record);

    int insertSelective(CLBorrowThird record);

    CLBorrowThird selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLBorrowThird record);

    int updateByPrimaryKey(CLBorrowThird record);

    CLBorrowThird getBorrowThirdByOrderNo(String orderNo);

    CLBorrowThird findOneByBorrowId(Long borrowId);
}