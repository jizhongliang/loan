package com.hwc.framework.modules.model;

import com.hwc.framework.common.LianLianConstant;

/**
 * Created by   on 2017/11/1.
 * 连连支付 银行卡还款扣款接口
 */
public class RepaymentModel extends  BasePaymentModel {


    /**
     * 用户唯一编号
     */
    private String user_id;

    /**
     * 商户业务类型 虚拟商品:101001 实物商品:109001
     */
    private String busi_partner;

    /**
     * 版本号
     */
    private String api_version;

    /**
     * 商户订单时间
     */
    private String dt_order;

    /**
     * 商品名称
     */
    private String name_goods;

    /**
     * 订单描述
     */
    private String info_order;

    /**
     * 订单金额
     */
    private String money_order;

    /**
     * 订单有效时间
     */
    private String valid_order;

    /**
     * 风控参数
     */
    private String risk_item;

    /**
     * 计划还款日期
     */
    private String schedule_repayment_date;

    /**
     * 还款计划编号
     */
    private String repayment_no;

    /**
     * 支付方式
     */
    private String pay_type;

    /**
     * 签约协议号
     */
    private String no_agree;

    /**
     * 连连支付支付单号
     */
    private String oid_paybill;

    /**
     * 清算日期
     */
    private String settle_date;

    /**
     * 支付结果
     */
    private String result_pay;

    /**
     * 银行编号
     */
    private String bank_code;

    /**
     * 证件类型
     */
    private String id_type;

    /**
     * 证件号码
     */
    private String id_no;

    /**
     * 银行账号姓名
     */
    private String acct_name;

    /**
     * 银行卡号
     */
    private String card_no;

    /**
     * 订单关联Id
     */
    private String correlationID;




    public RepaymentModel(String orderNo ) {
        super( );
        this.setOrderNo(orderNo);
        this.setNo_order(orderNo);
        this.setService("repayment");
        this.setApi_version(LianLianConstant.API_VERSION);
        this.setPay_type(LianLianConstant.PAY_TYPE_CERTIFIED);
        this.setSubUrl("https://repaymentapi.lianlianpay.com/bankcardrepayment.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[] { "platform", "user_id", "oid_partner",
                "sign_type", "sign", "busi_partner", "api_version", "no_order",
                "dt_order", "name_goods", "info_order", "money_order",
                "notify_url", "valid_order", "risk_item",
                "schedule_repayment_date", "repayment_no", "pay_type",
                "no_agree" };
    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "platform", "user_id", "oid_partner",
                "sign_type", "sign", "busi_partner", "api_version", "no_order",
                "dt_order", "name_goods", "info_order", "money_order",
                "notify_url", "valid_order", "risk_item",
                "schedule_repayment_date", "repayment_no", "pay_type",
                "no_agree" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "sign_type", "sign",
                "correlationID", "oid_partner", "no_order", "dt_order",
                "money_order", "oid_paybill", "settle_date", "info_order",
                "repayment_no" };
    }

    @Override
    public String[] respVerifyParamNames() {
        return new String[] { "oid_partner", "sign_type", "sign", "dt_order",
                "no_order", "oid_paybill", "money_order", "result_pay",
                "settle_date", "info_order", "pay_type", "bank_code",
                "no_agree", "id_type", "id_no", "acct_name", "card_no" };
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
     * 获取商户业务类型虚拟商品:101001实物商品:109001
     *
     * @return busi_partner
     */
    public String getBusi_partner() {
        return busi_partner;
    }

    /**
     * 设置商户业务类型虚拟商品:101001实物商品:109001
     *
     * @param busi_partner
     */
    public void setBusi_partner(String busi_partner) {
        this.busi_partner = busi_partner;
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
     * 获取商品名称
     *
     * @return name_goods
     */
    public String getName_goods() {
        return name_goods;
    }

    /**
     * 设置商品名称
     *
     * @param name_goods
     */
    public void setName_goods(String name_goods) {
        this.name_goods = name_goods;
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
     * 获取订单金额
     *
     * @return money_order
     */
    public String getMoney_order() {
        return money_order;
    }

    /**
     * 设置订单金额
     *
     * @param money_order
     */
    public void setMoney_order(String money_order) {
        this.money_order = money_order;
    }

    /**
     * 获取订单有效时间
     *
     * @return valid_order
     */
    public String getValid_order() {
        return valid_order;
    }

    /**
     * 设置订单有效时间
     *
     * @param valid_order
     */
    public void setValid_order(String valid_order) {
        this.valid_order = valid_order;
    }

    /**
     * 获取风控参数
     *
     * @return risk_item
     */
    public String getRisk_item() {
        return risk_item;
    }

    /**
     * 设置风控参数
     *
     * @param risk_item
     */
    public void setRisk_item(String risk_item) {
        this.risk_item = risk_item;
    }

    /**
     * 获取计划还款日期
     *
     * @return schedule_repayment_date
     */
    public String getSchedule_repayment_date() {
        return schedule_repayment_date;
    }

    /**
     * 设置计划还款日期
     *
     * @param schedule_repayment_date
     */
    public void setSchedule_repayment_date(String schedule_repayment_date) {
        this.schedule_repayment_date = schedule_repayment_date;
    }

    /**
     * 获取还款计划编号
     *
     * @return repayment_no
     */
    public String getRepayment_no() {
        return repayment_no;
    }

    /**
     * 设置还款计划编号
     *
     * @param repayment_no
     */
    public void setRepayment_no(String repayment_no) {
        this.repayment_no = repayment_no;
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
     * 获取连连支付支付单号
     *
     * @return oid_paybill
     */
    public String getOid_paybill() {
        return oid_paybill;
    }

    /**
     * 设置连连支付支付单号
     *
     * @param oid_paybill
     */
    public void setOid_paybill(String oid_paybill) {
        this.oid_paybill = oid_paybill;
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
     * 获取证件类型
     *
     * @return id_type
     */
    public String getId_type() {
        return id_type;
    }

    /**
     * 设置证件类型
     *
     * @param id_type
     */
    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    /**
     * 获取证件号码
     *
     * @return id_no
     */
    public String getId_no() {
        return id_no;
    }

    /**
     * 设置证件号码
     *
     * @param id_no
     */
    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    /**
     * 获取银行账号姓名
     *
     * @return acct_name
     */
    public String getAcct_name() {
        return acct_name;
    }

    /**
     * 设置银行账号姓名
     *
     * @param acct_name
     */
    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
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
