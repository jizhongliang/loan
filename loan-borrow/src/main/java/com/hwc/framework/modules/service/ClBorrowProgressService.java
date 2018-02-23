package com.hwc.framework.modules.service;

import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClBorrowProgressService extends Service<ClBorrowProgress> {
    /**
     * 记录订单变化日志
     * @param borrow
     */
    public void borrowProgress(ClBorrow borrow);
}
