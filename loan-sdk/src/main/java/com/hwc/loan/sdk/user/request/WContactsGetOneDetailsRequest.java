package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.WContactsGetOneDetailsResponse;


public class WContactsGetOneDetailsRequest extends RequestBase<WContactsGetOneDetailsResponse> {

    public static final String METHOD = "/mana/user/wContacts/getOne";

    public WContactsGetOneDetailsRequest() {
        super(METHOD);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
