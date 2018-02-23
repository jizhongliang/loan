package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DUserOtherInfo {
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 账号
     */
    private String accnt;

    /**
     * 账号1
     */
    private String unionid;

    /**
     * 用户标识(关联客户主键)
     */
    private Long userId;

    /**
     * WX 微信,QQ qq TAOBAO 淘宝
     */
    private String cat;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

}