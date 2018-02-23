package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowerUserinfo;
import com.hwc.framework.modules.service.BorrowerUserinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@Api(tags = "借款用户信息")
public class BorrowerUserinfoController {

    @Autowired
    private BorrowerUserinfoService borrowerUserinfoService;

    @PostMapping("/infos")
    @ApiOperation(value = "借款用户信息")
    public Response<List<BorrowerUserinfo>> getBorrowList() {
        return Response.success(borrowerUserinfoService.findBorrowerUserinfos());
    }

    @PostMapping("/state")
    @ApiOperation(value = "借款状态")
    public Response<Response> queryBorrowerUserinfo(String orderId) {
        try {
            return Response.success(borrowerUserinfoService.queryBorrowerUserinfo(orderId));
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @PostMapping("/sync")
    @ApiOperation(value = "借款信息同步")
    public Response synBorrowerUserinfo() {
        borrowerUserinfoService.synBorrowerUserinfo();
        return Response.success();
    }
}
