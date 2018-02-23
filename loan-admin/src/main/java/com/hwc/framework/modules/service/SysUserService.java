package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.model.SysUser;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;


/**
 * 2017/09/22.
 */
public interface SysUserService extends Service<SysUser> {
    public Response getLoginUser(String userName, String password);

    public Response updateUser(DSysUser dSysUser);

    public Response deleteUser(Integer userId);

    public DSysUser getOneUser(Integer userId);

    public List<DSysUser> getListUserPage(Map<String, String> param);

    public DSysUser getOneUserByMap(Map<String, String> param);
}
