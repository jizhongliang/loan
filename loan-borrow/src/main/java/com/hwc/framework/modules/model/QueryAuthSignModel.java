package com.hwc.framework.modules.model;

/**
 * Created by   on 2017/11/1.
 * 用户签约信息查询
 */
public class QueryAuthSignModel extends  BasePaymentModel {

    /**
     * 用户唯一标识
     */
    private String user_id;

    /**
     * 支付方式 D:认证支付
     */
    private String pay_type;

    /**
     * 协议编号，可不填
     */
    private String no_agree;

    /**
     * 银行卡号，可不填
     */
    private String card_no;

    /**
     * 查询偏移量 0 不分页查询
     */
    private String offset;

    /**
     * 总记录数
     */
    private String count;

    /**
     * 签约结果集
     */
    private String agreement_list;






    public QueryAuthSignModel(String orderNo ) {
        super( );
        this.setService("queryAuthSign");
        this.setOrderNo(orderNo);
        this.setPay_type("D");
        this.setOffset("0"); // 偏移量
        this.setSubUrl("https://queryapi.lianlianpay.com/bankcardbindlist.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[] { "oid_partner", "user_id", "platform", "pay_type",
                "sign_type", "sign", "no_agree", "card_no", "offset" };

    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "oid_partner", "user_id", "platform", "pay_type",
                "sign_type", "sign", "no_agree", "card_no", "offset" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "user_id", "count",
                "agreement_list", "sign_type", "sign" };
    }

    /**
     * 获取用户唯一标识
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户唯一标识
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取支付方式D:认证支付
     * @return pay_type
     */
    public String getPay_type() {
        return pay_type;
    }

    /**
     * 设置支付方式D:认证支付
     * @param pay_type
     */
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    /**
     * 获取协议编号，可不填
     * @return no_agree
     */
    public String getNo_agree() {
        return no_agree;
    }

    /**
     * 设置协议编号，可不填
     * @param no_agree
     */
    public void setNo_agree(String no_agree) {
        this.no_agree = no_agree;
    }

    /**
     * 获取银行卡号，可不填
     * @return card_no
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * 设置银行卡号，可不填
     * @param card_no
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    /**
     * 获取查询偏移量0不分页查询
     * @return offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * 设置查询偏移量0不分页查询
     * @param offset
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * 获取总记录数
     * @return count
     */
    public String getCount() {
        return count;
    }

    /**
     * 设置总记录数
     * @param count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 获取签约结果集
     * @return agreement_list
     */
    public String getAgreement_list() {
        return agreement_list;
    }

    /**
     * 设置签约结果集
     * @param agreement_list
     */
    public void setAgreement_list(String agreement_list) {
        this.agreement_list = agreement_list;
    }
}
