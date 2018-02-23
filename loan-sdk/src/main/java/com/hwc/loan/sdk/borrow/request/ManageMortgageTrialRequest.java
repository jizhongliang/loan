package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageTrialResponse;

public class ManageMortgageTrialRequest extends RequestBase<ManageMortgageTrialResponse> {

    public static final String METHOD = "/manage/mortgage/trial";

    private Long id;
    private String state;
    private String remark;
    private BigDecimal realQuota;
    private BigDecimal realRate;
    public ManageMortgageTrialRequest() {
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

    public BigDecimal getRealQuota() {return realQuota;}

    public void setRealQuota(BigDecimal realQuota) {this.realQuota = realQuota;}

    public BigDecimal getRealRate() {return realRate;}

    public void setRealRate(BigDecimal realRate) {this.realRate = realRate;}
}