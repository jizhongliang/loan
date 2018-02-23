package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BorrowRepayCalcPrePaymentResponse;

public class BorrowRepayCalcPrePaymentRequest extends RequestBase<BorrowRepayCalcPrePaymentResponse> {

    public static final String METHOD = "/api/borrow/repay/calcPrePayment";

    private Long id;

    public BorrowRepayCalcPrePaymentRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 