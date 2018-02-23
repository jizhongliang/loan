package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageReviewAuthResponse;

public class ManageMortgageReviewAuthRequest extends RequestBase<ManageMortgageReviewAuthResponse> {

    public static final String METHOD = "/manage/mortgage/reviewAuth";

    private Long id;
    private String state;
    private String remark;

    public ManageMortgageReviewAuthRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

} 