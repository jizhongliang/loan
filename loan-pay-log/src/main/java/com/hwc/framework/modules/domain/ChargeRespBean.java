package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/10.
 */
@Data
public class ChargeRespBean {
    /**
     * 银行卡号
     */
    private String bank_card_no;
    /**
     * 支付通道 baofu lianlian
     */
    private String pay_channel;
    /**
     * 请求订单号  用于 订单 查询
     */
    private String order_no;

    /**
     * 原交易商户订单号
     */
    private String orig_trans_id;
    /**
     * 代扣金额
     */
    private Double amount;
    /**
     * 状态
     */
    private String state;
    /**
     * 请求 批次号 用于 订单 查询
     */
    private String batch_id;

    private String repay_id;
    private String remark;

    public String getKey() {
        return "charge_" + order_no + "_" + state;
    }
}
