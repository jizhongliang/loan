package com.hwc.framework.modules.controller;

import cn.freesoft.utils.HttpUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.framework.common.BaofuUtils;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.EarlyRepayBean;
import com.hwc.framework.modules.domain.LianlianResponse;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.RepaymentBean;
import com.hwc.framework.modules.service.ChargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by   on 2017/11/2.
 */
@RestController
@RequestMapping("/api/pay")
@Api(tags = "还款协议")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    /**
     * 主动分期还款
     *
     * @param request
     * @return
     */
    @PostMapping("/repayment")
    @ApiOperation(value = "主动分期还款")
    public Response repayment(@RequestBody DebitBean request) {
    	if(request.getType()==0) {
    		BaofuUtils.isCredit.set(false);
    	}
        return chargeService.repayment(request);
    }

    /**
     * 提前还款
     *
     * @param request
     * @return
     */
    @PostMapping("/preRepayment")
    @ApiOperation(value = "提前还款")
    public Response preRepayment(@RequestBody EarlyRepayBean request) {
    	if(request.getType()==0) {
    		BaofuUtils.isCredit.set(false);
    	}
        return chargeService.preRepayment(request);
    }

//    @PostMapping("/repaymentCallback")
//    public LianlianResponse paymentCallback(HttpServletRequest request) {
//        String resp = HttpUtils.getRequestParams(request);
//        LianlianResponse response = chargeService.repepaymentCallback(resp);
//        return response;
//    }
    
    @PostMapping("/baofuRepay")
    @ApiOperation(value = "宝付代扣还款")
    public Response baofuRepay(@RequestBody DebitBean request) {
    	if(request.getType()==0) {
    		BaofuUtils.isCredit.set(false);
    	}
        return chargeService.baofuRepayment(request);
    }

}
