package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.MortgageIndex;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.mybatis.core.Service;

import java.util.List;

/**
 * 2017/10/23.
 */
public interface ClMortgageBorrowService extends Service<ClBorrow> {
     Response borrow(BorrowBean bean);

    /**
     * 获取抵押首页
     * @return
     */
    Response getBanner();

    public Response<MortgageIndex> homePage(Long userId);

    List<Double> getQuotaRang(Long userId);
}
