package com.hwc.loan.sdk.user.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DCloanUserDomain {

    private Long id;
    /**
     * 客户表 外键
     */
    private Long userId;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户类型
     */
    private String cat;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 注册客户端
     */
    private String registerClient;

    /**
     * 渠道名称
     */
    private long channelId;

    /**
     * 渠道名称
     */
    private String channelName;


    private String mktName;

    /**
     *
     */
    private String uuid;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 身份证地址
     */
    private String idAddr;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddr;

    /**
     * 性别
     */
    private String sex;

    /**
     * 居住地址
     */
    private String liveAddr;

    /**
     * 居住时长
     */
    private String liveTime;

    /**
     * 婚姻状况
     */
    private String marryState;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 民族
     */
    private String national;

    /**
     * 学历
     */
    private String education;

    /**
     * 工作年限
     */
    private String workingYears;

    /**
     * 月薪范围
     */
    private String salary;

    private String state;

    /**
     * create_time
     */
    private String createTime;

    /**
     * update_time
     */
    private String updateTime;

    /**
     * 是否白名单
     */
    private boolean whiteList;
}
