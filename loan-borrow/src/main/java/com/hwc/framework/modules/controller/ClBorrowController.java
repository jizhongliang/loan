package com.hwc.framework.modules.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.loan.sdk.borrow.domain.Rate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by   on 2017/11/15.
 */
@RestController
@RequestMapping("/api/borrow")
@Api(tags = "借款列表")
public class ClBorrowController {

    /**  */
    @Autowired
    private ClBorrowRepayService clBorrowRepayService;

    /**  */
    @Autowired
    private ClBorrowService      clBorrowService;

    @PostMapping("/list")
    @ApiOperation(value = "借款列表")
    public Response<List<RepayPlanBean>> getBorrowList(@RequestBody IdRequest<Long> userId) {
        return Response.success(clBorrowRepayService.getRepayPlans(userId.getId()));
    }

    @PostMapping("/getRate")
    @ApiOperation(value = "获取日费率")
    public Response<Rate> getRate(@RequestBody IdRequest<Long> userId) {
        Rate rate = new Rate();
        Map<String, String> map = clBorrowService.findRate(userId.getId());
        rate.setRate(map.get("rate"));
        rate.setRates(map.get("rates"));
        rate.setRatess(map.get("ratess"));
        return Response.success(rate);
    }
}
