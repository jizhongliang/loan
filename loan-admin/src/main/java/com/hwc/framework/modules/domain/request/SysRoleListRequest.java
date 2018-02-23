package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

public class SysRoleListRequest extends ItemsRequest {

    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
