package com.hwc.framework.modules.model;

import java.math.BigDecimal;
import java.util.Date;

public class CLBorrowRepayThird {
    private Long id;

    private String orderNo;

    private Long borrowRepayId;

    private String thirdRepayNo;

    private BigDecimal repayAmount;

    private Integer status;

    private Date createTime;

    private Date updateTime;
    private BigDecimal interest;
    private BigDecimal penaltyAmout;
    private BigDecimal principleAmount;

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPenaltyAmout() {
        return penaltyAmout;
    }

    public void setPenaltyAmout(BigDecimal penaltyAmout) {
        this.penaltyAmout = penaltyAmout;
    }

    public BigDecimal getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(BigDecimal principleAmount) {
        this.principleAmount = principleAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getBorrowRepayId() {
        return borrowRepayId;
    }

    public void setBorrowRepayId(Long borrowRepayId) {
        this.borrowRepayId = borrowRepayId;
    }

    public String getThirdRepayNo() {
        return thirdRepayNo;
    }

    public void setThirdRepayNo(String thirdRepayNo) {
        this.thirdRepayNo = thirdRepayNo == null ? null : thirdRepayNo.trim();
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}