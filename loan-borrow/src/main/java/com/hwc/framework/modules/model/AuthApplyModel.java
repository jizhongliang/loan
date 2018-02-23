package com.hwc.framework.modules.model;

import com.hwc.framework.common.LianLianConstant;

/**
 * Created by   on 2017/11/1.
 * 连连支付 分期付 - 授权申请接口
 */
public class AuthApplyModel extends  BasePaymentModel {
    /**
     * 用户唯一编号
     */
    private String user_id;

    /**
     * 版本号
     */
    private String api_version;

    /**
     * 还款计划　JSON格式
     */
    private String repayment_plan;

    /**
     * 还款编号
     */
    private String repayment_no;

    /**
     * 短信参数
     */
    private String sms_param;

    /**
     * 支付方式
     */
    private String pay_type;

    /**
     * 签约协议号
     */
    private String no_agree;

    /**
     * 授权验证Token (现在已取消验证)
     */
    private String token;

    /**
     * 订单关联Id
     */
    private String correlationID;



    public AuthApplyModel(String orderNo) {
        super();
        this.setService("authApply");
        this.setOrderNo(orderNo);
        this.setApi_version(LianLianConstant.API_VERSION);
        this.setPay_type(LianLianConstant.PAY_TYPE_CERTIFIED);
        this.setSubUrl("https://repaymentapi.lianlianpay.com/agreenoauthapply.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[] { "platform", "user_id", "oid_partner",
                "sign_type", "sign", "api_version", "repayment_plan",
                "repayment_no", "sms_param", "pay_type", "no_agree" };
    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "platform", "user_id", "oid_partner",
                "sign_type", "sign", "api_version", "repayment_plan",
                "repayment_no", "sms_param", "pay_type", "no_agree" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "token", "sign_type",
                "sign","correlationID", "oid_partner", "repayment_no" };
    }


    /**
     * 获取用户唯一编号
     *
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户唯一编号
     *
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
     * 获取还款计划　JSON格式
     *
     * @return repayment_plan
     */
    public String getRepayment_plan() {
        return repayment_plan;
    }

    /**
     * 设置还款计划　JSON格式
     *
     * @param repayment_plan
     */
    public void setRepayment_plan(String repayment_plan) {
        this.repayment_plan = repayment_plan;
    }

    /**
     * 获取还款编号
     *
     * @return repayment_no
     */
    public String getRepayment_no() {
        return repayment_no;
    }

    /**
     * 设置还款编号
     *
     * @param repayment_no
     */
    public void setRepayment_no(String repayment_no) {
        this.repayment_no = repayment_no;
    }

    /**
     * 获取短信参数
     *
     * @return sms_param
     */
    public String getSms_param() {
        return sms_param;
    }

    /**
     * 设置短信参数
     *
     * @param sms_param
     */
    public void setSms_param(String sms_param) {
        this.sms_param = sms_param;
    }

    /**
     * 获取支付方式
     *
     * @return pay_type
     */
    public String getPay_type() {
        return pay_type;
    }

    /**
     * 设置支付方式
     *
     * @param pay_type
     */
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    /**
     * 获取签约协议号
     *
     * @return no_agree
     */
    public String getNo_agree() {
        return no_agree;
    }

    /**
     * 设置签约协议号
     *
     * @param no_agree
     */
    public void setNo_agree(String no_agree) {
        this.no_agree = no_agree;
    }

    /**
     * 获取授权验证Token(现在已取消验证)
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置授权验证Token(现在已取消验证)
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取订单关联Id
     * @return correlationID
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * 设置订单关联Id
     * @param correlationID
     */
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }


}
