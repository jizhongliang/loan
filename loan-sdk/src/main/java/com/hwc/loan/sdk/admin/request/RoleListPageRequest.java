package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.admin.response.RoleListPageResponse;

public class RoleListPageRequest extends ItemsRequest<RoleListPageResponse> {

    public static final String METHOD = "/api/admin/sys/role/listPage";

    public RoleListPageRequest() {
        super(METHOD);
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
