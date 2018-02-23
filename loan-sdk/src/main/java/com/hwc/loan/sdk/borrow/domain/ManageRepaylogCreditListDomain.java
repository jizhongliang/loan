package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class ManageRepaylogCreditListDomain {

    private String name;
    private Long borrowId;
    private Long userId;
    private Long repayId;
    private String mobile;
    private String orderNo;
    private Double borrowAmount;
    private Double repayAmount;
    private int periods;
    private int repay_period;
    private Double interest;
    private Double penaltyAmout;
    private Integer penaltyDay;
    private Double repayTotalAmount;
    private Double hasPayAmount;
    private String payAccount;
    private String repayWay;
    private String repayAccount;
    private String serialNumber;
    private Date repayTime;
    private Date payTime;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() {
        return this.borrowId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setRepayId(Long repayId) {
        this.repayId = repayId;
    }

    public Long getRepayId() {
        return this.repayId;
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

    public void setBorrowAmount(Double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public Double getBorrowAmount() {
        return this.borrowAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Double getRepayAmount() {
        return this.repayAmount;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getPeriods() {
        return this.periods;
    }

    public void setRepay_period(int repay_period) {
        this.repay_period = repay_period;
    }

    public int getRepay_period() {
        return this.repay_period;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInterest() {
        return this.interest;
    }

    public void setPenaltyAmout(Double penaltyAmout) {
        this.penaltyAmout = penaltyAmout;
    }

    public Double getPenaltyAmout() {
        return this.penaltyAmout;
    }

    public void setPenaltyDay(Integer penaltyDay) {
        this.penaltyDay = penaltyDay;
    }

    public Integer getPenaltyDay() {
        return this.penaltyDay;
    }

    public void setRepayTotalAmount(Double repayTotalAmount) {
        this.repayTotalAmount = repayTotalAmount;
    }

    public Double getRepayTotalAmount() {
        return this.repayTotalAmount;
    }

    public void setHasPayAmount(Double hasPayAmount) {
        this.hasPayAmount = hasPayAmount;
    }

    public Double getHasPayAmount() {
        return this.hasPayAmount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPayAccount() {
        return this.payAccount;
    }

    public void setRepayWay(String repayWay) {
        this.repayWay = repayWay;
    }

    public String getRepayWay() {
        return this.repayWay;
    }

    public void setRepayAccount(String repayAccount) {
        this.repayAccount = repayAccount;
    }

    public String getRepayAccount() {
        return this.repayAccount;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Date getRepayTime() {
        return this.repayTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayTime() {
        return this.payTime;
    }

} 