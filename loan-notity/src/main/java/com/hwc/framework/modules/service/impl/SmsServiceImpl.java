package com.hwc.framework.modules.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dahantc.api.sms.json.JSONHttpClient;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.Constant;
import com.hwc.framework.common.SmsTemplate;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DSmsReports;
import com.hwc.framework.modules.domain.DSmsTpl;
import com.hwc.framework.modules.service.SmsService;
import com.hwc.framework.modules.service.SmsTplService;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/18.
 */
@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private IHwcCache           iHwcCache;

    @Value("${sms.account}")
    private String              account;

    @Value("${sms.password}")
    private String              password;

    @Autowired
    private SmsTplService       smsTplService;

    @Override
    public Response sendSms(DSms dSms) {
        try {
            String vCode = dSms.getCode();
            if (FsUtils.strsEmpty(dSms.getPhone()) || !FsUtils.isMobileNum(dSms.getPhone())
                || FsUtils.strsEmpty(dSms.getType())) {
                return Response.fail("参数错误");
            }
            logger.info("=====================notify,"+"type:"+ dSms.getType()+"phone:"+ dSms.getPhone());
            DSmsTpl tpl = smsTplService.findSmsTplByType(dSms.getType());
            if (tpl != null) {
                Response isAllow = this.isAllowSendSms(dSms);
                if (isAllow.getSuccess()) {
                    DSms sms = new DSms();
                    sms.setSendtime(new Date());
                    sms.setPhone(dSms.getPhone());
                    sms.setCode(vCode + "");
                    sms.setState("10");
                    sms.setType(dSms.getType());
                    String content = tpl.getTpl() + vCode;
                    String send = this.sendCode(sms, content);
                    if (send.equals("0")) {
                        return Response.success("短信发送成功");
                    } else {
                        return Response.fail("短信发送失败,请重新获取");
                    }
                } else {
                    return Response.fail(isAllow.getMessage());
                }

            }
            return Response.fail("短信模板不存在");
        } catch (Exception ex) {
            logger.error("发送短信错误:", ex);
            return Response.fail("短信模板不存在");
        }
    }

    @Override
    public Response verifyCode(DSms dSms) {
        if (FsUtils.strsEmpty(dSms.getPhone(), dSms.getType(), dSms.getCode())
            || !FsUtils.isMobileNum(dSms.getPhone())) {
            return Response.fail("参数错误");
        }
        String str = "sms:" + dSms.getType() + ":" + dSms.getPhone();
        String re = iHwcCache.get(str);
        if (FsUtils.strsEmpty(re)) {
            return Response.fail("验证码已经失效");
        }
        DSms sms = JSONObject.parseObject(re, DSms.class);
        if (dSms.getCode().equals(sms.getCode())) {
            iHwcCache.del(str);
            return Response.success("验证成功");
        } else {
            return Response.fail("短信验证码错误");
        }
    }

    private String sendCode(DSms sms, String content) {
        try {
            JSONHttpClient jsonHttpClient = new JSONHttpClient(Constant.SMS_MASTER_URL);
            jsonHttpClient.setRetryCount(1);
            String sendhRes = jsonHttpClient.sendSms(account, password, sms.getPhone(), content,
                Constant.SMS_SIGN, Constant.SMS_SUB_CODE);
            DSmsReports dSmsReports = JSONObject.parseObject(sendhRes, DSmsReports.class);
            logger.info("短信结果：" + sendhRes);
            if (dSmsReports.getResult().equals("0")) { // && dSmsReports.getReports().getStatus().equals("0")
                // 保存
                String str = "sms:" + sms.getType() + ":" + sms.getPhone();
                iHwcCache.set(str, Constant.SMS_TIME_LIMIT, sms);
                // 计数
                String key = "sms:cnt:" + sms.getType() + ":" + sms.getPhone();
                iHwcCache.incr(key);
                iHwcCache.expire(key, FsUtils.getDaySpan(new Date()));
                return "0";
            }
        } catch (Exception e) {
            logger.error("发送短信异常");
        }
        return "-1";
    }
    //
    //    /**
    //     * 生成短信验证码
    //     *
    //     * @return
    //     */
    //    public int createVcode() {
    //        int vcode = (int) (Math.random() * 9000) + 1000;
    //        return vcode;
    //    }

    /**
     * 获取验证码
     *
     * @return
     */
    public String getContent(String type, String code) {
        String message = null;
        if ("register".equals(type)) {
            message = SmsTemplate.REGISTER;
        } else if ("resetPwd".equals(type)) {
            message = SmsTemplate.RESET_PWD;
        } else if ("bindCard".equals(type)) {
            message = SmsTemplate.BIND_CARD;
        } else if ("payCode".equals(type)) {
            message = SmsTemplate.PAY_CODE;
        } else if ("login".equals(type)) {
            message = SmsTemplate.LOGIN;
        }
        return message + code;
    }

    // 判断是否允许发送短信
    private Response isAllowSendSms(DSms dSms) {
        String str = "sms:" + dSms.getType() + ":" + dSms.getPhone();
        String key = "sms:cnt:" + dSms.getType() + ":" + dSms.getPhone();
        if (iHwcCache.exists(key)) {
            Integer cnt = Integer.parseInt(iHwcCache.get(key));
            if (cnt > 5) {
                return Response.fail("短信超过最大次数，请明日再发");
            }
        }
        String re = iHwcCache.get(str);
        if (FsUtils.strsEmpty(re)) {
            return Response.success();
        }
        DSms findSms = JSONObject.parseObject(re, DSms.class);
        Date d1 = findSms.getSendtime();
        Date d2 = this.getNow();
        long diff = d2.getTime() - d1.getTime();
        if (diff > 60 * 1000) {
            return Response.success();
        } else {
            return Response.fail("获取短信过于频繁，请稍后再试");
        }
    }

    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }
}
