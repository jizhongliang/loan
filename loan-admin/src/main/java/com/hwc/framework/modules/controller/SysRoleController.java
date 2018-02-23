package com.hwc.framework.modules.controller;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DRelation;
import com.hwc.framework.modules.domain.DSysRole;
import com.hwc.framework.modules.domain.request.SysRoleListRequest;
import com.hwc.framework.modules.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/09/22.
 */
@RestController
@RequestMapping("/api/admin/sys/role")
public class SysRoleController {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Resource
    private SysRoleService roleService;

    @PostMapping("/updateOne")
    public Response updateRole(@RequestBody DSysRole role) {
        Response response = roleService.updateRole(role);
        return response;
    }

    @PostMapping("/deleteOne")
    public Response delete(@RequestBody IdRequest<Integer> role) {
        Response response = roleService.deleteRole(role.getId());
        return response;
    }

    @PostMapping("/getOne")
    public Response<DSysRole> getOne(@RequestBody IdRequest<Integer> role) {
        Response response = roleService.getOneRole(role.getId());
        return response;
    }

    @PostMapping("/listPage")
    public Response<List<DSysRole>> list(@RequestBody SysRoleListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",request.getName());
        List<DSysRole> list = roleService.getListRole(map);
        return Response.success(list);
    }

    @PostMapping("/setAuthority")
    public Response setAuthority(@RequestBody DRelation dRelation) {
        Response response = roleService.setAuthority(dRelation.getRoleId(),dRelation.getIds());
        return response;
    }


}
