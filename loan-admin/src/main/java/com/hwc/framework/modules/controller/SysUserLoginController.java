package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.domain.response.SysUserLoginResponse;
import com.hwc.framework.modules.service.SysMenuService;
import com.hwc.framework.modules.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin/sys/user")
public class SysUserLoginController {

    @Resource
    private SysMenuService menuService;

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Response<DSysUser> login(@RequestBody DSysUser request) {
        Response response = sysUserService.getLoginUser(request.getAccount(), request.getPassword());
        return response;
    }

}
