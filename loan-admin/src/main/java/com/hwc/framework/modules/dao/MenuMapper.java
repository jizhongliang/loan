package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.SysMenu;
import com.hwc.mybatis.core.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<SysMenu> {
    public List<SysMenu> getMenuListByUserRole(Integer roleId);
}