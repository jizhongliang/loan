package com.hwc.framework.modules.domain;

import lombok.Data;

public class ResResult {

    private String mobile;
    private String boUserName;
    private Integer status;
    private String remark;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBoUserName() {
        return boUserName;
    }

    public void setBoUserName(String boUserName) {
        this.boUserName = boUserName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
