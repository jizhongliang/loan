package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ArcCreditBean;
import com.hwc.framework.modules.model.UserBorrowQuota;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.web.core.RequestUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/23.
 * 用户额度
 */
@RestController
@RequestMapping("/api/credit")
public class ArcCreditController {
    private static final Logger logger = LoggerFactory.getLogger(ArcCreditController.class);

    @Autowired
    private ArcCreditService arcCreditService;

    //    @PostMapping("/example")
//    public Response example(@RequestParam Integer id) {
//        ArcCredit arcCredit = arcCreditService.findById(id);
//
//        return Response.success(arcCredit);
//    }
    @PostMapping("/genQuota")
    public Response genQuota(@RequestBody ArcCreditBean bean) {
        return arcCreditService.setUserQuota(bean.getUserId(), bean.getTotal().doubleValue());
    }

    @PostMapping("/getQuota")
    @ApiOperation("获取用户额度")
    public Response<UserBorrowQuota> getQuota(@RequestBody IdRequest<Long> request) {
        return arcCreditService.getQuota(request);

    }

}
