package com.hwc.framework.modules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ConfigQueryRequest;
import com.hwc.framework.modules.domain.H5UrlConfig;
import com.hwc.framework.modules.domain.HelpBean;
import com.hwc.framework.modules.domain.OssConfig;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.service.ArcSysConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 2017/11/01.
 */
@RestController
@RequestMapping("/api/sys/config")
@Api("系统参数配置")
public class ArcSysConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ArcSysConfigController.class);

    @Autowired
    private ArcSysConfigService arcSysConfigService;

    @PostMapping("/ossConfig")
    @ApiOperation("获取OSS配置 id=face 人脸识别 ,id=dy 抵押类文件, id=avatar 用户昵称头像")
    public Response<OssConfig> getOssConfig(@RequestBody IdRequest<String> request) {
        return arcSysConfigService.getOssConfig(request);
    }

    @PostMapping("/h5")
    @ApiOperation("获取app协议地址 id=包名")
    public Response<H5UrlConfig> getH5(@RequestBody HelpBean request) {
        return arcSysConfigService.getAppH5(request);
    }

    @PostMapping("/querySysConfig")
    @ApiOperation("获取系统配置信息")
    public Response<ArcSysConfig> getSysConfig(@RequestBody ConfigQueryRequest request) {
        ArcSysConfig arcSysConfig = arcSysConfigService.getSysConfigByCode(request.getCode());
        return Response.success(arcSysConfig);
    }
}
