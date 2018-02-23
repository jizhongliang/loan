package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.CreditGenQuotaResponse;

public class CreditGenQuotaRequest extends RequestBase<CreditGenQuotaResponse> {

    public static final String METHOD = "/api/credit/genQuota";

    private Long id;
    private Long userId;
    private BigDecimal total;
    private Long creditType;
    private String grade;
    private BigDecimal used;
    private BigDecimal unuse;
    private String state;

    public CreditGenQuotaRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setCreditType(Long creditType) {
        this.creditType = creditType;
    }

    public Long getCreditType() {
        return this.creditType;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setUsed(BigDecimal used) {
        this.used = used;
    }

    public BigDecimal getUsed() {
        return this.used;
    }

    public void setUnuse(BigDecimal unuse) {
        this.unuse = unuse;
    }

    public BigDecimal getUnuse() {
        return this.unuse;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

} 