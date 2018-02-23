package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class ManageCreditBorrowListbyOneUserDomain {

    private Long id;
    private Long userId;
    private String name;
    private String mobile;
    private String orderNo;
    private String borrowType;
    private Long mid;
    private int periods;
    private Double total_amount;
    private Double amount;
    private Double realAmount;
    private Double fee;
    private Date createTime;
    private int timeLimit;
    private String state;
    private String isnotify;
    private Long cardId;
    private Double serviceFee;
    private Double flowFee;
    private Double infoAuthFee;
    private Double interest;
    private Double rate;
    private String borrow_rate_descript;
    private String client;
    private String address;
    private String coordinate;
    private String remark;
    private String ip;
    private boolean isRepay;
    private boolean isCanBorrow;
    private String cardno;
    private String overdue_hint;
    private boolean auth;
    private boolean pwd;
    private String tradePassword;

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

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public String getBorrowType() {
        return this.borrowType;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getMid() {
        return this.mid;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getPeriods() {
        return this.periods;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getTotal_amount() {
        return this.total_amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getRealAmount() {
        return this.realAmount;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getFee() {
        return this.fee;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getTimeLimit() {
        return this.timeLimit;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setIsnotify(String isnotify) {
        this.isnotify = isnotify;
    }

    public String getIsnotify() {
        return this.isnotify;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardId() {
        return this.cardId;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getServiceFee() {
        return this.serviceFee;
    }

    public void setFlowFee(Double flowFee) {
        this.flowFee = flowFee;
    }

    public Double getFlowFee() {
        return this.flowFee;
    }

    public void setInfoAuthFee(Double infoAuthFee) {
        this.infoAuthFee = infoAuthFee;
    }

    public Double getInfoAuthFee() {
        return this.infoAuthFee;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInterest() {
        return this.interest;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setBorrow_rate_descript(String borrow_rate_descript) {
        this.borrow_rate_descript = borrow_rate_descript;
    }

    public String getBorrow_rate_descript() {
        return this.borrow_rate_descript;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient() {
        return this.client;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return this.coordinate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIsRepay(boolean isRepay) {
        this.isRepay = isRepay;
    }

    public boolean getIsRepay() {
        return this.isRepay;
    }

    public void setIsCanBorrow(boolean isCanBorrow) {
        this.isCanBorrow = isCanBorrow;
    }

    public boolean getIsCanBorrow() {
        return this.isCanBorrow;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardno() {
        return this.cardno;
    }

    public void setOverdue_hint(String overdue_hint) {
        this.overdue_hint = overdue_hint;
    }

    public String getOverdue_hint() {
        return this.overdue_hint;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean getAuth() {
        return this.auth;
    }

    public void setPwd(boolean pwd) {
        this.pwd = pwd;
    }

    public boolean getPwd() {
        return this.pwd;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradePassword() {
        return this.tradePassword;
    }

} 