package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageRepaymentDetailResponse;

public class ManageRepaymentDetailRequest extends RequestBase<ManageRepaymentDetailResponse> {

    public static final String METHOD = "/manage/repayment/detail";

    private Long id;

    public ManageRepaymentDetailRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 