package com.hwc.framework.modules.model;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class PreCardBindData {
    /**
     * 交易子类
     */
    private String txn_sub_type="11";
    /**
     * 接入类型
     */
    private String biz_type;
    /**
     * 终端号
     */
    private String terminal_id;
    /**
     * 商户号
     */
    private String member_id;
    /**
     * 商户流水号
     */
    private String trans_serial_no;
    /**
     * 商户订单号
     */
    private String trans_id;
    /**
     * 绑定卡号
     */
    private String acc_no;
    /**
     * 身份证类型
     */
    private String id_card_type="01";
    /**
     * 身份证号
     */
    private String id_card;
    /**
     * 持卡人姓名
     */
    private String id_holder;
    /**
     * 银行卡绑定手机号
     */
    private String mobile;
    /**
     * 卡有效期
     */
    private String valid_date="";
    /**
     * 卡安全码
     */
    private String valid_no="";
    /**
     * 银行编码
     */
    private String card_type="";
    /**
     * 订单日期 14 位定长。
     格式：年年年年月月日日时时分分秒秒
     */
    private String trade_date="";
    /**
     * 附加字段
     */
    private String additional_info="";
    /**
     * 请求方保留域
     */
    private String req_reserved="";

    private String acc_pwd="";




}
