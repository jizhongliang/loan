package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClCreditBorrowService extends Service<ClBorrow> {
    Response borrow(BorrowBean bean);

    /**
     * 借款首页
     * @param userId
     * @return
     */
    BorrowBean getIndex(Long userId);

    /**
     * 审核信用类订单
     * @param bean
     * @param sysUserId
     */
      Response auditBorrow(BorrowAuditBean bean, Long sysUserId);
}
