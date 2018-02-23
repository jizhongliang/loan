package com.hwc.framework.modules.third;

import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClPayLog;

/**
 * Created by   on 2017/11/23.
 */
public interface BorrowNotifyService {

    /**
     * 新申请 消息通知
     *
     * @param borrow
     */
      void applyBorrowNotify(ClBorrow borrow) ;

    /**
     * 申请被拒绝消息通知
     *
     * @param borrow
     */
      void refuseBorrowNotify(ClBorrow borrow);

    /**
     * 审核通过
     *
     * @param borrow
     */
      void borrowPassNotify(ClBorrow borrow) ;

    /**
     * 逾期通知
     *
     * @param repay
     */
      void borrowOverdueNotify(ClBorrowRepay repay) ;

    /**
     * 快到期提醒
     *
     * @param repay
     */
      void repayExpireNotify(ClBorrowRepay repay);
      /** 
       * 放款成功提醒
       * @param borrow
       */
	void paySuccessNotify(ClBorrow borrow);
	/** 
	 * 还款成功提醒
	 * @param clBorrowRepay
	 */
	void repaySuccess(ClBorrowRepay clBorrowRepay);


}
