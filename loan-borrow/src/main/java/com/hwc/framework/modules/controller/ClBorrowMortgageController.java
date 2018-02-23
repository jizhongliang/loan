package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.MortgageIndex;
import com.hwc.framework.modules.service.ClMortgageBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 */

/**
 * 抵押贷款
 */
@RestController
@RequestMapping("/api/mortgage/borrow")
@Api(tags = "车位分期借款申请")
public class ClBorrowMortgageController {

    @Autowired
    private ClMortgageBorrowService mortgageBorrowService;


    @PostMapping("/banner")
    @ApiOperation(value = "首页广告")
    public Response getBanner() {
        return mortgageBorrowService.getBanner();
    }

    /**
     * 首页获取 抵押借款情况
     *
     * @param userId
     * @return
     */
    @PostMapping("/index")
    @ApiOperation(value = "车位分期首页")
    public Response<MortgageIndex> getIndex(@RequestBody IdRequest<Long> userId) {
        return mortgageBorrowService.homePage(userId.getId());
    }


    /**
     * 审核通过 提现
     *
     * @return
     */
    @PostMapping("/withdraw")
    @ApiOperation(value = "车位分期提现")
    public Response withdrawCash(@RequestBody BorrowBean bean) {
        return mortgageBorrowService.borrow(bean);
    }


    @PostMapping("/quotaRang")
    @ApiOperation(value = "车位分期额度选择范围")
    public Response<List<Double>> quotaRang(@RequestBody IdRequest<Long> userId) {
        return Response.success(mortgageBorrowService.getQuotaRang(userId.getId()));
    }
    /**
     * 选择借款金额和分期数
     * @return
     */
    /**
     * 选择借款金额和分期数
     *
     * @return
     */
//    @GetMapping("/choice")
//    public Response choice() {
//        return null;
//    }
//
//    /**
//     * 查询借款信息
//     *
//     * @return
//     */
//    @GetMapping("/findBorrowInfo")
//    public Response findBorrowInfo() {
//        return null;
//    }


//    public Response apply() {
//        return null;
//    }
}
