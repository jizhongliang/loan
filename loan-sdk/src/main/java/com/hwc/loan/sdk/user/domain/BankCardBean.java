package com.hwc.loan.sdk.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class BankCardBean {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户标识
     */
    private Long userId;

    /**
     * 开户行
     */
    private String bank;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 预留手机号
     */
    private String phone;

    /**
     * 签约协议编号
     */
    private String agreeNo;

    /**
     * 绑卡时间
     */
    private Date bindTime;
    private String id_holder;
    private String id_no;
    /**
     * 签约结果
     */
    private String signResult;
    private String bankCode;
    private String channel;
}
