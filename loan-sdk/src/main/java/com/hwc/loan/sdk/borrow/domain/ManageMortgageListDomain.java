package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class ManageMortgageListDomain {

    private Long id;
    private Long userId;
    private String orderNo;
    private String userName;
    private String mobile;
    private String descript;
    private String dyCity;
    private String dyAddress;
    private String dyCommunity;
    private String dyType;
    private Double dyArea;
    private String dyBuyYear;
    private Double dyBuyPrice;
    private Double newPrice;
    private Double borrowAmount;
    private String status;
    private Date applyDate;
    private Double realQuota;
    private Double realRate;
    private List userImg;
    private List assetsImg;
    private List otherImg;
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

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
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

    public void setDyArea(Double dyArea) {
        this.dyArea = dyArea;
    }

    public Double getDyArea() {
        return this.dyArea;
    }

    public void setDyBuyYear(String dyBuyYear) {
        this.dyBuyYear = dyBuyYear;
    }

    public String getDyBuyYear() {
        return this.dyBuyYear;
    }

    public void setDyBuyPrice(Double dyBuyPrice) {
        this.dyBuyPrice = dyBuyPrice;
    }

    public Double getDyBuyPrice() {
        return this.dyBuyPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Double getNewPrice() {
        return this.newPrice;
    }

    public void setBorrowAmount(Double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public Double getBorrowAmount() {
        return this.borrowAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getApplyDate() {
        return this.applyDate;
    }

    public void setRealQuota(Double realQuota) {
        this.realQuota = realQuota;
    }

    public Double getRealQuota() {
        return this.realQuota;
    }

    public void setRealRate(Double realRate) {
        this.realRate = realRate;
    }

    public Double getRealRate() {
        return this.realRate;
    }

    public void setUserImg(List userImg) {
        this.userImg = userImg;
    }

    public List getUserImg() {
        return this.userImg;
    }

    public void setAssetsImg(List assetsImg) {
        this.assetsImg = assetsImg;
    }

    public List getAssetsImg() {
        return this.assetsImg;
    }

    public void setOtherImg(List otherImg) {
        this.otherImg = otherImg;
    }

    public List getOtherImg() {
        return this.otherImg;
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