package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.SysUser;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    public List<SysUser> listUserPage(Map<String, String> param);

    public int queryRoleHaveSysUserCount(Integer roleId);

}