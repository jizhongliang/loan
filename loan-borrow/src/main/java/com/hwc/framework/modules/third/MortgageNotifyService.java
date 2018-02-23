package com.hwc.framework.modules.third;

import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClMortgage;

/**
 * Created by   on 2017/11/24.
 */
public interface MortgageNotifyService {
    /**
     * 抵押申请
     * @param mortgage
     */
    void mortgageApplyNotify(ClMortgage mortgage);
    /**
     * 通过初审通知
     *
     * @param mortgage
     */
    void mortgageTrailNotify(ClMortgage mortgage);

    /**
     * 申请被拒绝通知
     *
     * @param mortgage
     */
    void mortgageRefuseNotify(ClMortgage mortgage);

    /**
     * 复审通过消息通知
     *
     * @param mortgage
     */
    void mortgageReviewNotify(ClMortgage mortgage);

    /**
     * 最终审核通过消息通知
     *
     * @param mortgage
     */
    void mortgageAuthNotify(ClMortgage mortgage);

    /**
     * 抵押 提现 成功通知
     */
      void mortgageWithdrawalsNotify(ClBorrow borrow);
}
