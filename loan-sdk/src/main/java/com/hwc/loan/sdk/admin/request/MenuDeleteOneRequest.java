package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.MenuDeleteOneResponse;

public class MenuDeleteOneRequest extends RequestBase<MenuDeleteOneResponse> {

    public static final String METHOD = "/api/admin/sys/menu/deleteOne";

    public MenuDeleteOneRequest() {
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
