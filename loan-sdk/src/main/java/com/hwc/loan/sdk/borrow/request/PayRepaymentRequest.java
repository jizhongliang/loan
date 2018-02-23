package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.PayRepaymentResponse;

public class PayRepaymentRequest extends RequestBase<PayRepaymentResponse> {

    public static final String METHOD = "/api/pay/repayment";

    private Long repayId;
    private String tradePassword;

    public PayRepaymentRequest() {
        super(METHOD);
    }

    public void setRepayId(Long repayId) {
        this.repayId = repayId;
    }

    public Long getRepayId() {
        return this.repayId;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradePassword() {
        return this.tradePassword;
    }

} 