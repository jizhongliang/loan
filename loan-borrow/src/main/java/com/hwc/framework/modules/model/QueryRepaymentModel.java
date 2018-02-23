package com.hwc.framework.modules.model;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.common.LianLianConstant;
import com.hwc.framework.common.SignUtil;

import java.util.Map;

/**
 * Created by   on 2017/11/1.
 */
public class QueryRepaymentModel extends BasePaymentModel {
    /**
     * 商户订单时间
     */
    private String dt_order;

    /**
     * 查询版本号
     */
    private String query_version;


    /**
     * 支付结果   SUCCESS:成功 WAITING:待支付 PROCESSING:银行处理中 REFUND:退款 FAILUER:失败
     */
    private String result_pay;

    /**
     * 连连支付 支付单
     */
    private String oid_paybill;

    /**
     * 交易金额
     */
    private String money_order;

    /**
     * 清算日期
     */
    private String settle_date;

    /**
     * 订单描述
     */
    private String info_order;

    /**
     * 支付方式
     */
    private String pay_type;

    /**
     * 银行编号
     */
    private String bank_code;

    /**
     * 银行名称
     */
    private String bank_name;

    /**
     * 支付备注
     */
    private String memo;

    /**
     * 银行卡号
     */
    private String card_no;


    public QueryRepaymentModel() {
        super();

    }

    public QueryRepaymentModel(String orderNo) {
        super();
        this.setOrderNo(orderNo);
        this.setQuery_version(LianLianConstant.API_VERSION);
        this.setSubUrl("https://queryapi.lianlianpay.com/orderquery.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[]{"oid_partner", "sign_type", "sign", "no_order",
                "dt_order", "oid_paybill", "query_version"};
    }

    @Override
    public String[] reqParamNames() {
        return new String[]{"oid_partner", "sign_type", "sign", "no_order",
                "dt_order", "oid_paybill", "query_version"};
    }

    public String[] verifySignParamNames() {
        return new String[]{"ret_code", "ret_msg", "sign_type", "sign",
                "result_pay", "oid_partner", "dt_order", "no_order",
                "oid_paybill", "money_order", "settle_date", "info_order",
                "pay_type", "bank_code", "memo"};
    }

    @Override
    public String[] respParamNames() {
        return new String[]{"ret_code", "ret_msg", "sign_type", "sign",
                "result_pay", "oid_partner", "dt_order", "no_order",
                "oid_paybill", "money_order", "settle_date", "info_order",
                "pay_type", "bank_code", "bank_name", "memo", "card_no"};
    }


    public boolean verfiySign(BasePaymentModel model, String publicKey) {
        Map<String, Object> map = paramToMap(this.verifySignParamNames());
        String verifyJsonStr = SignUtil.genSignData(JSON.toJSONString(map));
        return SignUtil.checksign(publicKey, verifyJsonStr,
                model.getSign());
    }

    /**
     * 获取商户订单时间
     *
     * @return dt_order
     */
    public String getDt_order() {
        return dt_order;
    }

    /**
     * 设置商户订单时间
     *
     * @param dt_order
     */
    public void setDt_order(String dt_order) {
        this.dt_order = dt_order;
    }

    /**
     * 获取查询版本号
     *
     * @return query_version
     */
    public String getQuery_version() {
        return query_version;
    }

    /**
     * 设置查询版本号
     *
     * @param query_version
     */
    public void setQuery_version(String query_version) {
        this.query_version = query_version;
    }

    /**
     * 获取支付结果   SUCCESS:成功 WAITING:待支付 PROCESSING:银行处理中 REFUND:退款 FAILUER:失败
     *
     * @return result_pay
     */
    public String getResult_pay() {
        return result_pay;
    }

    /**
     * 设置支付结果   SUCCESS:成功 WAITING:待支付 PROCESSING:银行处理中 REFUND:退款 FAILUER:失败
     *
     * @param result_pay
     */
    public void setResult_pay(String result_pay) {
        this.result_pay = result_pay;
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
     * 获取交易金额
     *
     * @return money_order
     */
    public String getMoney_order() {
        return money_order;
    }

    /**
     * 设置交易金额
     *
     * @param money_order
     */
    public void setMoney_order(String money_order) {
        this.money_order = money_order;
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
     * 获取银行编号
     *
     * @return bank_code
     */
    public String getBank_code() {
        return bank_code;
    }

    /**
     * 设置银行编号
     *
     * @param bank_code
     */
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    /**
     * 获取银行名称
     *
     * @return bank_name
     */
    public String getBank_name() {
        return bank_name;
    }

    /**
     * 设置银行名称
     *
     * @param bank_name
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * 获取支付备注
     *
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置支付备注
     *
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取银行卡号
     *
     * @return card_no
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * 设置银行卡号
     *
     * @param card_no
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }
}
