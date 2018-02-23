package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysMenu;
import com.hwc.framework.modules.model.SysMenu;
import com.hwc.mybatis.core.Service;

import java.util.List;


/**
 * 2017/09/22.
 */
public interface SysMenuService extends Service<SysMenu> {
    public DSysMenu getOneMenuByCode(String code);

    public Response getOneMenu(Integer menuId);

    public Response addOneMenu(DSysMenu menu);

    public Response updateMenu(DSysMenu menu);

    public Response deleteMenu(Integer menuId);

    public List<SysMenu> selectMenus(String menuName, Integer level);

    public List<DSysMenu> getMenuListByRoleId(Integer roleId);

    public Response addMenu2(DSysMenu dMenu);
}
