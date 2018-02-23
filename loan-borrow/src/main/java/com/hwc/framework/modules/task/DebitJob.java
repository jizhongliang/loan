package com.hwc.framework.modules.task;

import com.hwc.framework.modules.service.ClBorrowRepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2017/12/5.
 */
@Component("debitJob")
public class DebitJob implements BorrowJob {

    @Autowired
    private ClBorrowRepayService repayService;

    @Override
    public void execute() {
        repayService.batchDebitRepay();
    }
}
