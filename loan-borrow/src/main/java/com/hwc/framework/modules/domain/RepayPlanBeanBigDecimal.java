package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/11/7.
 */
@Data
public class RepayPlanBeanBigDecimal {
    @ApiModelProperty("借款金额")
    private Double amount = 0D;
    @ApiModelProperty("已还款总金额")
    private BigDecimal repayAmount;
    @ApiModelProperty("放款日期")
    private Date borrow_date;
    @ApiModelProperty("借款总金额+利息")
    private Double total_amount;
    @ApiModelProperty("首次还款金额")
    private Double first_repay_amount;
    @ApiModelProperty("首次还款日期")
    private Date first_repay_date;
    @ApiModelProperty("最近一期还款计划Id")
    private Long first_repay_id;
    @ApiModelProperty("总利息")
    private Double interest = 0D;
    @ApiModelProperty("最后一次还款时间")
    private Date end_repay_date;
    @ApiModelProperty("借款利率")
    private BigDecimal rate;
    @ApiModelProperty("分期数")
    private int periods;
    @ApiModelProperty("已还期数")
    private int hasRepay_periods = 0;
    @ApiModelProperty("未还期数")
    private int unRepay_periods = 0;
    @ApiModelProperty("未还金额")
    private Double unRepay_amount = 0D;
    @ApiModelProperty("还款银行卡号")
    private String bankCardNo;
    @ApiModelProperty("最近一期未还款金额")
    private Double current_repay_amount;
    @ApiModelProperty("最近一期未还款时间")
    private Date current_repay_date;
    @ApiModelProperty("最近一期未还款计划ID")
    private Long current_repay_id;

    @ApiModelProperty("借款id")
    private Long borrowId;
    @ApiModelProperty("还款计划")
    private List<BorrowRepayBean> plans;
}
