package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by   on 2017/10/30.
 */
@Data
public class BorrowBean {

    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    private String name;

    private String mobile;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    /**
     * 借款类型， X  信用类 ,D  抵押类
     */

    private String borrowType;

    /**
     * 抵押物申请订单号
     */
    private Long mid;

    /**
     * 期数
     */
    @ApiModelProperty(value = "分期数", required = true)
    private int periods;
    /**
     * 借款金额
     */
    @ApiModelProperty(value = "可借款金额", required = true)
    private Double amount;
    /**
     * 总额度
     */
    @ApiModelProperty("总额度")
    private Double total_amount;
    /**
     * 实际到账金额
     */
    @ApiModelProperty("实际到账金额")
    private Double realAmount;

    /**
     * 综合费用(借款利息+服务费+信息认证费)
     */
    private Double fee;

    /**
     * 订单生成时间
     */

    private Date createTime;

    /**
     * 借款期限(天)
     */

    private int timeLimit;

    /**
     * 订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     */
    @ApiModelProperty(value = "状态", allowableValues = "22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账")
    private String state;


    private String isnotify;

    /**
     * 收款银行卡关联id
     */
    @ApiModelProperty("收款银行卡关联id")
    private Long cardId;

    /**
     * 服务费
     */

    private Double serviceFee;

    /**
     * 流量费
     */

    private Double flowFee;

    /**
     * 信息认证费
     */

    private Double infoAuthFee;

    /**
     * 借款利息
     */
    @ApiModelProperty("借款利息")
    private Double interest;

    /**
     * 借款日利率
     */
    @ApiModelProperty(value = "借款日利率 %", required = true)
    private Double rate;
    @ApiModelProperty("借款日利率描述")
    private String borrow_rate_descript;

    /**
     * 客户端 默认10-移动app
     */
    @ApiModelProperty(value = "客户端 默认10-移动app", required = true)
    private String client;

    /**
     * 发起借款地址
     */
    @ApiModelProperty("发起借款地址")
    private String address;

    /**
     * 借款经纬度坐标
     */
    @ApiModelProperty("借款经纬度坐标")
    private String coordinate;

    /**
     * 备注、审核说明
     */
    private String remark;
    /**
     * ip地址
     */
    @ApiModelProperty("ip地址")
    private String ip;
    /**
     * 是否已经还款
     */
    @ApiModelProperty("是否已经还款")
    private boolean isRepay;
    /**
     * 是否可以借款
     */
    @ApiModelProperty("是否可以借款")
    private boolean isCanBorrow;
    /**
     *
     */
    @ApiModelProperty("银行卡号")
    private String cardno;
    /**
     * 逾期费用提醒
     */
    @ApiModelProperty("逾期后 显示在首页在上")
    private String overdue_hint;
    @ApiModelProperty("身份认证是否我完成")
    private boolean auth;
    @ApiModelProperty("交易密码是否设置")
    private boolean pwd;
    @ApiModelProperty("交易密码")
    private String tradePassword;

    /**
     * 消费场景
     */
    @ApiModelProperty("消费场景")
    private String scenes;

    @ApiModelProperty("合同code")
    private String contractId;

    /**
     * 是否成功结果款 1：是 0否
     */
    private String everSucceedBorrow;

}
