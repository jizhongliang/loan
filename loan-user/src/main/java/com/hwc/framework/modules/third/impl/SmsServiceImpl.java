package com.hwc.framework.modules.third.impl;

import com.hwc.framework.modules.domain.DSysUser;
import com.hwc.framework.modules.model.SysUser;
import com.hwc.framework.modules.service.SysUserService;
import com.hwc.loan.sdk.admin.domain.DSysUserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.service.ClUserEquipmentInfoService;
import com.hwc.framework.modules.service.ClUserOtherInfoService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.third.SmsService;

import cn.freesoft.utils.FsUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 2017/12/4.
 */
@Component
public class SmsServiceImpl implements SmsService {
    private static final Logger        logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    @Autowired
    private IHwcCache                  cache;

    @Autowired
    @Qualifier("smsProducer")
    private HwcOnsProducer             producer;

    @Autowired
    private ClUserService              userService;

    @Autowired
    private ClUserEquipmentInfoService equipmentInfoService;

    @Autowired
    private ClUserOtherInfoService     clUserOtherInfoService;

    @Autowired
    private SysUserService sysUserService;

    public Response verifyCode(DSms dSms) {
        if (FsUtils.strsEmpty(dSms.getType(), dSms.getPhone())) {
            return Response.fail("参数不能为空");
        }
        String key = "sms:" + dSms.getType() + ":phone:" + dSms.getPhone();

        String val = cache.get(key);

        if (FsUtils.strsEmpty(val)) {
            logger.info("key:{}", key);
            //return Response.fail("短信验证码已失效");
            return Response.fail("短信验证码错误");
        }
        DSms sms = JSONObject.parseObject(val, DSms.class);
        if (dSms.getCode().equals(sms.getCode())) {
            cache.del(key);
            return Response.success("验证成功");
        } else {
            return Response.fail("短信验证码错误");
        }
    }

    @Override
    public Response sendSms(DSms dSms) {
        if (FsUtils.strsEmpty(dSms.getType(), dSms.getPhone())
                || FsUtils.strsEmpty(dSms.getType())) {
            return Response.fail("参数错误");
        }
        if (!FsUtils.isMobileNum(dSms.getPhone())) {
            return Response.fail("手机号格式不正确");
        }
        //        List<ClUserEquipmentInfo> equipmentInfos = equipmentInfoService.getEqInfo(dSms.getE());
        //        if (FsUtils.strsEmpty(equipmentInfos) || equipmentInfos.isEmpty()) {
        //            logger.error("非法调用短信{}", JSONObject.toJSONString(dSms));
        //            return Response.fail("参数不能为空");
        //        }
        //        String eqkey = "sms:" + dSms.getType() + ":" + dSms.getE();
        //        if (cache.exists(eqkey)) {
        //            return Response.fail("请勿频繁操作");
        //        } else {
        //            cache.set(eqkey, 60L, dSms);
        //        }
        DUser user = userService.getUserByLoginName(dSms.getPhone());

        //若是忘了密码请求发送验证码时，先校验用户是否存在。不存在则直接抛出用户不存在，不发生验证码
        if (user == null) {
            if ("findPay".equals(dSms.getType()) || "findReg".equals(dSms.getType())) {
                return Response.fail("该手机号未注册，请确认手机号输入是否正确");
            }
        }
        if (!FsUtils.strsEmpty(dSms.getUserId())) {
            if (user == null || !dSms.getUserId().equals(user.getId())) {
                return Response.fail("该手机号不是当前账号的手机号");
            }
        }

        if (FsUtils.strsEmpty(user) || !"register".equals(dSms.getType())) {
            String key = "sms:" + dSms.getType() + ":phone:" + dSms.getPhone();
            dSms.setCode(FsUtils.randomNumeric(4));
            cache.set(key, 60L * 5L, dSms);
            producer.sendJson("sms", key, null, dSms);
            return Response.success("短信发送成功");
        } else {
            return Response.fail("该手机号码已经注册");
        }
    }

    @Override
    public Response sendSmsWx(DSms dSms) {
        if (FsUtils.strsEmpty(dSms.getType(), dSms.getPhone())
                || FsUtils.strsEmpty(dSms.getType())) {
            return Response.fail("参数错误");
        }
        if (!FsUtils.isMobileNum(dSms.getPhone())) {
            return Response.fail("手机号格式不正确");
        }
        //        List<ClUserEquipmentInfo> equipmentInfos = equipmentInfoService.getEqInfoByPhoneMark(dSms.getE());
        //        if (FsUtils.strsEmpty(equipmentInfos) || equipmentInfos.isEmpty()) {
        //            logger.error("非法调用短信{}", JSONObject.toJSONString(dSms));
        //            return Response.fail("参数不能为空");
        //        }
        //        String eqkey = "sms:" + dSms.getType() + ":" + dSms.getE();
        //        if (cache.exists(eqkey)) {
        //            return Response.fail("请勿频繁操作");
        //        } else {
        //            cache.set(eqkey, 60L, dSms);
        //        }
        DUser user = userService.getUserByLoginName(dSms.getPhone());

        //若是忘了密码请求发送验证码时，先校验用户是否存在。不存在则直接抛出用户不存在，不发生验证码
        if (user == null) {
            if ("findPay".equals(dSms.getType()) || "findReg".equals(dSms.getType())) {
                return Response.fail("该手机号未注册，请确认手机号输入是否正确");
            }
        }
        if (FsUtils.strsEmpty(user) || !"register".equals(dSms.getType())) {
            String key = "sms:" + dSms.getType() + ":phone:" + dSms.getPhone();
            dSms.setCode(FsUtils.randomNumeric(4));
            cache.set(key, 60L * 5L, dSms);
            producer.sendJson("sms", key, null, dSms);
            return Response.success("短信发送成功");
        } else {
            return Response.fail("该手机号码已经注册");
        }
    }

    @Override
    public Response sendSmsManage(DSms dSms) {
        if (FsUtils.strsEmpty(dSms.getType(), dSms.getPhone())
                || FsUtils.strsEmpty(dSms.getType())) {
            return Response.fail("参数错误");
        }
        if (!FsUtils.isMobileNum(dSms.getPhone())) {
            return Response.fail("手机号格式不正确");
        }

        //DUser user = userService.getUserByLoginName(dSms.getPhone());
        Map<String, String> param = new HashMap<String, String>();
        param.put("phone", dSms.getPhone());
        DSysUser sysUser =sysUserService.getOneUserByMap(param);

        if (sysUser == null) {
            return Response.fail("请输入正确的账号密码");
        }

        logger.info("sendCodeManage,type:"+dSms.getType());
        if ("manageLogin".equals(dSms.getType())) {
            String key = "sms:" + dSms.getType() + ":phone:" + dSms.getPhone();
            dSms.setCode(FsUtils.randomNumeric(4));
            logger.info("sendCodeManage, phone:"+dSms.getPhone()+"code:"+dSms.getCode());
            cache.set(key, 60L * 5L, dSms);
            producer.sendJson("sms", key, null, dSms);
            return Response.success("短信发送成功");
        } else {
            return Response.fail("获取短信验证码失败");
        }
    }
}
