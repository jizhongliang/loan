package com.hwc.framework.modules.controller.manage;

import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.service.ClBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 * 后台处理抵押借款订单
 */
@RestController
@RequestMapping("/manage/mortgage/borrow")
@Api(tags = "后台处理抵押借款订单")
public class ClMortgageBorrowManageController {
    @Autowired
    private ClBorrowService borrowService;

    /**
     * 借款列表
     *
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "借款列表")
    public Response<List<BorrowBean>> borrowList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.MORTGAGE);
        return borrowService.getBorrowBeanList(request);

    }

    /**
     * 放款订单列表
     *
     * @return
     */
    @ApiOperation(value = "放款订单列表")
    @PostMapping("/loanlist")
    public Response<List<BorrowBean>> loanBorrowList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.MORTGAGE);
        return borrowService.getBorrowBeanList(request);

    }

    /**
     * 已还款记录 包括 已还款 已逾期 坏账
     *
     * @return
     */
    @ApiOperation(value = "已还款记录 包括 已还款 已逾期 坏账")
    @PostMapping("/repayList")
    public Response<List<BorrowBean>> borrowRepayList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.MORTGAGE);
        return borrowService.getBorrowBeanList(request);

    }

}
