package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/10.
 * 请求支付，查询支付的订单 后用于返回的数据
 */
@Data
public class PayRespBean {
    /**
     * 银行卡号
     */
    private String bank_card_no;
    /**
     * 支付通道
     */
    private String pay_channel;
    /**
     * 请求订单号  用于 订单 查询
     */
    private String order_no;
    /**
     * 代付金额
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
    /**
     * 备注
     */
    private String remark;

    /**
     * bao fu 唯一id号
     */
    private String response_id;

    public String getkey() {
        return "pay_" + order_no + "_" + state;
    }

}
