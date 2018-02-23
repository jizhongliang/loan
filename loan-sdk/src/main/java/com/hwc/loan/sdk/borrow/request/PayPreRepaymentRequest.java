package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.PayPreRepaymentResponse;

public class PayPreRepaymentRequest extends RequestBase<PayPreRepaymentResponse> {

    public static final String METHOD = "/api/pay/preRepayment";

    private Long borrowId;
    private String tradePassword;

    public PayPreRepaymentRequest() {
        super(METHOD);
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() {
        return this.borrowId;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradePassword() {
        return this.tradePassword;
    }

} 