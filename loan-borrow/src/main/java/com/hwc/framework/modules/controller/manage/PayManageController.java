package com.hwc.framework.modules.controller.manage;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.domain.PayQueryRequest;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 * web后台支付记录管理
 */
@RestController
@RequestMapping("/manage/pay")
@Api(tags = "web后台支付记录管理")
public class PayManageController {


    @Autowired
    private ClPayLogService payLogService;

    @Autowired
    private PayService payService;

    /**
     * 支付记录
     *
     * @return
     */
    @PostMapping("/credit/list")
    @ApiOperation(value = "信用分期 支付记录")
    public Response<List<PayLogBean>> creditPayList(@RequestBody PayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.CREDIT);
        return payLogService.payLogList(request);
    }

    /**
     * 支付记录
     *
     * @return
     */
    @PostMapping("/morrgage/list")
    @ApiOperation(value = "抵押分期 支付记录")
    public Response<List<PayLogBean>> morrgagePayList(@RequestBody PayQueryRequest request) {
        request.setBorrow_type(BorrowTypeConstant.MORTGAGE);
        return payLogService.payLogList(request);
    }

    /**
     * 获取重新支付信息
     *
     * @return
     */
    @PostMapping("auditPay")
    public Response auditPay(@RequestBody IdRequest<Long> borrowId) {
        return null;
    }

    /**
     * 手工确认放款
     *
     * @return
     */
    @PostMapping("rePay")
    @ApiOperation(value = "手工确认放款")
    public Response borrowLoan(@RequestBody IdRequest<Long> borrowId) {

        return payService.manualLoan(borrowId.getId());
    }

}
