package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserUpdateStateResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserUpdateStateRequest extends RequestBase<UserUpdateStateResponse> {
    public static final String METHOD = "/mana/user/updateState";

    public UserUpdateStateRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private Long id;

    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
