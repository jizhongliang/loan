package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.MenuListByRoleResponse;

/**
 * Created by  on 2017/10/9.
 */
public class MenuListByRoleRequest extends RequestBase<MenuListByRoleResponse> {
    public static final String METHOD = "/api/admin/sys/menu/listByRole";

    private Integer id;

    public MenuListByRoleRequest() {
        super(METHOD);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
