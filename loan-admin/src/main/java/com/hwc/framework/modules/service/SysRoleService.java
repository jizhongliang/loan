package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysRole;
import com.hwc.framework.modules.model.SysRole;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;


/**
 * 2017/09/22.
 */
public interface SysRoleService extends Service<SysRole> {

    public Response updateRole(DSysRole role);

    public Response deleteRole(Integer id);

    public Response getOneRole(Integer id);

    public List<DSysRole> getListRole(Map<String, String> param);

    public Response setAuthority(Integer roleId, String ids);

    public DSysRole getOneRoleByTips(String tips);

//    public int deleteAuthorityByRoleId(Integer id);
}
