package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class BorrowRepayRepayPlanListDomain {

    private Double amount;
    private Date borrow_date;
    private Double total_amount;
    private Double first_repay_amount;
    private Date first_repay_date;
    private Long first_repay_id;
    private Double interest;
    private Date end_repay_date;
    private Double rate;
    private int periods;
    private int hasRepay_periods;
    private int unRepay_periods;
    private Double unRepay_amount;
    private String bankCardNo;
    private Double current_repay_amount;
    private Date current_repay_date;
    private Long current_repay_id;
    private Long borrowId;
    private List plans;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getBorrow_date() {
        return this.borrow_date;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getTotal_amount() {
        return this.total_amount;
    }

    public void setFirst_repay_amount(Double first_repay_amount) {
        this.first_repay_amount = first_repay_amount;
    }

    public Double getFirst_repay_amount() {
        return this.first_repay_amount;
    }

    public void setFirst_repay_date(Date first_repay_date) {
        this.first_repay_date = first_repay_date;
    }

    public Date getFirst_repay_date() {
        return this.first_repay_date;
    }

    public void setFirst_repay_id(Long first_repay_id) {
        this.first_repay_id = first_repay_id;
    }

    public Long getFirst_repay_id() {
        return this.first_repay_id;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInterest() {
        return this.interest;
    }

    public void setEnd_repay_date(Date end_repay_date) {
        this.end_repay_date = end_repay_date;
    }

    public Date getEnd_repay_date() {
        return this.end_repay_date;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getPeriods() {
        return this.periods;
    }

    public void setHasRepay_periods(int hasRepay_periods) {
        this.hasRepay_periods = hasRepay_periods;
    }

    public int getHasRepay_periods() {
        return this.hasRepay_periods;
    }

    public void setUnRepay_periods(int unRepay_periods) {
        this.unRepay_periods = unRepay_periods;
    }

    public int getUnRepay_periods() {
        return this.unRepay_periods;
    }

    public void setUnRepay_amount(Double unRepay_amount) {
        this.unRepay_amount = unRepay_amount;
    }

    public Double getUnRepay_amount() {
        return this.unRepay_amount;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo() {
        return this.bankCardNo;
    }

    public void setCurrent_repay_amount(Double current_repay_amount) {
        this.current_repay_amount = current_repay_amount;
    }

    public Double getCurrent_repay_amount() {
        return this.current_repay_amount;
    }

    public void setCurrent_repay_date(Date current_repay_date) {
        this.current_repay_date = current_repay_date;
    }

    public Date getCurrent_repay_date() {
        return this.current_repay_date;
    }

    public void setCurrent_repay_id(Long current_repay_id) {
        this.current_repay_id = current_repay_id;
    }

    public Long getCurrent_repay_id() {
        return this.current_repay_id;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() {
        return this.borrowId;
    }

    public void setPlans(List plans) {
        this.plans = plans;
    }

    public List getPlans() {
        return this.plans;
    }

} 