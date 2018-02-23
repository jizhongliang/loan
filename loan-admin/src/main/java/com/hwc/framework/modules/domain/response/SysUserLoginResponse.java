package com.hwc.framework.modules.domain.response;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysMenu;
import com.hwc.framework.modules.domain.DSysUser;

import java.io.Serializable;
import java.util.List;

public class SysUserLoginResponse extends Response implements Serializable {
    private DSysUser dSysUser;
    private List<DSysMenu> menuList;

    public DSysUser getdSysUser() {
        return dSysUser;
    }

    public void setdSysUser(DSysUser dSysUser) {
        this.dSysUser = dSysUser;
    }

    public List<DSysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<DSysMenu> menuList) {
        this.menuList = menuList;
    }


}
