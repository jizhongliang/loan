package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DSmsTpl;
import com.hwc.framework.modules.service.SmsService;
import com.hwc.framework.modules.service.SmsTplService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2017/10/18.
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {
    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @PostMapping("/sendCode")
    public Response sendCode(@RequestBody DSms dSms) {
        Response response = this.smsService.sendSms(dSms);
        return response;
    }

    @PostMapping("/verifyCode")
    public Response verifyCode(@RequestBody DSms dSms) {
        Response response = this.smsService.verifyCode(dSms);
        return response;
    }
}
