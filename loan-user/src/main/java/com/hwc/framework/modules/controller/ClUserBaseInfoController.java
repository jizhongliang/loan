package com.hwc.framework.modules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.CloanUserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/api/user/baseInfo")
public class ClUserBaseInfoController {
    private static final Logger   logger = LoggerFactory.getLogger(ClUserBaseInfoController.class);

    @Autowired
    private ClUserBaseInfoService clUserBaseInfoService;

    @Autowired
    private ClUserAuthService     clUserAuthService;

    @Autowired
    private CloanUserService      cloanUserService;

    /**
     *  获取用户详情 API
     *
     */
    @PostMapping("/getOne")
    public Response getOne(@RequestBody IdRequest<Long> request) {
        Response response = cloanUserService.getCloanUserById(request.getId());
        return response;
    }

    @PostMapping("/byphone")
    public Response byphone(@RequestBody DCloanUserDomain dCloanUserDomain) {
        Response response = clUserBaseInfoService.getByPhone(dCloanUserDomain);
        return response;
    }

    /**
     * 
     * @param dCloanUserDomain
     * @return
     */
    @PostMapping("/getByIdNo")
    public Response getByIdNo(@RequestBody DCloanUserDomain dCloanUserDomain) {
        Response response = clUserBaseInfoService.getByIdNo(dCloanUserDomain);
        return response;
    }

    /**
     *  获取用户验证状态结果 和 交易密码状态
     *
     */
    @PostMapping("/getAuthTradeState")
    public Response getAuthStateAndTradeState(@RequestBody IdRequest<Long> request) {
        Response response = this.clUserAuthService.getAuthTradeState(request.getId());
        return response;
    }

    /**
     *  获取用户详情 APP
     *
     */
    @PostMapping("/userInfo")
    public Response userInfo(@RequestBody IdRequest<Long> request) {
        Response response = clUserBaseInfoService.getUserBaseInfoApp(request.getId());
        return response;
    }

    /**
     *  更新用户的身份信息
     *
     */
    @PostMapping("/updateUserIdCardInfo")
    public Response updateUserIdCardInfo(@RequestBody DUserBaseInfo dUserBaseInfo) {
        Response response = clUserBaseInfoService.updateUserBaseInfoByUserId(dUserBaseInfo);
        return response;
    }

    @PostMapping("/updateUserBaseinfoCw")
    public Response updateUserBaseinfoCw(@RequestBody DUserBaseInfo dUserBaseInfo) {
        Response response = clUserBaseInfoService.updateUserBaseInfoByUserIdCW(dUserBaseInfo);
        return response;
    }
}
