package com.hwc.loan.sdk.admin.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.admin.response.RoleGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class RoleGetOneRequest extends RequestBase<RoleGetOneResponse> {
    public static final String METHOD = "/api/admin/sys/role/getOne";

    public RoleGetOneRequest() {
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
