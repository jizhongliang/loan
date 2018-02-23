package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

public class SysUserListRequest extends ItemsRequest {

    private  String name;

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
