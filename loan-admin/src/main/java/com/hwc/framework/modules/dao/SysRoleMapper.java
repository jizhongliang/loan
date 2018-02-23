package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.SysRole;
import com.hwc.mybatis.core.Mapper;

public interface SysRoleMapper extends Mapper<SysRole> {

    public void deleteAuthorityByRoleId(Integer roleId);

}