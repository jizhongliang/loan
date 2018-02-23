package com.hwc.framework.modules.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.service.ArcBasecodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 2017/11/01.
 */
@RestController
@RequestMapping("/api/sys")
@Api(tags = "基本代码获取")
public class ArcBasecodeController {
    private static final Logger logger = LoggerFactory.getLogger(ArcBasecodeController.class);

    @Autowired
    private ArcBasecodeService  arcBasecodeService;

    @PostMapping("/basecodeList")
    @ApiOperation(value = "获取字典数据")
    public Response<List<BaseCode>> getBasecode(@RequestBody IdRequest<String> request) {
        return Response.success(arcBasecodeService.getBasecodeList(request.getId()));
    }
    @PostMapping("/bankcodeList")
    @ApiOperation(value = "获取字典数据")
    public Response<List<BaseCode>> getBankcode(@RequestBody IdRequest<String> request) {
    	return Response.success(arcBasecodeService.getBankcodeList(request.getId()));
    }

    @PostMapping("/getBaseCode")
    @ApiOperation(value = "获取baseCode ext数据")
    public Response<BaseCode> getBaseCode(@RequestBody BaseCode request) {
        return Response
            .success(arcBasecodeService.getBaseCode(request.getCat(), request.getCode()));
    }

}
