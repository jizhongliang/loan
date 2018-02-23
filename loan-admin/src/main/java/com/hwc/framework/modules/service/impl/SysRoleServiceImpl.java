package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import com.hwc.framework.modules.dao.SysRelationMapper;
import com.hwc.framework.modules.dao.SysRoleMapper;
import com.hwc.framework.modules.dao.SysUserMapper;
import com.hwc.framework.modules.domain.DSysRole;
import com.hwc.framework.modules.model.SysRelation;
import com.hwc.framework.modules.model.SysRole;
import com.hwc.framework.modules.service.SysRoleService;
import com.hwc.framework.modules.utils.Convert;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/09/22.
 */
@Service
public class SysRoleServiceImpl extends AbstractService<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    SysRelationMapper relationMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public Response updateRole(DSysRole request) {
        SysRole sysRole = new SysRole();
        if (FsUtils.strsEmpty(request.getName(),request.getTips())){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        covertToSysRole(request,sysRole);
        if (!FsUtils.strsEmpty(request.getId())){
            this.mapper.updateByPrimaryKeySelective(sysRole);
            return Response.success();
        }else {
            DSysRole findSysRole = this.getOneRoleByTips(sysRole.getTips());
            if (findSysRole != null){
                return Response.fail(ResponseCode.OBJECT_IS_EMPTY);
            }else {
                sysRole.setPid(1);
                sysRole.setTips(sysRole.getTips().toUpperCase());
                this.mapper.insert(sysRole);
                return Response.success();
            }

        }
    }

    @Override
    public Response deleteRole(Integer id) {
        if (FsUtils.strsEmpty(id)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        if (!FsUtils.strsEmpty(id)){
            if (id.equals("1")){//常量
                return Response.fail("不能删除管理员角色");
            }
            int count = this.sysUserMapper.queryRoleHaveSysUserCount(id);
            if (count >0 ){
                return Response.fail("该角色下拥有用户，不能删除");
            }
            this.mapper.deleteByPrimaryKey(id);
        }
        return Response.success();
    }

    @Override
    public Response getOneRole(Integer id) {
        if (FsUtils.strsEmpty(id)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        SysRole role = this.mapper.selectByPrimaryKey(id);
        if (role != null){
            DSysRole response = new DSysRole();
            covertToDSysRole(response,role);
            return Response.success(response);
        }else {
            return Response.fail(ResponseCode.OBJECT_IS_EMPTY);
        }
    }

    @Override
    public List<DSysRole> getListRole(Map<String, String> param) {
        List<DSysRole> responseList = null;
        List<SysRole>  roleList = this.mapper.selectBySelective(param);
        if (roleList != null){
            responseList = new ArrayList<DSysRole>();
            for (int i=0; i<roleList.size(); i++){
                DSysRole dSysRole = new DSysRole();
                covertToDSysRole(dSysRole,roleList.get(i));
                responseList.add(dSysRole);
            }
        }
        List<DSysRole> list = PageUtils.convert(roleList,responseList);
        return  list;
    }

    @Override
    public Response setAuthority(Integer roleId, String ids) {
        if (FsUtils.strsEmpty(roleId,ids)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
        this.mapper.deleteAuthorityByRoleId(roleId);
        for (Integer id : Convert.toIntArray(ids)) {
            SysRelation relation = new SysRelation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
        return Response.success();
    }

    @Override
    public DSysRole getOneRoleByTips(String tips) {
        DSysRole dSysRole = new DSysRole();
        Map<String,String> map = new HashMap<String,String>();
        if (!FsUtils.strsEmpty(tips)){
            map.put("tips",tips.toUpperCase());
            SysRole sysRole = this.mapper.selectOneBySelective(map);
            if (sysRole !=null){
                covertToDSysRole(dSysRole,sysRole);
                return dSysRole;
            }
        }
        return null;
    }


    public void covertToDSysRole (DSysRole response, SysRole role){
        response.setId(role.getId());
        response.setName(role.getName());
        response.setPid(role.getPid());
        response.setNum(role.getNum());
        response.setTips(role.getTips());
        response.setVersion(role.getVersion());
    }

    public void covertToSysRole (DSysRole response, SysRole role){
        role.setId(response.getId());
        role.setName(response.getName());
        role.setPid(response.getPid());
        role.setNum(response.getNum());
        role.setTips(response.getTips());
        role.setVersion(response.getVersion());
    }

}
