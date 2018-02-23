/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.controller.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.ServiceException;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.service.UserAuthService;

/**
 * 微信用户认证相关controller
 * @author jinlilong
 * @version $Id: UserAuthController.java, v 0.1 2018年2月1日 上午9:37:08 jinlilong Exp $
 */
@RestController
@RequestMapping("/api/user/auth/wx")
public class UserAuthController {

    /**  日志*/
    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    /**  用户认证相关service*/
    @Autowired
    private UserAuthService     userAuthService;

    /**
     * 微信端用户实名认证
     * @param dUserBaseInfo
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/authentication")
    public Response<String> authentication(@RequestBody DUserBaseInfo dUserBaseInfo) {
        try {
            return userAuthService.authentication(dUserBaseInfo);
        } catch (ServiceException e) {
            logger.error("微信端用户实名认证=>" + e.getErrorMessage());
            return Response.fail(e.getErrorMessage());
        }
    }

}
