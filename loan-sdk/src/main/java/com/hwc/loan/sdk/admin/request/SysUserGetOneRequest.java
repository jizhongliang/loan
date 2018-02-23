package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.SysUserGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class SysUserGetOneRequest extends RequestBase<SysUserGetOneResponse> {
    public static final String METHOD = "/api/admin/sys/user/getOne";

    public SysUserGetOneRequest() {
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
