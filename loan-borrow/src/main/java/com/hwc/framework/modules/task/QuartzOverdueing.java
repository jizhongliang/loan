package com.hwc.framework.modules.task;

import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by   on 2017/11/22.
 */
@Component("Overdueing")
public class QuartzOverdueing implements BorrowJob {
    private static final Logger logger = LoggerFactory.getLogger(QuartzOverdueing.class);
    @Autowired
    private ClBorrowRepayService repayService;


    @Override
    public void execute() {
        repayService.clacOverdue();
    }
}
