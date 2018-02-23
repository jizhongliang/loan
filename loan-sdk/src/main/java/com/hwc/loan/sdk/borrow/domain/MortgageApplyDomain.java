package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class MortgageApplyDomain {

    private Long id;
    private Long userId;
    private String descript;
    private String dyCity;
    private String dyAddress;
    private String dyCommunity;
    private String dyType;
    private BigDecimal dyArea;
    private Date dyBuyTime;
    private BigDecimal dyBuyPrice;
    private BigDecimal borrowAmount;
    private Date expireDate;
    private String name;
    private String mobile;
    private String orderNo;
    private String state;
    private String halt;
    private Date applyDate;
    private Long applyby;
    private BigDecimal realQuota;
    private BigDecimal realRate;
    private Long passBy;
    private Date updated;
    private Date created;
    private String remark;

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

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDyCity(String dyCity) {
        this.dyCity = dyCity;
    }

    public String getDyCity() {
        return this.dyCity;
    }

    public void setDyAddress(String dyAddress) {
        this.dyAddress = dyAddress;
    }

    public String getDyAddress() {
        return this.dyAddress;
    }

    public void setDyCommunity(String dyCommunity) {
        this.dyCommunity = dyCommunity;
    }

    public String getDyCommunity() {
        return this.dyCommunity;
    }

    public void setDyType(String dyType) {
        this.dyType = dyType;
    }

    public String getDyType() {
        return this.dyType;
    }

    public void setDyArea(BigDecimal dyArea) {
        this.dyArea = dyArea;
    }

    public BigDecimal getDyArea() {
        return this.dyArea;
    }

    public void setDyBuyTime(Date dyBuyTime) {
        this.dyBuyTime = dyBuyTime;
    }

    public Date getDyBuyTime() {
        return this.dyBuyTime;
    }

    public void setDyBuyPrice(BigDecimal dyBuyPrice) {
        this.dyBuyPrice = dyBuyPrice;
    }

    public BigDecimal getDyBuyPrice() {
        return this.dyBuyPrice;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public BigDecimal getBorrowAmount() {
        return this.borrowAmount;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setHalt(String halt) {
        this.halt = halt;
    }

    public String getHalt() {
        return this.halt;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getApplyDate() {
        return this.applyDate;
    }

    public void setApplyby(Long applyby) {
        this.applyby = applyby;
    }

    public Long getApplyby() {
        return this.applyby;
    }

    public void setRealQuota(BigDecimal realQuota) {
        this.realQuota = realQuota;
    }

    public BigDecimal getRealQuota() {
        return this.realQuota;
    }

    public void setRealRate(BigDecimal realRate) {
        this.realRate = realRate;
    }

    public BigDecimal getRealRate() {
        return this.realRate;
    }

    public void setPassBy(Long passBy) {
        this.passBy = passBy;
    }

    public Long getPassBy() {
        return this.passBy;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

} 