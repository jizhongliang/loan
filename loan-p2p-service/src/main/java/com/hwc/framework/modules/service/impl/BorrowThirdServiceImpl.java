package com.hwc.framework.modules.service.impl;

import com.hwc.framework.modules.dao.CLBorrowThirdMapper;
import com.hwc.framework.modules.model.CLBorrowThird;
import com.hwc.framework.modules.service.BorrowThirdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jzl on 2017/12/26.
 */
@Service
public class BorrowThirdServiceImpl implements BorrowThirdService {
    private static Logger logger = LoggerFactory.getLogger(BorrowThirdServiceImpl.class);
    @Autowired
    private CLBorrowThirdMapper borrowThirdMapper;

    /**
     * 新增
     * @param borrowThird
     */
    @Override
    public void insertOne(CLBorrowThird borrowThird) {
        borrowThirdMapper.insertSelective(borrowThird);
    }

    @Override
    public CLBorrowThird getBorrowThirdByOrderNo(String orderNo) {
        return borrowThirdMapper.getBorrowThirdByOrderNo(orderNo);
    }

    @Override
    public CLBorrowThird findOneByBorrowId(Long borrowId) {
        return borrowThirdMapper.findOneByBorrowId(borrowId);
    }
}
