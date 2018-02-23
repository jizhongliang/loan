package com.hwc.loan.sdk.user.domain;

import lombok.Data;

@Data
public class DUserContactsDomain {

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