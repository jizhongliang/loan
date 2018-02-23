package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageBorrowRepayListResponse;

public class ManageMortgageBorrowRepayListRequest extends RequestBase<ManageMortgageBorrowRepayListResponse> {

    public static final String METHOD = "/manage/mortgage/borrow/repayList";

    private String mobile;
    private String orderNo;
    private String state;
    private String name;
    private Date start;
    private Date end;
    private String type;
    private Long userId;
    private int page;
    private int pageSize;

    public ManageMortgageBorrowRepayListRequest() {
        super(METHOD);
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

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
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