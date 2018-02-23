package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BankCardGetAuthDataResponse;

public class BankCardGetAuthDataRequest extends RequestBase<BankCardGetAuthDataResponse> {

    public static final String METHOD = "/api/bank/card/getAuthData";

    private Long userId;
    private String bank;
    private String cardNo;
    private String bankCode;

    public BankCardGetAuthDataRequest() {
        super(METHOD);
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

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return this.bankCode;
    }

} 