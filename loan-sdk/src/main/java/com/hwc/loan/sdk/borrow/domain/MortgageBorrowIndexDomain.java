package com.hwc.loan.sdk.borrow.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class MortgageBorrowIndexDomain {

    private String page;
    private String alt_message;
    private String state;
    private Double quota;
    private Double total_quota;
    private String rate_descript;
    private boolean pwd;
    private boolean auth;

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return this.page;
    }

    public void setAlt_message(String alt_message) {
        this.alt_message = alt_message;
    }

    public String getAlt_message() {
        return this.alt_message;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getQuota() {
        return this.quota;
    }

    public void setTotal_quota(Double total_quota) {
        this.total_quota = total_quota;
    }

    public Double getTotal_quota() {
        return this.total_quota;
    }

    public void setRate_descript(String rate_descript) {
        this.rate_descript = rate_descript;
    }

    public String getRate_descript() {
        return this.rate_descript;
    }

    public void setPwd(boolean pwd) {
        this.pwd = pwd;
    }

    public boolean getPwd() {
        return this.pwd;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean getAuth() {
        return this.auth;
    }

} 