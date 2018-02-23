package com.hwc.loan.sdk.borrow.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/10/30.
 */
@Data
public class DBorrowBean {

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

    private int periods;
    /**
     * 借款金额
     */

    private Double amount;
    /**
     * 总额度
     */

    private Double total_amount;
    /**
     * 实际到账金额
     */

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

    private String state;


    private String isnotify;

    /**
     * 收款银行卡关联id
     */

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

    private Double interest;

    /**
     * 借款日利率
     */

    private Double rate;

    private String borrow_rate_descript;

    /**
     * 客户端 默认10-移动app
     */

    private String client;

    /**
     * 发起借款地址
     */

    private String address;

    /**
     * 借款经纬度坐标
     */

    private String coordinate;

    /**
     * 备注、审核说明
     */
    private String remark;
    /**
     * ip地址
     */

    private String ip;
    /**
     * 是否已经还款
     */

    private boolean isRepay;
    /**
     * 是否可以借款
     */

    private boolean isCanBorrow;
    /**
     *
     */

    private String cardno;
    /**
     * 逾期费用提醒
     */
    private String overdue_hint;

    private boolean auth;

    private boolean pwd;

}
