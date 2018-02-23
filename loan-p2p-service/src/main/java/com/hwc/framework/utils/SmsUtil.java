package com.hwc.framework.utils;

import com.alibaba.fastjson.JSONObject;
import com.dahantc.api.sms.json.JSONHttpClient;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DSmsReports;
import com.hwc.framework.modules.domain.DSmsTpl;

import cn.freesoft.utils.FsUtils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsUtil {
    public static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    @Value("${sms.account}")
    private static String account;

    @Value("${sms.password}")
    private static String password;
    
//    public static boolean sendMsg(String mobile,String content){
//        JSONHttpClient jsonHttpClient = null;
//        try {
//            jsonHttpClient = new JSONHttpClient(Constant.SMS_MASTER_URL);
//        } catch (org.apache.commons.httpclient.URIException e) {
//            logger.warn("向{}发送短信失败{}",mobile,e.getMessage());
//            return false;
//        }
//        jsonHttpClient.setRetryCount(1);
//        String result = jsonHttpClient.sendSms(account, password,
//                mobile, content, Constant.SMS_SIGN, Constant.SMS_SUB_CODE);
//        logger.warn("向{}发送短信结果{}",mobile,result);
//        return true;
//    }
    
    public static boolean sendMsg(String phone, String content) {
        try {
            JSONHttpClient jsonHttpClient = new JSONHttpClient(Constant.SMS_MASTER_URL);
            jsonHttpClient.setRetryCount(1);
            String sendhRes = jsonHttpClient.sendSms("dh83451", "3ottLV4s", phone, content,
                Constant.SMS_SIGN, Constant.SMS_SUB_CODE);
            DSmsReports dSmsReports = JSONObject.parseObject(sendhRes, DSmsReports.class);
            logger.info("短信结果：" + sendhRes);
            if (dSmsReports.getResult().equals("0")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("发送短信异常");
        }
		return false;
    }
}
