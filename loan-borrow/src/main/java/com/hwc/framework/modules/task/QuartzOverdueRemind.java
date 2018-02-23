package com.hwc.framework.modules.task;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.impl.ArcCreditServiceImpl;
import com.hwc.framework.modules.third.BorrowNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by   on 2017/11/29.
 * 块逾期提醒
 */
@Component("OverdueRemind")
public class QuartzOverdueRemind implements BorrowJob {
    private static final Logger logger = LoggerFactory.getLogger(QuartzOverdueRemind.class);
    @Autowired
    private ClBorrowRepayService repayService;

    @Autowired
    private BorrowNotifyService notifyService;

    @Override
    public void execute() {
        List<ClBorrowRepay> repays = repayService.getSoonExpireRepayPlan(2);
        if (FsUtils.strsEmpty(repays)) {
            return;
        }
        for (ClBorrowRepay repay : repays) {
            notifyService.repayExpireNotify(repay);
        }
    }
}
