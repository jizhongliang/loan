package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.RoleSetAuthorityResponse;

public class RoleSetAuthorityRequest extends RequestBase<RoleSetAuthorityResponse> {
    public static final String METHOD = "/api/admin/sys/role/setAuthority";

    public RoleSetAuthorityRequest() {
        super(METHOD);
    }

    private Integer roleId;

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
