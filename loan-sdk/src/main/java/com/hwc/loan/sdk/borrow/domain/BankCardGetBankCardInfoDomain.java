package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class BankCardGetBankCardInfoDomain {

    private Long id;
    private Long userId;
    private String bank;
    private String cardNo;
    private String phone;
    private String agreeNo;
    private Date bindTime;
    private String id_holder;
    private String id_no;
    private String signResult;
    private String bankCode;
    private String channel;

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

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return this.bank;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setAgreeNo(String agreeNo) {
        this.agreeNo = agreeNo;
    }

    public String getAgreeNo() {
        return this.agreeNo;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getBindTime() {
        return this.bindTime;
    }

    public void setId_holder(String id_holder) {
        this.id_holder = id_holder;
    }

    public String getId_holder() {
        return this.id_holder;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getId_no() {
        return this.id_no;
    }

    public void setSignResult(String signResult) {
        this.signResult = signResult;
    }

    public String getSignResult() {
        return this.signResult;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return this.channel;
    }

} 