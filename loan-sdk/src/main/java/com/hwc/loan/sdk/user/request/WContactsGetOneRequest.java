package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.WContactsGetOneResponse;


public class WContactsGetOneRequest extends RequestBase<WContactsGetOneResponse> {

    public static final String METHOD = "/api/user/wContacts/getOne";

    public WContactsGetOneRequest() {
        super(METHOD);
    }
    /**
     * 手机号码
     */
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
