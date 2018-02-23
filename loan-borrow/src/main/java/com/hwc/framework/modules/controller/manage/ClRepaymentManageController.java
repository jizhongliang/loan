package com.hwc.framework.modules.controller.manage;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.BorrowRepayBean;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.service.ChargeService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 */
@RestController
@RequestMapping("/manage/repayment")
@Api(tags = "还款管理")
public class ClRepaymentManageController {

    @Autowired
    private ClBorrowRepayService repayService;
    @Autowired
    private ChargeService chargeService;

    /**
     * 还款计划列表
     *
     * @return
     */
    @PostMapping("/credit/list")
    @ApiOperation(value = "信用分期 还款计划列表")
    public Response<List<BorrowRepayBean>> creditList(@RequestBody RepayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.CREDIT);
        return repayService.repayList(request);
    }
    /**
     * 还款计划列表
     *
     * @return
     */
    @PostMapping("/mortgage/list")
    @ApiOperation(value = "抵押分期 还款计划列表")
    public Response<List<BorrowRepayBean>> mortgageList(@RequestBody RepayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.MORTGAGE);
        return repayService.repayList(request);
    }
    /**
     * 手工扣款
     *
     * @return
     */
    @PostMapping("/manualRepay")
    @ApiOperation(value = "手工扣款")
    public Response manualRepay(@RequestBody IdRequest<Long> repay_id) {
        DebitBean bean=new DebitBean();
        bean.setRepayId(repay_id.getId());
        return chargeService.repayment(bean);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查看还款计划明细")
    public Response<RepayPlanBean> repayDetail(@RequestBody IdRequest<Long> borrow_id) {
        RepayPlanBean bean = repayService.getRepayPlan(borrow_id.getId());
        return Response.success(bean);
    }


}

