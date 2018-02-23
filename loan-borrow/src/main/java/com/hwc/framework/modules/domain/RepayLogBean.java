package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/21.
 */
@Data
public class RepayLogBean {
    private String name;
    private Long borrowId;
    private Long userId;
    private Long repayId;
    private String mobile;
    private String orderNo;
    private Double borrowAmount;
    private Double repayAmount;
    private int periods;
    private int repay_period;
    private Double interest;
    private Double penaltyAmout;
    private Integer penaltyDay;
    private Double repayTotalAmount;
    private Double hasPayAmount;
    private String payAccount;
    private String repayWay;
    /**
     * 还款账号
     */
    private String repayAccount;
    /**
     * 还款流水号
     */

    private String serialNumber;

    private Date repayTime;
    private Date payTime;



}
