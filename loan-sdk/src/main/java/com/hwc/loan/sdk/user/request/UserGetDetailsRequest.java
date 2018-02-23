package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.SysUserGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserGetDetailsRequest extends RequestBase<SysUserGetOneResponse> {
    public static final String METHOD = "/api/cl/user/base/info/getDetails";

    public UserGetDetailsRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
