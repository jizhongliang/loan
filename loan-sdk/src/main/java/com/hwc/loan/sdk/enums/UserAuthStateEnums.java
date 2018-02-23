/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.enums;

/**
 * 用户认证状态枚举
 * @author jinlilong
 * @version $Id: UserIDStateEnums.java, v 0.1 2018年2月1日 上午11:45:11 jinlilong Exp $
 */
public enum UserAuthStateEnums {

    /**  */
    ID_STATE_NO_AUTH("10", "身份证未认证"),

    /**  */
    ID_STATE_ALREADY_AUTH("30", "身份证已认证"),

    /**  */
    CONTACT_STATE_NO_AUTH("10", "联系人未认证"),

    /**  */
    CONTACT_STATE_ALREADY_AUTH("30", "联系人已认证"),

    /**  */
    BANK_CARD_STATE_NO_AUTH("10", "银行卡未认证"),

    /**  */
    BANK_CARD_STATE_ALREADY_AUTH("30", "银行卡已认证"),

    /**  */
    PHONE_STATE_NO_AUTH("10", "运营商未认证"),

    /**  */
    PHONE_STATE_ALREADY_AUTH("30", "运营商已认证");

    /**  */
    private String code;

    /**  */
    private String msg;

    UserAuthStateEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     * 
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter method for property <tt>msg</tt>.
     * 
     * @param msg value to be assigned to property msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
