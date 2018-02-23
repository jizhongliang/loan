package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DSms {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendtime;

    /**
     * 短信类型
     */
    private String type;

    /**
     * 验证码
     */
    private String code;

    /**
     * 短信是否被使用 10-已使用 20-未使用
     */
    private String state;

    /**
     * 短信验证次数
     */
    private Integer verifyTime;
}
