package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DVerify;
import com.hwc.framework.modules.third.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/18.
 */
@RestController
@RequestMapping("/api/verify")
public class VerifyCodeController {
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    private VerifyService verifyService;

    @PostMapping("/getCode")
    public Response sendCode(@RequestBody DVerify dVerify) {
        Response response = this.verifyService.getVerifyCode(dVerify);
        return response;
    }

    @PostMapping("/verifyCode")
    public Response verifyCode(@RequestBody DVerify dVerify) {
        Response response = this.verifyService.verifyCode(dVerify);
        return response;
    }
}
