package com.hwc.framework.modules.model;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.common.LianLianConstant;
import com.hwc.framework.common.PaySecurity;
import com.hwc.framework.common.SignUtil;

import java.util.Map;

/**
 * Created by   on 2017/11/1.
 * .连连支付 实时付款 - 付款交易
 */
public class PaymentModel extends BasePaymentModel {


    /**
     * 版本号
     */
    private String api_version;

    /**
     * 商户付款时间
     */
    private String dt_order;

    /**
     * 付款金额
     */
    private String money_order;

    /**
     * 银行账号
     */
    private String card_no;

    /**
     * 收款人姓名
     */
    private String acct_name;

    /**
     * 收款人银行名称
     */
    private String bank_name;

    /**
     * 付款用途
     */
    private String info_order;

    /**
     * 对公对私标志 0-对私 1-对公
     */
    private String flag_card;

    /**
     * 收款备注 5W及以上必传
     */
    private String memo;

    /**
     * 大额行号 对公字段
     */
    private String prcptcd;

    /**
     * 银行编码 对公字段
     */
    private String bank_code;

    /**
     * 开户行所在市编码 对公字段
     */
    private String city_code;

    /**
     * 开户支行名称 对公字段
     */
    private String brabank_name;

    /**
     * 加密数据
     */
    private String pay_load;

    /**
     * 连连支付支付单
     */
    private String oid_paybill;

    /**
     * 验证码 疑似订单时返回
     */
    private String confirm_code;

    /**
     * 支付结果
     */
    private String result_pay;

    /**
     * 清算日期
     */
    private String settle_date;


    public PaymentModel(String orderNo) {
        super();
        this.setOrderNo(orderNo);
        this.setNo_order(orderNo);
        this.setService("payment");
        this.setApi_version(LianLianConstant.API_VERSION);
        this.setFlag_card(LianLianConstant.PERSONAL_CARD);
        this.setSubUrl("https://instantpay.lianlianpay.com/paymentapi/payment.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[]{"oid_partner", "platform", "api_version", "sign",
                "sign_type", "no_order", "dt_order", "money_order", "card_no",
                "acct_name", "bank_name", "info_order", "flag_card",
                "notify_url", "memo", "prcptcd", "bank_code", "city_code",
                "brabank_name"};
    }

    @Override
    public String[] reqParamNames() {
        return new String[]{"oid_partner", "pay_load"};
    }

    @Override
    public String[] respParamNames() {
        return new String[]{"ret_code", "ret_msg", "sign_type", "sign",
                "oid_partner", "no_order", "oid_paybill", "confirm_code"};
    }

    @Override
    public String[] respVerifyParamNames() {
        return new String[]{"oid_partner", "sign_type", "sign", "no_order",
                "dt_order", "money_order", "oid_paybill", "info_order",
                "result_pay", "settle_date"};
    }


    public void sign(String privateKey, String publicKey) {
        Map<String, Object> map = paramToMap(this.signParamNames());
        this.setSign(SignUtil.genRSASign(JSON.toJSONString(map), privateKey));

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
     * 获取银行账号
     *
     * @return card_no
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * 设置银行账号
     *
     * @param card_no
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    /**
     * 获取收款人姓名
     *
     * @return acct_name
     */
    public String getAcct_name() {
        return acct_name;
    }

    /**
     * 设置收款人姓名
     *
     * @param acct_name
     */
    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    /**
     * 获取收款人银行名称
     *
     * @return bank_name
     */
    public String getBank_name() {
        return bank_name;
    }

    /**
     * 设置收款人银行名称
     *
     * @param bank_name
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * 获取付款用途
     *
     * @return info_order
     */
    public String getInfo_order() {
        return info_order;
    }

    /**
     * 设置付款用途
     *
     * @param info_order
     */
    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }

    /**
     * 获取对公对私标志0-对私1-对公
     *
     * @return flag_card
     */
    public String getFlag_card() {
        return flag_card;
    }

    /**
     * 设置对公对私标志0-对私1-对公
     *
     * @param flag_card
     */
    public void setFlag_card(String flag_card) {
        this.flag_card = flag_card;
    }

    /**
     * 获取收款备注5W及以上必传
     *
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置收款备注5W及以上必传
     *
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取大额行号对公字段
     *
     * @return prcptcd
     */
    public String getPrcptcd() {
        return prcptcd;
    }

    /**
     * 设置大额行号对公字段
     *
     * @param prcptcd
     */
    public void setPrcptcd(String prcptcd) {
        this.prcptcd = prcptcd;
    }

    /**
     * 获取银行编码对公字段
     *
     * @return bank_code
     */
    public String getBank_code() {
        return bank_code;
    }

    /**
     * 设置银行编码对公字段
     *
     * @param bank_code
     */
    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    /**
     * 获取开户行所在市编码对公字段
     *
     * @return city_code
     */
    public String getCity_code() {
        return city_code;
    }

    /**
     * 设置开户行所在市编码对公字段
     *
     * @param city_code
     */
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    /**
     * 获取开户支行名称对公字段
     *
     * @return brabank_name
     */
    public String getBrabank_name() {
        return brabank_name;
    }

    /**
     * 设置开户支行名称对公字段
     *
     * @param brabank_name
     */
    public void setBrabank_name(String brabank_name) {
        this.brabank_name = brabank_name;
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

    /**
     * 获取验证码疑似订单时返回
     *
     * @return confirm_code
     */
    public String getConfirm_code() {
        return confirm_code;
    }

    /**
     * 设置验证码疑似订单时返回
     *
     * @param confirm_code
     */
    public void setConfirm_code(String confirm_code) {
        this.confirm_code = confirm_code;
    }

    /**
     * 获取支付结果
     *
     * @return result_pay
     */
    public String getResult_pay() {
        return result_pay;
    }

    /**
     * 设置支付结果
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
}
