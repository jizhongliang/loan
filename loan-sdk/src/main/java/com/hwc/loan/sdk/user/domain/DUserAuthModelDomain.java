package com.hwc.loan.sdk.user.domain;

import lombok.Data;

@Data
public class DUserAuthModelDomain{
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
     * 主键
     */
    private Long id;

    /**
     * 用户标识
     */
    private Long userId;

    /**
     * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String idState;

    /**
     * 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String contactState;

    /**
     * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String bankCardState;

    /**
     * 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String workInfoState;

    /**
     * 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String otherInfoState;

    /**
     * 人行征信，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String creditState;

    private String cat;

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