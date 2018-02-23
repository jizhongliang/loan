package com.hwc.framework.modules.third.Impl;

import com.alibaba.fastjson.JSON;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.config.BorrowConsumerConfig;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.service.BorrowService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import com.hwc.loan.sdk.borrow.domain.DBorrowBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by   on 2017/11/23.
 */
@Service
public class BorrowNotifyServiceImpl implements BorrowNotifyService {
    @Autowired
    private HwcOnsProducer producer;

    @Autowired
    private BorrowConsumerConfig config;

    @Autowired
    private BorrowService borrowService;


    /**
     * 逾期通知
     * repay_borrow_overdue
     *
     * @param repay
     */
    public void borrowOverdueNotify(CLBorrowRepay repay) {
        CLBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
        Properties properties = new Properties();
        properties.setProperty("borrow", JSON.toJSONString(borrow));
        producer.sendJson(config.getBorrowOverdueTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties, repay);
    }

    /**
     * 快到期提醒
     * repay_borrow_expire
     *
     * @param repay
     */
    public void repayExpireNotify(CLBorrowRepay repay) {
        CLBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
        Properties properties = new Properties();
        properties.setProperty("borrow", JSON.toJSONString(borrow));
        producer.sendJson(config.getRepayExpireTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties, repay);
    }
}
