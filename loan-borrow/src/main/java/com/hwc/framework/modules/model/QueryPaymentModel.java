package com.hwc.framework.modules.model;

import com.hwc.framework.common.LianLianConstant;

/**
 * Created by   on 2017/11/1.
 * 连连支付 实时付款 - 付款结果查询
 */
public class QueryPaymentModel extends BasePaymentModel {


    /**
     * 版本号
     */
    private String api_version;

    /**
     * 连连支付支付单
     */
    private String oid_paybill;

    /**
     * 商户付款时间
     */
    private String dt_order;

    /**
     * 付款金额
     */
    private String money_order;

    /**
     * 付款结果
     */
    private String result_pay;

    /**
     * 清算日期
     */
    private String settle_date;

    /**
     * 订单描述
     */
    private String info_order;


    public QueryPaymentModel(String orderNo) {
        super();
        this.setService("queryPayment");
        this.setOrderNo(orderNo);
        this.setApi_version(LianLianConstant.API_VERSION);
        this.setSubUrl("https://instantpay.lianlianpay.com/paymentapi/queryPayment.htm");

    }

    @Override
    public String[] signParamNames() {
        return new String[]{"oid_partner", "platform", "api_version",
                "no_order", "oid_paybill", "sign_type", "sign"};
    }

    @Override
    public String[] reqParamNames() {
        return new String[]{"oid_partner", "platform", "api_version",
                "no_order", "oid_paybill", "sign_type", "sign"};
    }

    @Override
    public String[] respParamNames() {
        return new String[]{"ret_code", "ret_msg", "sign_type", "sign",
                "oid_partner", "no_order", "dt_order", "money_order",
                "oid_paybill", "result_pay", "settle_date", "info_order"};
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

    /**
     * 获取商户付款时间
     *
     * @return dt_order
     */
    public String getDt_order() {
        return dt_order;
    }

    /**
     * 设置商户付款时间
     *
     * @param dt_order
     */
    public void setDt_order(String dt_order) {
        this.dt_order = dt_order;
    }

    /**
     * 获取付款金额
     *
     * @return money_order
     */
    public String getMoney_order() {
        return money_order;
    }

    /**
     * 设置付款金额
     *
     * @param money_order
     */
    public void setMoney_order(String money_order) {
        this.money_order = money_order;
    }

    /**
     * 获取付款结果
     *
     * @return result_pay
     */
    public String getResult_pay() {
        return result_pay;
    }

    /**
     * 设置付款结果
     *
     * @param result_pay
     */
    public void setResult_pay(String result_pay) {
        this.result_pay = result_pay;
    }

    /**
     * 获取清算日期
     *
     * @return settle_date
     */
    public String getSettle_date() {
        return settle_date;
    }

    /**
     * 设置清算日期
     *
     * @param settle_date
     */
    public void setSettle_date(String settle_date) {
        this.settle_date = settle_date;
    }

    /**
     * 获取订单描述
     *
     * @return info_order
     */
    public String getInfo_order() {
        return info_order;
    }

    /**
     * 设置订单描述
     *
     * @param info_order
     */
    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }
}
