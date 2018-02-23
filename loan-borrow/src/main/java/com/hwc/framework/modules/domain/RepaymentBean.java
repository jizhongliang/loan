package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class RepaymentBean {
    private String bankCardNo;
    private String orderNo;
    private Double amount;
    private String realName;
    private Double realAmount;
    private Long borrowId;
    private String idNo;
    private String agreeNo;
    private String phone;
    private Double penaltyAmout;
    private String uid;
    private Date registerTime;
    private Date repayTime;


}
