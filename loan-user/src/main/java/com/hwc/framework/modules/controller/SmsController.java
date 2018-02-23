package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.third.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by  on 2017/12/4.
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {
    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @PostMapping("/sendCode")
    public Response sendCode( @RequestBody DSms dSms) {
       // dSms.setE(e);
        Response response = this.smsService.sendSms(dSms);
        return response;
    }

    @PostMapping("/sendCodeWx")
    public Response sendCodeWX(  @RequestBody DSms dSms) {
      //  dSms.setE(e);
        Response response = this.smsService.sendSmsWx(dSms);
        return response;
    }

    @PostMapping("/verifyCode")
    public Response verifyCode(@RequestBody DSms dSms) {
        Response response = this.smsService.verifyCode(dSms);
        return response;
    }

    @PostMapping("/sendCodeManage")
    public Response sendCodeManage(@RequestBody DSms dSms) {
        Response response = this.smsService.sendSmsManage(dSms);
        return response;
    }
}
