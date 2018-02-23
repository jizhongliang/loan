package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/10.
 */
@Data
public class BankCardAuthoriztionRespBean {
    /**
     * 银行卡名
     */
    private String bank_name;
    /**
     * 银行卡识别码 标准识别码
     */
    private String bank_code;
    /**
     * 银行卡号
     */
    private String bank_card_no;
    /**
     * 绑定的通道 baofu lianlian
     */
    private String pay_channel;
    /**
     * 绑定后的协议号
     */
    private String agree_no;
    /**
     * 请求订单号
     */
    private String order_no;
    /**
     * 错误代码
     */
    private String state;
}
