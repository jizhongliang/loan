package com.hwc.framework.modules.controller;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.sdk.core.Client;
import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.domain.request.SysUserListRequest;
import com.hwc.framework.modules.service.SysUserService;
import com.hwc.loan.sdk.user.request.UserListPageRequest;
import com.hwc.loan.sdk.user.response.UserListPageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/api/admin/sys/user")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/updateOne")
    public Response updateOne(@RequestBody DSysUser dSysUser) {
        Response response = sysUserService.updateUser(dSysUser);
        return response;
    }

    @PostMapping("/deleteOne")
    public Response deleteOne(@RequestBody IdRequest<Integer> user) {
        Response response = sysUserService.deleteUser(user.getId());
        return response;
    }

    @PostMapping("/getOne")
    public Response get(@RequestBody IdRequest<Integer> user) {
        DSysUser dsysUser = sysUserService.getOneUser(user.getId());
        return Response.success(dsysUser);
    }

    @PostMapping("/listPage")
    public Response<List<DSysUser>> list(@RequestBody SysUserListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",request.getName());
        map.put("roleid",request.getRoleId());
        List<DSysUser> list = sysUserService.getListUserPage(map);
        return Response.success(list);
    }

    @Autowired
    @Qualifier("userClient")
    private Client userClient;

    @PostMapping("/test")
    public Response test() {
        UserListPageRequest request = new UserListPageRequest();
        request.setPage(1);
        request.setPageSize(10);
        UserListPageResponse response = userClient.invoke(request);
        return Response.success(response);
    }
}
