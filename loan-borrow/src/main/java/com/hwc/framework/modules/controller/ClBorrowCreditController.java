package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.service.ClCreditBorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/credit/borrow")
@Api(tags = "信用分期借款")
public class ClBorrowCreditController {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowCreditController.class);

    @Autowired
    private ClCreditBorrowService clBorrowService;

    /**
     * 首页获取 信用借款情况
     *
     * @param userId
     * @return
     */
    @PostMapping("/index")
    @ApiOperation(value = "信用分期首页")
    public Response<BorrowBean> getIndex(@RequestBody IdRequest<Long> userId) {

        BorrowBean bean = clBorrowService.getIndex(userId.getId());
        return Response.success(bean);

    }

    /**
     * 提借款申请
     *
     * @return
     */
    @PostMapping("/apply")
    @ApiOperation(value = "申请借款")
    public Response borrowApply(@RequestBody BorrowBean borrowBean) {
        Response response = clBorrowService.borrow(borrowBean);
        return response;

    }

    /**
     * 选择借款金额和分期数
     *
     * @return
     */
    public Response choice() {
        return null;
    }

    /**
     * 查询借款信息
     *
     * @return
     */
    public Response findBorrowInfo() {
        return null;
    }


}
