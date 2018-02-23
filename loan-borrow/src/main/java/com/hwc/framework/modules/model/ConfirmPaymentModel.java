package com.hwc.framework.modules.model;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.common.LianLianConstant;
import com.hwc.framework.common.PaySecurity;
import com.hwc.framework.common.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by  on 2017/11/1.
 * 连连支付 实时付款 - 确认付款
 */
public class ConfirmPaymentModel extends  BasePaymentModel{

    private static final Logger logger = LoggerFactory
            .getLogger(ConfirmPaymentModel.class);

    /**
     * 版本号
     */
    private String api_version;

    /**
     * 验证码
     */
    private String confirm_code;

    /**
     * 加密数据
     */
    private String pay_load;

    /**
     * 连连支付支付单
     */
    private String oid_paybill;



    public ConfirmPaymentModel(String orderNo ) {
        super( );
        this.setOrderNo(orderNo);
        this.setService("confirmPayment");
        this.setApi_version(LianLianConstant.API_VERSION);
        this.setSubUrl("https://instantpay.lianlianpay.com/paymentapi/confirmPayment.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[] { "oid_partner", "sign_type", "sign", "platform",
                "api_version", "no_order", "confirm_code", "notify_url" };
    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "oid_partner", "pay_load" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "sign_type", "sign",
                "oid_partner", "no_order", "oid_paybill" };
    }

    /**
     * 确认付款  签名和其他的不一样
     * @param privateKey
     * @param publicKey
     */
    public void sign(String privateKey,String publicKey) {
        Map<String, Object> map = paramToMap(this.signParamNames());
        this.setSign(SignUtil.genRSASign(JSON.toJSONString(map),privateKey));

        logger.info("使用连连银通公钥加密");
        Map<String, Object> pubMap = paramToMap(this.signParamNames());
        String encryptStr = PaySecurity.encrypt(JSON.toJSONString(pubMap),
                publicKey);

        this.setPay_load(encryptStr);
    }

    /**
     * 获取版本号
     *
     * @return api_version
     */
    public String getApi_version() {
        return api_version;
    }

    /**
     * 设置版本号
     *
     * @param api_version
     */
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    /**
     * 获取验证码
     *
     * @return confirm_code
     */
    public String getConfirm_code() {
        return confirm_code;
    }

    /**
     * 设置验证码
     *
     * @param confirm_code
     */
    public void setConfirm_code(String confirm_code) {
        this.confirm_code = confirm_code;
    }

    /**
     * 获取加密数据
     *
     * @return pay_load
     */
    public String getPay_load() {
        return pay_load;
    }

    /**
     * 设置加密数据
     *
     * @param pay_load
     */
    public void setPay_load(String pay_load) {
        this.pay_load = pay_load;
    }

    /**
     * 获取连连支付支付单
     *
     * @return oid_paybill
     */
    public String getOid_paybill() {
        return oid_paybill;
    }

    /**
     * 设置连连支付支付单
     *
     * @param oid_paybill
     */
    public void setOid_paybill(String oid_paybill) {
        this.oid_paybill = oid_paybill;
    }
}
