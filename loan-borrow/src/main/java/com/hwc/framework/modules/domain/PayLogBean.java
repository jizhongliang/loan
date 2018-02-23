package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/1.
 */
@Data
public class PayLogBean {
    @ApiModelProperty("交易订单号")
    private String orderNo;
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("借款Id")
    private Long borrowId;
    @ApiModelProperty("还款计划ID")
    private Long repayId;
    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private Double amount;
    /**
     *银行卡号
     */
    @ApiModelProperty("银行卡号")
    private String bankCardNo;
    /**
     *银行名
     */
    @ApiModelProperty("银行名")
    private String bankCardName;
    /**
     *10:自有资金 20:其他资金'
     */
    @ApiModelProperty("10:自有资金 20:其他资金'")
    private String source;
    /**
     *'支付方式 10:放款 20:扣款 30:线下放款  40:线下扣款',
     */@ApiModelProperty("支付方式 10:放款 20:扣款 30:线下放款  40:线下扣款")
    private String type;
    /**
     * '业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣,
     */
    @ApiModelProperty("业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣")
    private String scenes;
    /**
     *'支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败',
     */
    @ApiModelProperty("支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败")
    private String state;
    /**
     *
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     *'支付请求时间'
     */
    @ApiModelProperty("支付请求时间")
    private Date payTime;
    @ApiModelProperty("借款时间")
    private Date borrowTime;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("支付序列号")
    private String serialNumber;



}