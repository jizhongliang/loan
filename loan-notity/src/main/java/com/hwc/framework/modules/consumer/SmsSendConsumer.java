package com.hwc.framework.modules.consumer;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Condition;

/**
 * Created by  on 2017/12/4.
 */
@Component
public class SmsSendConsumer extends OnsConsumerBase {
    private static final Logger logger = LoggerFactory.getLogger(SmsSendConsumer.class);
    @Value("${ons.sms.consumer.sendId}")
    private String id;
    @Value("${ons.sms.consumer.sendTag}")
    private String tag;
    @Value("${ons.topic}")
    private String topic;

    @Autowired
    private SmsService smsService;

    @Override
    protected String getConsumerId() {
        return id;
    }

    @Override
    protected String getTags() {
        return tag;
    }

    @Override
    protected boolean doConsume(HwcOnsContext context) {
        JSONObject jsonObject = new JSONObject();
        logger.info("SmsSendConsumer:{}", jsonObject.toJSONString(context));
        if (context.getData() instanceof DSms) {
            jsonObject.put("type", ((DSms) context.getData()).getType());
            jsonObject.put("code", ((DSms) context.getData()).getCode());
            //jsonObject.put("content", ((DSms) context.getData()).getContent());
            jsonObject.put("phone", ((DSms) context.getData()).getPhone());
        } else {
            logger.info("不是DSms类型");
        }
        String type = jsonObject.getString("type");
        if (type.equals("register") || type.equals("findReg") || type.equals("findPay")||type.equals("manageLogin")) {
            Response response = smsService.sendSms((DSms) context.getData());
            logger.info("发送短信返回值{}", JSONObject.toJSONString(response));
        } else {
            logger.info("非type:{}", type);
        }

        return false;
    }

}
