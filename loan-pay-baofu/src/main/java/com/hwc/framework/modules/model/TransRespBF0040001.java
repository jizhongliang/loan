package com.hwc.framework.modules.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by   on 2017/11/8.
 */
@XStreamAlias("trans_reqData")
public class TransRespBF0040001 {

    private String trans_orderid;//宝付订单号
    private String trans_batchid;//宝付批次号
    private String trans_no;// 商户订单号
    private String trans_money;// 转账金额
    private String to_acc_name;// 收款人姓名
    private String to_acc_no;// 收款人银行帐号
    private String to_bank_name;// 收款人银行名称
    private String to_pro_name;// 收款人开户行省名
    private String to_city_name;// 收款人开户行市名
    private String to_acc_dept;// 收款人开户行机构名
    private String trans_card_id;//身份证号码
    private String trans_mobile;//手机号
    private String trans_summary;//摘要

    public String getTrans_orderid() {
        return trans_orderid;
    }

    public void setTrans_orderid(String trans_orderid) {
        this.trans_orderid = trans_orderid;
    }

    public String getTrans_batchid() {
        return trans_batchid;
    }

    public void setTrans_batchid(String trans_batchid) {
        this.trans_batchid = trans_batchid;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }

    public String getTrans_money() {
        return trans_money;
    }

    public void setTrans_money(String trans_money) {
        this.trans_money = trans_money;
    }

    public String getTo_acc_name() {
        return to_acc_name;
    }

    public void setTo_acc_name(String to_acc_name) {
        this.to_acc_name = to_acc_name;
    }

    public String getTo_acc_no() {
        return to_acc_no;
    }

    public void setTo_acc_no(String to_acc_no) {
        this.to_acc_no = to_acc_no;
    }

    public String getTo_bank_name() {
        return to_bank_name;
    }

    public void setTo_bank_name(String to_bank_name) {
        this.to_bank_name = to_bank_name;
    }

    public String getTo_pro_name() {
        return to_pro_name;
    }

    public void setTo_pro_name(String to_pro_name) {
        this.to_pro_name = to_pro_name;
    }

    public String getTo_city_name() {
        return to_city_name;
    }

    public void setTo_city_name(String to_city_name) {
        this.to_city_name = to_city_name;
    }

    public String getTo_acc_dept() {
        return to_acc_dept;
    }

    public void setTo_acc_dept(String to_acc_dept) {
        this.to_acc_dept = to_acc_dept;
    }

    public String getTrans_summary() {
        return trans_summary;
    }

    public void setTrans_summary(String trans_summary) {
        this.trans_summary = trans_summary;
    }

    public String getTrans_card_id() {
        return trans_card_id;
    }

    public void setTrans_card_id(String trans_card_id) {
        this.trans_card_id = trans_card_id;
    }

    public String getTrans_mobile() {
        return trans_mobile;
    }

    public void setTrans_mobile(String trans_mobile) {
        this.trans_mobile = trans_mobile;
    }
}