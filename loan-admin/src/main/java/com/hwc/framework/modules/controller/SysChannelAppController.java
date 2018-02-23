package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysChannelApp;
import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.domain.request.SysChannelAppListRequest;
import com.hwc.framework.modules.service.SysChannelAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin/sys/channel")
public class SysChannelAppController {
    private static final Logger logger = LoggerFactory.getLogger(SysChannelAppController.class);
    @Resource
    private SysChannelAppService sysChannelAppService;

    @PostMapping("/checkV2")
    public Response checkV2(@RequestBody SysChannelAppListRequest dSysChannelApp) {
        Response response = sysChannelAppService.getChannelAppByType(dSysChannelApp.getAppType());
        return response;
    }
}
