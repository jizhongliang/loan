package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageRepaylogCreditListResponse;

public class ManageRepaylogCreditListRequest extends RequestBase<ManageRepaylogCreditListResponse> {

    public static final String METHOD = "/manage/repaylog/credit/list";

    private String name;
    private String state;
    private String mobile;
    private Date start;
    private Date end;
    private String orderNo;
    private String repayWay;
    private String account;
    private String borrow_type;
    private int page;
    private int pageSize;

    public ManageRepaylogCreditListRequest() {
        super(METHOD);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return this.start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setRepayWay(String repayWay) {
        this.repayWay = repayWay;
    }

    public String getRepayWay() {
        return this.repayWay;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    public void setBorrow_type(String borrow_type) {
        this.borrow_type = borrow_type;
    }

    public String getBorrow_type() {
        return this.borrow_type;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

} 