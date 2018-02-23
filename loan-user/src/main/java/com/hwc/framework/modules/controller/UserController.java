package com.hwc.framework.modules.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.sdk.core.Client;
import com.hwc.framework.modules.domain.DContactsModel;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.DUserModel;
import com.hwc.framework.modules.model.ClUser;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.loan.sdk.borrow.request.RateRequest;
import com.hwc.loan.sdk.borrow.response.RateResponse;

/**
 * 用户基本管理
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ClUserService       clUserService;

    @Autowired
    private Client              borrowClient;

    /**
     * APP注册
     *
     */
    @PostMapping("/register")
    public Response register(@RequestBody DUser dUser) {
        return clUserService.registerUser(dUser);

    }

    /**
     * 微信注册
     *
     */
    @PostMapping("/wxRegister")
    public Response wxRegister(@RequestBody DUser dUser) {
        return clUserService.wxRegisterUser(dUser);
    }

    /**
     * 登录
     *
     */
    @PostMapping("/login")
    public Response login(@RequestBody DUser dUser) {
        return clUserService.loginUser(dUser);
    }

    /**
     * 微信验证账号
     *
     */
    @PostMapping("/wxCheckAccount")
    public Response wxCheck(@RequestBody DUser dUser) {
        return clUserService.wxCheckAccount(dUser);
    }

    /**
     * 微信登录
     *
     */
    @PostMapping("/wxLogin")
    public Response wxLogin(@RequestBody DUser dUser) {
        return clUserService.wxLoginUser(dUser);
    }

    /**
     * 微信退出
     *
     */
    @PostMapping("/loginout")
    public Response loginout(@RequestBody DUser dUser) {
        return clUserService.loginout(dUser);
    }

    /**
     * 微信登录(已有账号绑定第三方账号)
     *
     */
    @PostMapping("/wxBindLogin")
    public Response wxBindLogin(@RequestBody DUser dUser) {
        return clUserService.wxBindLogin(dUser);
    }

    /**
     * 短信验证码登录
     *
     */
    @PostMapping("/vCodeLogin")
    public Response vCodeLogin(@RequestBody DUser dUser) {
        return clUserService.vCodeLoginUser(dUser);
    }

    /**
     * 修改密码
     *
     */
    @PostMapping("/updatePwd")
    public Response resetPwd(@RequestBody DUser dUser) {
        return clUserService.updatePwd(dUser);
    }

    /**
     * 忘记密码
     *
     */
    @PostMapping("/forgetPwd")
    public Response forgetPwd(@RequestBody DUser dUser) {
        return clUserService.forgetPwd(dUser);
    }

    /**
     * 获取用户类型-信用贷款 抵押贷款
     *
     */
    @PostMapping("/getCat")
    public Response getCat(@RequestBody IdRequest<Long> request) {
        return clUserService.getUserCat(request.getId());
    }

    /**
     * 更新用户类型
     *
     */
    @PostMapping("/updateCat")
    public Response updateCat(@RequestBody DUser dUser) {
        return clUserService.updateCat(dUser);
    }

    /**
     * 是否设置了交易密码
     *
     */
    @PostMapping("/isSetTradePwd")
    public Response isSetTradePwd(@RequestBody DUser dUser) {
        return clUserService.isSetTradePwd(dUser);
    }

    /**
     * 设置交易密码
     *
     */
    @PostMapping("/setTradePwd")
    public Response setTradePwd(@RequestBody DUser dUser) {
        return clUserService.setTradePwd(dUser);
    }

    /**
     * 修改交易密码
     *
     */
    @PostMapping("/changeTradePwd")
    public Response changeTradePwd(@RequestBody DUser dUser) {
        return clUserService.changeTradePwd(dUser);
    }

    /**
     * 修改登录密码
     *
     */
    @PostMapping("/changeDLPwd")
    public Response changeDLPwd(@RequestBody DUser dUser) {
        return clUserService.changeDLPwd(dUser);
    }

    /**
     * 验证交易密码状态
     *
     */
    @PostMapping("/validateTradePwd")
    public Response validateTradePwd(@RequestBody DUser dUser) {
        return clUserService.validateTradePwd(dUser);
    }

    /**
     * （重置交易密码前）验证用户
     *
     */
    @PostMapping("/validateUser")
    public Response validateUser(@RequestBody DUserBaseInfo dUserBaseInfo) {
        return clUserService.validateUser(dUserBaseInfo);
    }

    /**
     * (重置交易密码时 获取验证码 验证用户信息 lyf)
     * 
     * @param dUserBaseInfo
     * @return
     */
    @PostMapping("/resetTradePwdPreCheck")
    public Response resetTradePwdPreCheck(@RequestBody DUserModel DUserModel) {
        return clUserService.resetTradePwdPreCheck(DUserModel);
    }

    /**
     * 重置交易密码
     *
     */
    @PostMapping("/resetTradePwd")
    public Response resetTradePwd(@RequestBody DUserModel dUserModel) {
        return clUserService.resetTradePwd(dUserModel);
    }

    /**
     * 上传通讯录
     *
     */
    @PostMapping("/uploadContacts")
    public Response uploadContacts(@RequestBody DContactsModel contactsModel) {
        return clUserService.uploadContacts(contactsModel);
    }

    /**
     * 上传短信
     *
     */
    @PostMapping("/uploadMessages")
    public Response uploadMessages(@RequestBody DContactsModel contactsModel) {
        return clUserService.uploadContacts(contactsModel);
    }

    @PostMapping("/getBorrowRate")
    public Response getBorrowRate(@RequestBody DUserModel DUserModel) {
        String loginName = DUserModel.getLoginName();
        double borrowRate = 0.5;
        if (StringUtils.isNotBlank(loginName)) {
            borrowRate = clUserService.getRateByPhone(loginName);
        } else {
            borrowRate = clUserService.getRateByUserId(DUserModel.getUserId());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("borrowRate", borrowRate);
        return Response.success(map);
    }

    @PostMapping("/getBorrowRateBig")
    public Response getBorrowRateBig(@RequestBody DUserModel DUserModel) {
        String loginName = DUserModel.getLoginName();
        BigDecimal borrowRate = new BigDecimal(0.5000);
        if (StringUtils.isNotBlank(loginName)) {
            borrowRate = clUserService.getRateByPhoneBig(loginName);
        } else {
            borrowRate = clUserService.getRateByUserIdBig(DUserModel.getUserId());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("borrowRate", borrowRate);
        return Response.success(map);
    }

    @PostMapping("/switch")
    public Response list(@RequestBody IdRequest<Integer> request) {
        return clUserService.getCode(request.getId());

    }

    /**
     * 获取白名单利率
     * @param DUserModel
     * @return
     */
    @PostMapping("/getRate")
    public Response getRate(@RequestBody DUserModel DUserModel) {
        String loginName = DUserModel.getLoginName();

        ClUser clUser = clUserService.findUserByParameter(loginName, "");
        String borrowRate = "0.05";
        RateRequest rateRequest = new RateRequest();
        rateRequest.setId(clUser.getId());
        RateResponse rateResponse = borrowClient.invoke(rateRequest);
        if (rateResponse.getSuccess()) {
            logger.info(rateResponse.getData().getRate());
            borrowRate = rateResponse.getData().getRate();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("borrowRate", borrowRate);
        return Response.success(map);
    }

    /**
     * 获取用户的上下文信息
     * @param dUserBaseInfo
     * @return
     */
    @PostMapping("/getWxUserContextData")
    public Response getWxUserContextData(@RequestBody DUser dUser) {
        return clUserService.queryUserData(dUser.getOpenid());
    }
}
