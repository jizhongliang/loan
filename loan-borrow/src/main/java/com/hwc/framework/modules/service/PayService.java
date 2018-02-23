package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.base.api.ResponsePage;
import com.hwc.framework.modules.model.ClBorrow;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by   on 2017/11/1.
 */
public interface PayService {
    Response manualLoan(Long borrowId);

    /**
     * 放款
     *
     * @param borrow
     */
    Response loan(ClBorrow borrow);


    /**
     * 代扣 回调
     *
     * @param request
     */
    String loanCallback(HttpServletRequest request);

    Response queryPayMent(String orderNo);

    Response loanQueryCallback(List<String> orders);

}
