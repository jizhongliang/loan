package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowBeanBigDecimal;
import com.hwc.framework.modules.domain.BorrowRepayBean;
import com.hwc.framework.modules.domain.PreRepayBean;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.domain.RepayPlanBeanBigDecimal;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2017/10/31.
 */
@RestController
@RequestMapping("/api/borrow/repay")
@Api(tags = "还款计划")
public class ClBorrowRepayController {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayController.class);


    @Autowired
    private ClBorrowRepayService clBorrowRepayService;

    /**
     * 获取一个临时的还款计划
     *
     * @param borrow
     * @return
     */
    @PostMapping("/calcFirstRepay")
    @ApiOperation(value = "借款确认界面 计算首日还款金额")
    public Response<BorrowRepayBean> calcFirstRepay(@RequestBody BorrowBean borrow) {
        BorrowRepayBean planBean = clBorrowRepayService.calcFirstRepay(borrow);
        return Response.success(planBean);


    }

    /**
     * 查询一个分期明细
     *
     * @return
     */
    @PostMapping("/repayPlanDetail")
    @ApiOperation(value = "获取借款的还款计划")
    public Response<RepayPlanBean> findBorrowRepayInfo(@RequestBody IdRequest<Long> borrowId) {
        RepayPlanBean planBean = clBorrowRepayService.getRepayPlan(borrowId.getId());
        return Response.success(planBean);
    }

    /**
     * 查询一个分期明细
     *
     * @return
     */
    @PostMapping("/mortgage/repayPlanDetail")
    @ApiOperation(value = "获取借款的还款计划")
    public Response<RepayPlanBean> getTempPlanDetail(@RequestBody BorrowBean borrow) {
        RepayPlanBean planBean = clBorrowRepayService.getTempRepayPlan(borrow);
        return Response.success(planBean);
    }

    /**
     * 计算提前还款的利息和总金额
     *
     * @param borrowId
     * @return
     */
    @PostMapping("calcPrePayment")
    @ApiOperation(value = "计算提前还款利息和金额")
    public Response<PreRepayBean> prepayment(@RequestBody IdRequest<Long> borrowId) {
        return clBorrowRepayService.getPrepaymentAmount(borrowId.getId());
    }

    /**
     * 获取一个临时的 还款计划列表
     *
     * @param borrow
     * @return
     */
    @PostMapping("/repayPlanList")
    @ApiOperation(value = "抵押 获取一个临时的还款计划列表")
    public Response<List<RepayPlanBeanBigDecimal>> getBorrowRepayPlanList(@RequestBody BorrowBeanBigDecimal borrow) {
        return clBorrowRepayService.getTempRepayPlanList(borrow);
    }

    @PostMapping("/credit/repayPlanList")
    @ApiOperation(value = "信用 获取一个临时的还款计划列表")
    public Response<RepayPlanBean> getTempCreditRepayPlanList(@RequestBody BorrowBeanBigDecimal borrow) {
        return clBorrowRepayService.genCreditRepayPlan(borrow);
    }

    @PostMapping("/borrowList")
    @ApiOperation(value = "借款记录列表")
    public Response<List<RepayPlanBean>> getBorrowList(@RequestBody IdRequest<Long> userId) {
        List<RepayPlanBeanBigDecimal> planBeans = clBorrowRepayService.getRepayPlanBig(userId.getId());
        if (FsUtils.strsEmpty(planBeans)) {
            return Response.fail("未找到借款记录");
        } else {
            return Response.success(planBeans);
        }


    }

    @PostMapping("/repayDetail")
    @ApiOperation(value = "本期还款明细")
    public Response<BorrowRepayBean> getRepayDetail(@RequestBody IdRequest<Long> request) {
        return clBorrowRepayService.getBorrowRepayDetail(request);
    }
    @PostMapping("/preRepayCheck")
    @ApiOperation(value = "提前还款检查")
    public Response preRepayCheck(@RequestBody IdRequest<Long> request) {
        return clBorrowRepayService.prePaymentCheck(request.getId());
    }
}

