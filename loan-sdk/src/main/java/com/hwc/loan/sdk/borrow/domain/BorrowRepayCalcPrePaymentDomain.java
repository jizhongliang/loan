package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class BorrowRepayCalcPrePaymentDomain {

    private Double amount;
    private Double Interest;
    private Double total;
    private String bank_card_no;
    private String bank_card_name;
    private Long borrow_id;
    private Long user_id;
    private Double rate;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setInterest(Double Interest) {
        this.Interest = Interest;
    }

    public Double getInterest() {
        return this.Interest;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setBank_card_no(String bank_card_no) {
        this.bank_card_no = bank_card_no;
    }

    public String getBank_card_no() {
        return this.bank_card_no;
    }

    public void setBank_card_name(String bank_card_name) {
        this.bank_card_name = bank_card_name;
    }

    public String getBank_card_name() {
        return this.bank_card_name;
    }

    public void setBorrow_id(Long borrow_id) {
        this.borrow_id = borrow_id;
    }

    public Long getBorrow_id() {
        return this.borrow_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }

} 