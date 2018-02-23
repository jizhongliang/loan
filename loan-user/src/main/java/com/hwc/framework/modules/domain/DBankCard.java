package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DBankCard {
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

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户真实姓名
     */
    private String idNo;

    /**
     * 是否可替换
     */
    private String state;
}