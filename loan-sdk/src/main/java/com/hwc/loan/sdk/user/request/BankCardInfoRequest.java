package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.BankCardInfoResponse;

/**
 * Created by jzl on 2018/1/17.
 */
public class BankCardInfoRequest extends RequestBase<BankCardInfoResponse> {
    public static final String METHOD = "/api/bank/card/getBankCardInfo";

    public BankCardInfoRequest() {
        super(METHOD);
    }
    /**
     * 用户id
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
