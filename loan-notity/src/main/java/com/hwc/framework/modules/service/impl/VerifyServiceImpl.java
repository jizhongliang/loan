package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import com.dahantc.api.sms.json.JSONHttpClient;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.Constant;
import com.hwc.framework.common.SmsTemplate;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DSmsReports;
import com.hwc.framework.modules.domain.DSmsTpl;
import com.hwc.framework.modules.domain.DVerify;
import com.hwc.framework.modules.service.SmsService;
import com.hwc.framework.modules.service.SmsTplService;
import com.hwc.framework.modules.service.VerifyService;
import com.hwc.framework.modules.utils.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2017/10/18.
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    private static final Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);

    @Autowired
    private IHwcCache iHwcCache;

    @Autowired
    private VerifyService verifyService;

    @Override
    public Response getVerifyCode(DVerify dVerify) {
       if (!FsUtils.strsEmpty(dVerify.getImei(),dVerify.getType())){
           String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
           dVerify.setvImageCode(verifyCode);
           String image = VerifyCodeUtils.getVerifyCode(verifyCode);
           Map<String,Object> map = new HashMap<>();
           map.put("image",image);
           // 保存
           String key = "verify:" + dVerify.getImei() + ":"+ dVerify.getType();
           iHwcCache.set(key,Constant.VERIFY_TIME_LIMIT,dVerify);
           return Response.success(map);
       }else {
           return Response.fail("获取图片验证码失败");
       }
    }

    @Override
    public Response verifyCode(DVerify request) {
        if (FsUtils.strsEmpty(request.getImei(),request.getType(),request.getvImageCode())){
            return Response.fail("参数错误");
        }
        String key = "verify:" + request.getImei() + ":"+ request.getType();
        String re = iHwcCache.get(key);
        if (FsUtils.strsEmpty(re)) {
            return Response.fail("图片验证码已经失效");
        }
        DVerify dVerify = JSONObject.parseObject(re, DVerify.class);
        if (request.getvImageCode().equalsIgnoreCase(dVerify.getvImageCode())) {
            iHwcCache.del(key);
            return Response.success("验证成功");
        } else {
            return Response.fail("图片验证码错误");
        }
    }
}
