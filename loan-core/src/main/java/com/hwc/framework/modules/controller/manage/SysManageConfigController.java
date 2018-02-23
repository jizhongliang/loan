package com.hwc.framework.modules.controller.manage;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BaseCodeRequest;
import com.hwc.framework.modules.domain.ConfigQueryRequest;
import com.hwc.framework.modules.domain.SysConfig;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/28.
 */
@RestController
@RequestMapping("/manage/sys")
@Api(tags = "基本代码获取")
public class SysManageConfigController {

    @Autowired
    private ArcBasecodeService arcBasecodeService;


    @Autowired
    private ArcSysConfigService arcSysConfigService;

    @PostMapping("/basecodeList")
    @ApiOperation(value = "获取字典数据")
    public Response<List<BaseCode>> getBasecodeList(@RequestBody BaseCodeRequest request) {
        return arcBasecodeService.getBasecodeList(request);
    }

    @PostMapping("/list")
    @ApiModelProperty("系统参数列表")
    public Response<List<SysConfig>> getSysConfigList(@RequestBody ConfigQueryRequest request) {
        return arcSysConfigService.getSysConfig(request);
    }

    @PostMapping("/update")
    @ApiModelProperty("更新一个配置")
    public Response updateConfig(@RequestBody SysConfig config) {
        return arcSysConfigService.updateConfig(config);
    }

    @PostMapping("/del")
    @ApiModelProperty("删除一个配置")
    public Response delConfig(@RequestBody IdRequest<Long> request) {
        return arcSysConfigService.deleteConfig(request.getId());
    }

    @PostMapping("/add")
    @ApiModelProperty("新增一个配置")
    public Response setConfig(@RequestBody SysConfig config) {
        return arcSysConfigService.insertConfig(config);
    }

    @PostMapping("/basecode/add")
    @ApiModelProperty("新增一个配置")
    public Response addBasecode(@RequestBody BaseCode code) {
        return arcBasecodeService.add(code);
    }

    @PostMapping("/basecode/update")
    @ApiModelProperty("修改一个配置")
    public Response updateBasecode(@RequestBody BaseCode code) {
        return arcBasecodeService.update(code);
    }

    @PostMapping("/basecode/del")
    @ApiModelProperty("删除一个配置")
    public Response delBasecode(@RequestBody BaseCode code) {
        return arcBasecodeService.del(code);
    }
}
