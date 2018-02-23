package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.EarlyRepayBean;
import com.hwc.framework.modules.domain.LianlianResponse;
import com.hwc.framework.modules.model.ClBorrowRepay;

/**
 * Created by   on 2017/11/1.
 */
public interface ChargeService {
    /**
     * 扣款
     * @param model
     */
    Response repayment(ClBorrowRepay model);

    /**
     * 扣款回调处理
     * @param resp
     * @return
     */
    LianlianResponse repepaymentCallback(String resp);

    /**
     * 主动还款
     *
     * @param model
     * @return
     */
    public Response repayment(DebitBean model);

    /**
     * 提前还款
     * @param borrow_id
     * @return
     */
    Response preRepayment(EarlyRepayBean borrow_id);

	Response baofuRepayment(DebitBean request);
}
