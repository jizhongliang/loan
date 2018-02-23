package com.hwc.framework.modules.service;

import com.hwc.framework.modules.model.CLBorrowThird;

/**
 * Created by jzl on 2017/12/26.
 */
public interface BorrowThirdService {
    /**
     * 新增一条记录
     * @param borrowThird
     */
    void insertOne(CLBorrowThird borrowThird);

    CLBorrowThird getBorrowThirdByOrderNo(String orderNo);

    CLBorrowThird findOneByBorrowId(Long borrowId);
}
