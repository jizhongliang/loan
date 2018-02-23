package com.hwc.framework.modules.third;

import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;

/**
 * Created by   on 2017/11/23.
 */
public interface BorrowNotifyService {

    /**
     * 逾期通知
     *
     * @param repay
     */
      void borrowOverdueNotify(CLBorrowRepay repay) ;

    /**
     * 快到期提醒
     *
     * @param repay
     */
      void repayExpireNotify(CLBorrowRepay repay);


}
