package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.MortgageBorrowQuotaRangResponse;

public class MortgageBorrowQuotaRangRequest extends RequestBase<MortgageBorrowQuotaRangResponse> {

    public static final String METHOD = "/api/mortgage/borrow/quotaRang";

    private Long id;

    public MortgageBorrowQuotaRangRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 