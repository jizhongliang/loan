package com.hwc.framework.modules.model;

/**
 * Created by  on 2017/11/2.
 */
public class AgreementList{

    /**
     * 签约协议号
     */
    private String no_agree;

    /**
     * 银行卡号后四位
     */
    private String card_no;

    /**
     * 银行编号
     */
    private String bank_code;

    /**
     * 银行名称
     */
    private String bank_name;

    /**
     * 银行卡类型 2 储蓄卡 3 信用卡
     */
    private String card_type;

    /**
     * 手机号码
     */
    private String bind_mobile;

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
     * 获取银行卡号后四位
     *
     * @return card_no
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * 设置银行卡号后四位
     *
     * @param card_no
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
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
     * 获取银行卡类型2储蓄卡3信用卡
     *
     * @return card_type
     */
    public String getCard_type() {
        return card_type;
    }

    /**
     * 设置银行卡类型2储蓄卡3信用卡
     *
     * @param card_type
     */
    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    /**
     * 获取手机号码
     *
     * @return bind_mobile
     */
    public String getBind_mobile() {
        return bind_mobile;
    }

    /**
     * 设置手机号码
     *
     * @param bind_mobile
     */
    public void setBind_mobile(String bind_mobile) {
        this.bind_mobile = bind_mobile;
    }
}