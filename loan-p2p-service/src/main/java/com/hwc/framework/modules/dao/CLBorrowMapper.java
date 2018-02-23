package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.CLBorrow;

public interface CLBorrowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLBorrow record);

    int insertSelective(CLBorrow record);

    CLBorrow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLBorrow record);

    int updateByPrimaryKey(CLBorrow record);

    CLBorrow findBorrowByOrderNo(String orderNo);

    void updateBorrowForNotify(CLBorrow borrow);

    void updateBorrowToRepaySuccess(Long id);
}