package com.hwc.framework.modules.controller.manage;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClCreditBorrowService;
import com.hwc.framework.modules.third.BorrowNotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Created by   on 2017/11/6.
 * 信用借款申请处理
 */
@RestController
@RequestMapping("/manage/credit/borrow")
@Api(tags = "信用借款申请处理")
public class ClCreditBorrowManageController {

    @Autowired
    private ClCreditBorrowService creditBorrowService;

    @Autowired
    private ClBorrowService borrowService;

    /**
     * 人工复审查询
     *
     * @return
     */
    @PostMapping("reviewlist")
    @ApiOperation(value = "人工复审列表")
    public Response<List<BorrowBean>> reviewList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.CREDIT);
        return borrowService.getBorrowBeanList(request);

    }

    @PostMapping("/audit")
    @ApiOperation(value = "人工复审")
    public Response auditBorrow(@RequestBody BorrowAuditBean bean) {
        return creditBorrowService.auditBorrow(bean, null);

    }

    @PostMapping("/listbyOneUser")
    @ApiOperation(value = "借款订单")
    public Response<List<BorrowBean>> borrowListByUserId(@RequestBody BorrowQueryRequest request) {
        if (FsUtils.strsNotEmpty(request.getUserId())) {
            return borrowService.getBorrowBeanList(request);
        } else
            return Response.fail("用户Id不存在");

    }

    /**
     * 借款列表
     *
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "借款列表")
    public Response<List<BorrowBean>> borrowList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.CREDIT);
        return borrowService.getBorrowBeanList(request);

    }

    /**
     * 放款订单列表
     *
     * @return
     */
    @PostMapping("/loanlist")
    @ApiOperation(value = "放款订单列表")
    public Response<List<BorrowBean>> loanBorrowList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.CREDIT);
        return borrowService.getBorrowBeanList(request);

    }

    /**
     * 已还款记录 包括 已还款 已逾期 坏账
     *
     * @return
     */
    @PostMapping("/repayList")
    @ApiOperation(value = "已还款记录 包括 已还款 已逾期 坏账")
    public Response<List<BorrowBean>> borrowRepayList(@RequestBody BorrowQueryRequest request) {
        request.setType(BorrowTypeConstant.CREDIT);
        return borrowService.getBorrowBeanList(request);

    }

    @PostMapping("/detail")
    @ApiOperation(value = "信用借款订单详情")
    public Response<BorrowBean> getBorrowDetail(@RequestBody IdRequest<Long> request) {
        return Response.success(borrowService.getBorrowBean(request.getId()));
    }


}
