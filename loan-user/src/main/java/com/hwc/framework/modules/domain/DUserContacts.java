package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DUserContacts {

    /**
     * 用户标识
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;
}