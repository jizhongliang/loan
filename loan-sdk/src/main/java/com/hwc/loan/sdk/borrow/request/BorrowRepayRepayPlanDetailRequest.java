package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BorrowRepayRepayPlanDetailResponse;

public class BorrowRepayRepayPlanDetailRequest extends RequestBase<BorrowRepayRepayPlanDetailResponse> {

    public static final String METHOD = "/api/borrow/repay/repayPlanDetail";

    private Long id;

    public BorrowRepayRepayPlanDetailRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 