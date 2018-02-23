/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.AuthenticationCwResponse;

/**
 * 
 * @author jinlilong
 * @version $Id: AuthenticationCwRequest.java, v 0.1 2018年1月18日 上午1:43:32 jinlilong Exp $
 */
public class AuthenticationCwRequest extends RequestBase<AuthenticationCwResponse> {

    /**  */
    private static final long  serialVersionUID = 8351190445014926343L;

    public static final String METHOD           = "/api/user/auth/authenticationCw";

    public AuthenticationCwRequest() {
        super(METHOD);
    }

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
