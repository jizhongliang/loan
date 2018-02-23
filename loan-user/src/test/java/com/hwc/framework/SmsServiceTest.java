/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.third.SmsService;

/**
 * 
 * @author jinlilong
 * @version $Id: SmsServiceTest.java, v 0.1 2018年1月5日 上午9:48:12 jinlilong Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsServiceTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void sendSmsWx() {
        DSms dSms = new DSms();
        dSms.setPhone("13757164151");
        dSms.setType("findReg");
        Response response = smsService.sendSmsWx(dSms);
        System.out.println(JSONObject.toJSON(response));

    }

}
