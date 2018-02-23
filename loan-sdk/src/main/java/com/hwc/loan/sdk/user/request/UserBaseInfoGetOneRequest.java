package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserBaseInfoGetOneRequest extends RequestBase<UserBaseInfoGetOneResponse> {
    public static final String METHOD = "/api/user/baseInfo/getOne";

    public UserBaseInfoGetOneRequest() {
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
