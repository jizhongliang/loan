package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;

/**
 * 2017/10/31.
 */
public interface ClBorrowRepayLogService extends Service<ClBorrowRepayLog> {
    void doSave(ClBorrowRepay repay, Map<String, Object> param);

    /**
     * app 上呈现的 还款记录
     *
     * @param userId
     * @return
     */
    List<PayLogBean> getPayLogList(Long userId);

    Response getPayLogList(RepayQueryRequest request);
}
