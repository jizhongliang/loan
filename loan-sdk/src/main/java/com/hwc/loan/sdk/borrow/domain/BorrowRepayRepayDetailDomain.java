package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class BorrowRepayRepayDetailDomain {

    private Long id;
    private Long userId;
    private Long borrowId;
    private Integer seq;
    private Double realAmount;
    private Double interest;
    private Double rate;
    private Double amount;
    private Double realAmountBalance;
    private Double repayAmount;
    private Double penaltyAmout;
    private Integer penaltyDay;
    private String state;
    private Date repayDate;
    private String name;
    private String orderNo;
    private String mobile;
    private String cardno;

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

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() {
        return this.borrowId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getRealAmount() {
        return this.realAmount;
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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setRealAmountBalance(Double realAmountBalance) {
        this.realAmountBalance = realAmountBalance;
    }

    public Double getRealAmountBalance() {
        return this.realAmountBalance;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Double getRepayAmount() {
        return this.repayAmount;
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

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public Date getRepayDate() {
        return this.repayDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardno() {
        return this.cardno;
    }

} 