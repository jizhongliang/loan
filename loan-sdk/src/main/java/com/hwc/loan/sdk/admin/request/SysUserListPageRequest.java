package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.admin.response.SysUserListPageResponse;

public class SysUserListPageRequest extends ItemsRequest<SysUserListPageResponse> {

    public static final String METHOD = "/api/admin/sys/user/listPage";

    public SysUserListPageRequest() {
        super(METHOD);
    }

}
