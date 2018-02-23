package com.hwc.framework.modules.domain;

import lombok.Data;

@Data
public class DUserEmerContacts {
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