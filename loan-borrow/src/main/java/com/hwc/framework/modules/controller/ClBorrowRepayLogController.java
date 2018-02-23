package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowRepayBean;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2017/10/31.
 */
@RestController
@RequestMapping("/api/borrow/repay/log")
@Api(tags = "已还款记录")
public class ClBorrowRepayLogController {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayLogController.class);

    @Autowired
    private ClBorrowRepayLogService clBorrowRepayLogService;

    /**
     * 已还款记录
     * @param userId
     * @return
     */
    @PostMapping("/borrowRepayList")
    @ApiOperation(value = "已还款记录列表")
    public Response<List<PayLogBean>> hasBorrowRepayList(@RequestBody IdRequest<Long>userId) {
        List<PayLogBean> beans = clBorrowRepayLogService.getPayLogList(userId.getId());
        return Response.success(beans);
    }

}
