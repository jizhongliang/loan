package com.hwc.framework.modules.domain;

import lombok.Data;

@Data
public class DUserAuthModel extends DUserAuth{
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户类型
     */
    private String cat;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}