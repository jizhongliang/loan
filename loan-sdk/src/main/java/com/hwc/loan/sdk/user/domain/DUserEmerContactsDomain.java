package com.hwc.loan.sdk.user.domain;

import lombok.Data;

@Data
public class DUserEmerContactsDomain {
    /**
     * 主键
     */
    private Long id;

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系号码
     */
    private String phone;

    /**
     * 客户表 外键
     */
    private Long userId;

    /**
     * 与本人关系
     */
    private String relation;

    /**
     * 是否直系
     */
    private String type;

}