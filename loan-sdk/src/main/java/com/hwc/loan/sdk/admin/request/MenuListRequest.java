package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.MenuListResponse;

public class MenuListRequest extends RequestBase<MenuListResponse> {
    public static final String METHOD = "/api/admin/sys/menu/list";

    public MenuListRequest() {
        super(METHOD);
    }
}
