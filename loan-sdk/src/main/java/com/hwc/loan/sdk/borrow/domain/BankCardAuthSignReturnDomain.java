package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class BankCardAuthSignReturnDomain {

    private String bank_name;
    private String bank_code;
    private String bank_card_no;
    private String pay_channel;
    private String agree_no;
    private String order_no;
    private String state;

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_code() {
        return this.bank_code;
    }

    public void setBank_card_no(String bank_card_no) {
        this.bank_card_no = bank_card_no;
    }

    public String getBank_card_no() {
        return this.bank_card_no;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getPay_channel() {
        return this.pay_channel;
    }

    public void setAgree_no(String agree_no) {
        this.agree_no = agree_no;
    }

    public String getAgree_no() {
        return this.agree_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOrder_no() {
        return this.order_no;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

} 