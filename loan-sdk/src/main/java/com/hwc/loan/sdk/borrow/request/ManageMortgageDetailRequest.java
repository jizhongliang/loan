package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageDetailResponse;

public class ManageMortgageDetailRequest extends RequestBase<ManageMortgageDetailResponse> {

    public static final String METHOD = "/manage/mortgage/detail";

    private Long id;

    public ManageMortgageDetailRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 