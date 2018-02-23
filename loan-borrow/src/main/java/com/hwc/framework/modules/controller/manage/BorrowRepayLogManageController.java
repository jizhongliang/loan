package com.hwc.framework.modules.controller.manage;


import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.RepayLogBean;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 */
@RestController
@RequestMapping("/manage/repaylog")
@Api(tags = "还款记录")
public class BorrowRepayLogManageController {
    @Autowired
    private ClBorrowRepayLogService repayLogService;

    /**
     * 还款记录列表
     */
    @PostMapping("/mortgage/list")
    @ApiOperation(value = "车位分期 还款记录列表")
    public Response<List<RepayLogBean>> mortgageRepayList(@RequestBody RepayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.MORTGAGE);
        return repayLogService.getPayLogList(request);
    }
    /**
     * 还款记录列表
     */
    @PostMapping("/credit/list")
    @ApiOperation(value = "信用分期 还款记录列表")
    public Response<List<RepayLogBean>> creditRepayList(@RequestBody RepayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.MORTGAGE);
        return repayLogService.getPayLogList(request);
    }
}
