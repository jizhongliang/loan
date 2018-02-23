package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

import java.util.Date;

public class WContactsListRequest extends ItemsRequest {

    /**
     * 手机
     */
    private String phone;

    /**
     * 身份证
     */
    private String idNo;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 类型
     */
    private String cat;
    /**
     * 信息状态
     */
    private String state;
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
