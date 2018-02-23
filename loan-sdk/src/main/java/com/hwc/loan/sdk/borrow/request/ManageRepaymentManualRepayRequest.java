package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageRepaymentManualRepayResponse;

public class ManageRepaymentManualRepayRequest extends RequestBase<ManageRepaymentManualRepayResponse> {

    public static final String METHOD = "/manage/repayment/manualRepay";

    private Long id;

    public ManageRepaymentManualRepayRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 