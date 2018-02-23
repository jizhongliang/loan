package com.hwc.framework.modules.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.QuartzLogModel;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.loan.sdk.quzrtz.request.QuartzLogListPageRequest;

import io.swagger.annotations.Api;

/**
* 定时任务记录Controller
*/
@RestController
@RequestMapping("api/p2p/manager/quartzLog")
@Api(tags = "定时任务管理")
public class QuartzLogController {

    @Autowired
    private QuartzLogService quartzLogService;

    /**
    * 定时日志列表
    * @param search
    * @param current
    * @param pageSize
    * @throws Exception
    */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "page")
    public Response quartzLog(@RequestBody QuartzLogListPageRequest request) throws Exception {
        Map<String, Object> searchMap = new HashMap<String, Object>();
        if (request.getQuartzId() != null) {
            searchMap.put("quartzId", request.getQuartzId());
        }
        Page<QuartzLogModel> page = quartzLogService.page(searchMap, request.getPage(),
            request.getPageSize());
        return Response.success(page);
    }
}
