package com.hwc.framework.modules.model;

import java.math.BigDecimal;

public class ArcCredit {
    private Long id;

    private Long userId;

    private BigDecimal total;

    private Long creditType;

    private String grade;

    private BigDecimal used;

    private BigDecimal unuse;

    private String state;

    private String reqExt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getCreditType() {
        return creditType;
    }

    public void setCreditType(Long creditType) {
        this.creditType = creditType;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public BigDecimal getUsed() {
        return used;
    }

    public void setUsed(BigDecimal used) {
        this.used = used;
    }

    public BigDecimal getUnuse() {
        return unuse;
    }

    public void setUnuse(BigDecimal unuse) {
        this.unuse = unuse;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getReqExt() {
        return reqExt;
    }

    public void setReqExt(String reqExt) {
        this.reqExt = reqExt == null ? null : reqExt.trim();
    }
}