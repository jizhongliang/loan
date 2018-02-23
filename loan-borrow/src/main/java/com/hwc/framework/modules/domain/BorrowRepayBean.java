package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.print.event.PrintJobAttributeEvent;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by   on 2017/10/31.
 */
@Data
public class BorrowRepayBean {
    @ApiModelProperty("还款计划ID")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 借款订单id
     */
    @ApiModelProperty("借款订单id")
    private Long borrowId;
    /**
     * 第几期
     */
    @ApiModelProperty("第几期")
    private Integer seq;
    /**
     * 应还本金
     */
    @ApiModelProperty("应还本金")
    private Double realAmount;

    /**
     * 利息
     */
    @ApiModelProperty("利息")
    private Double interest;

    /**
     * 利率 百分之
     */
    @ApiModelProperty("利率 百分之")
    private String rate;

    /**
     * 还款金额
     */
    @ApiModelProperty("还款金额")
    private Double amount;

    /**
     * 剩余本金
     */

    @ApiModelProperty("剩余本金")
    private Double realAmountBalance;

    /**
     * 实际还款金额
     */
    @ApiModelProperty("实际还款金额")
    private Double repayAmount = 0D;

    @ApiModelProperty("逾期金额")
    private Double penaltyAmout = 0D;

    /**
     * 逾期天数
     */
    @ApiModelProperty("逾期天数")
    private Integer penaltyDay = 0;
    @ApiModelProperty("状态")
    private String state;
    @ApiModelProperty("还款日期")
    private Date repayDate ;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("手机")
    private String mobile;
    @ApiModelProperty("还款卡号")
    private String cardno;
}
