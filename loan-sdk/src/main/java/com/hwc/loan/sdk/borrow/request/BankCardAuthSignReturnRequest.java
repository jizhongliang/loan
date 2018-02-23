package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BankCardAuthSignReturnResponse;

public class BankCardAuthSignReturnRequest extends RequestBase<BankCardAuthSignReturnResponse> {

    public static final String METHOD = "/api/bank/card/authSignReturn";

    private String card_no;
    private String bank_name;
    private String bank_code;
    private String id_holder;
    private String id_no;
    private String mobile;
    private String sms_code;
    private String service_name;
    private String ip;
    private Long borrow_id;
    private Long user_id;
    private String order_no;

    public BankCardAuthSignReturnRequest() {
        super(METHOD);
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_no() {
        return this.card_no;
    }

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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getSms_code() {
        return this.sms_code;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_name() {
        return this.service_name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
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

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOrder_no() {
        return this.order_no;
    }

} 