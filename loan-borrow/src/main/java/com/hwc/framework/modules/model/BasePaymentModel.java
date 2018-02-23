package com.hwc.framework.modules.model;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.common.LianLianConstant;
import com.hwc.framework.common.ReflectUtil;
import com.hwc.framework.common.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 2017/11/1.
 */
public class BasePaymentModel {

    private static final Logger logger = LoggerFactory.getLogger(BasePaymentModel.class);

    /**
     * 交易金额
     */
    private double amount;

    /**
     * 接口服务Service 供请求记录中区分接口使用
     */
    private String service;

    /**
     * 请求订单号 供请求记录中区分订单使用
     */
    private String orderNo;

    /**
     * 商户编号
     */
    private String oid_partner;

    /**
     * 商户付款流水号
     */
    private String no_order;

    /**
     * 签名方式
     */
    private String sign_type;

    /**
     * 签名方
     */
    private String sign;

    /**
     * 平台来源
     */
    private String platform;

    /**
     * 服务器异步通知地址
     */
    private String notify_url;

    /**
     * 交易结果code
     */
    public String ret_code;

    /**
     * 交易结果描述
     */
    public String ret_msg;

    /**
     * 提交地址
     */
    private String subUrl;

    /**
     * 请求签名参数数组
     *
     * @return
     */
    public String[] signParamNames() {
        return new String[0];
    }

    /**
     * 请求参数数组
     *
     * @return
     */
    public String[] reqParamNames() {
        return new String[0];
    }

    /**
     * 响应参数数组
     *
     * @return
     */
    public String[] respParamNames() {
        return new String[0];
    }

    /**
     * 响应验证参数数组
     *
     * @return
     */
    public String[] respVerifyParamNames() {
        return new String[0];
    }

    public BasePaymentModel() {
        super();
        this.setSign_type(LianLianConstant.SIGN_TYPE);
    }

    /**
     * 签名
     *
     * @param priateKey 是否需要连连银通公钥加密
     */
    public void sign(String priateKey) {
        Map<String, Object> map = paramToMap(this.signParamNames());
        this.setSign(SignUtil.genRSASign(JSON.toJSONString(map), priateKey));
    }

    /**
     * 检查返回code
     *
     * @return
     */
    public boolean checkReturn() {
        if (FsUtils.strsEmpty(this.getRet_code())
                && LianLianConstant.RESPONSE_SUCCESS_CODE.equals(this.getRet_code())) {
            return true;
        }
        return false;
    }

    /**
     * 同步响应验签
     *
     * @param model
     * @return
     */
    public boolean verfiySign(BasePaymentModel model, String publicKey) {
        Map<String, Object> map = paramToMap(this.respParamNames());
        String verifyJsonStr = SignUtil.genSignData(JSON.toJSONString(map));
        FsUtils.log_info("验签明文：" + verifyJsonStr);
        return SignUtil.checksign(publicKey, verifyJsonStr, model.getSign());
    }

    /**
     * 异步通知验签
     *
     * @param model
     * @return
     */
    public boolean checkSign(BasePaymentModel model, String publicKey) {
        Map<String, Object> map = paramToMap(this.respVerifyParamNames());
        String verifyJsonStr = SignUtil.genSignData(JSON.toJSONString(map));
        FsUtils.log_info("验签明文：" + verifyJsonStr);
        if (model.sign_type.equals("MD5")) {
            return SignUtil.checkMD5sign(verifyJsonStr, model.getSign());
        } else
            return SignUtil.checksign(publicKey, verifyJsonStr, model.getSign());
    }

    /**
     * 同步响应参数赋值
     *
     * @param respMsg
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public void response(String respMsg) throws UnsupportedEncodingException, IOException {
        JSONObject resultArray = JSON.parseObject(respMsg);
        StringBuilder verifyStr = new StringBuilder(50);
        Map<String, Field> fs = ReflectUtil.getClassField(getClass());
        for (String name : this.respParamNames()) {
            String value = resultArray.getString(name);
            verifyStr.append(name + " : " + value + ",");
            if (FsUtils.strsEmpty(value)) {
                continue;
            }
            Field f = fs.get(FsUtils.firstCharLowerCase(name));
            ReflectUtil.invokeSetMethod(f.getDeclaringClass(), this, f.getName(), f.getType(), value);
        }
        logger.info("响应参数： " + verifyStr);
    }

    /**
     * params 数组转 map
     *
     * @param params
     * @return
     */
    public Map<String, Object> paramToMap(String[] params) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String name : params) {
            Object result = ReflectUtil.invokeGetMethod(getClass(), this, name);
            if (null == result) {
                continue;
            }
            map.put(name, result);
        }
        return map;
    }

    /**
     * 获取交易金额
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置交易金额
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取接口服务Service供请求记录中区分接口使用
     *
     * @return service
     */
    public String getService() {
        return service;
    }

    /**
     * 设置接口服务Service供请求记录中区分接口使用
     *
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 获取请求订单号供请求记录中区分订单使用
     *
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置请求订单号供请求记录中区分订单使用
     *
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取商户编号
     *
     * @return oid_partner
     */
    public String getOid_partner() {
        return oid_partner;
    }

    /**
     * 设置商户编号
     *
     * @param oid_partner
     */
    public void setOid_partner(String oid_partner) {
        this.oid_partner = oid_partner;
    }

    /**
     * 获取商户付款流水号
     *
     * @return no_order
     */
    public String getNo_order() {
        return no_order;
    }

    /**
     * 设置商户付款流水号
     *
     * @param no_order
     */
    public void setNo_order(String no_order) {
        this.no_order = no_order;
    }

    /**
     * 获取签名方式
     *
     * @return sign_type
     */
    public String getSign_type() {
        return sign_type;
    }

    /**
     * 设置签名方式
     *
     * @param sign_type
     */
    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    /**
     * 获取签名方
     *
     * @return sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置签名方
     *
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取平台来源
     *
     * @return platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置平台来源
     *
     * @param platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 获取服务器异步通知地址
     *
     * @return notify_url
     */
    public String getNotify_url() {
        return notify_url;
    }

    /**
     * 设置服务器异步通知地址
     *
     * @param notify_url
     */
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    /**
     * 获取交易结果code
     *
     * @return ret_code
     */
    public String getRet_code() {
        return ret_code;
    }

    /**
     * 设置交易结果code
     *
     * @param ret_code
     */
    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    /**
     * 获取交易结果描述
     *
     * @return ret_msg
     */
    public String getRet_msg() {
        return ret_msg;
    }

    /**
     * 设置交易结果描述
     *
     * @param ret_msg
     */
    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    /**
     * 获取提交地址
     *
     * @return subUrl
     */
    public String getSubUrl() {
        return subUrl;
    }

    /**
     * 设置提交地址
     *
     * @param subUrl
     */
    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

}