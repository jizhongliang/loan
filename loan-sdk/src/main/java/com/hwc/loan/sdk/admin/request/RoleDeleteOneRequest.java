package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.RoleDeleteOneResponse;

public class RoleDeleteOneRequest extends RequestBase<RoleDeleteOneResponse> {

    public static final String METHOD = "/api/admin/sys/role/deleteOne";

    public RoleDeleteOneRequest() {
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
