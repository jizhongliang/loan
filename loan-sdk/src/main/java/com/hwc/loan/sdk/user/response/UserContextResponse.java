/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.user.response;

/**
 * 
 * @author jinlilong
 * @version $Id: UserContext.java, v 0.1 2018年1月22日 上午10:11:52 jinlilong Exp $
 */
public class UserContextResponse {

    /**  用户Id*/
    private long   userId;

    /**  微信openId*/
    private String openId;

    /**  访问网关的token*/
    private String token;

    /**  分期分类、10信用分期。20车位分期*/
    private String cat;

    /**  用户姓名*/
    private String name;

    /**  用户手机号码*/
    private String phone;

    /**  身份证号*/
    private String idCard;

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>openId</tt>.
     * 
     * @return property value of openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * Setter method for property <tt>openId</tt>.
     * 
     * @param openId value to be assigned to property openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * Getter method for property <tt>token</tt>.
     * 
     * @return property value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter method for property <tt>token</tt>.
     * 
     * @param token value to be assigned to property token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter method for property <tt>cat</tt>.
     * 
     * @return property value of cat
     */
    public String getCat() {
        return cat;
    }

    /**
     * Setter method for property <tt>cat</tt>.
     * 
     * @param cat value to be assigned to property cat
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>phone</tt>.
     * 
     * @return property value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for property <tt>phone</tt>.
     * 
     * @param phone value to be assigned to property phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter method for property <tt>idCard</tt>.
     * 
     * @return property value of idCard
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * Setter method for property <tt>idCard</tt>.
     * 
     * @param idCard value to be assigned to property idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

}
