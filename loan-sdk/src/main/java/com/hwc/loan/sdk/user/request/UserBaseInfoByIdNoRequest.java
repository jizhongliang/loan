/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserBaseInfoByIdNoResponse;

/**
 * 
 * @author jinlilong
 * @version $Id: UserBaseInfoByIdNoRequest.java, v 0.1 2018年1月31日 上午10:17:04 jinlilong Exp $
 */
public class UserBaseInfoByIdNoRequest extends RequestBase<UserBaseInfoByIdNoResponse> {

    /**  */
    private static final long  serialVersionUID = -2540916647147241468L;

    /**  */
    public static final String METHOD           = "/api/user/baseInfo/getByIdNo";

    public UserBaseInfoByIdNoRequest() {
        super(METHOD);
    }

    /**  身份证号*/
    private String idNo;

    /**
     * Getter method for property <tt>idNo</tt>.
     * 
     * @return property value of idNo
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * Setter method for property <tt>idNo</tt>.
     * 
     * @param idNo value to be assigned to property idNo
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}
