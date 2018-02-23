package com.hwc.framework.modules.domain;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class DUserModel {
    @ApiModelProperty("用户ID")
    private Long   userId;

    @ApiModelProperty("身份证")
    private String idNo;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("验证码")
    private String vCode;

    @ApiModelProperty("交易密码")
    private String tradePwd;

    @ApiModelProperty("上次交易密码修改时间")
    private Date   tradepwdModifyTime;

    @ApiModelProperty("注册手机号码")
    private String loginName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public Date getTradepwdModifyTime() {
        return tradepwdModifyTime;
    }

    public void setTradepwdModifyTime(Date tradepwdModifyTime) {
        this.tradepwdModifyTime = tradepwdModifyTime;
    }

    /**
     * Getter method for property <tt>loginName</tt>.
     * 
     * @return property value of loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Setter method for property <tt>loginName</tt>.
     * 
     * @param loginName value to be assigned to property loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
