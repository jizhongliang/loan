package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserGetOneDetailsResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserGetOneDetailsRequest extends RequestBase<UserGetOneDetailsResponse> {
    public static final String METHOD = "/mana/user/getOneDetail";

    public UserGetOneDetailsRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
