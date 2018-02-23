package com.hwc.loan.sdk.quzrtz.domain;

import lombok.Data;

import java.util.Date;

@Data
public class QuartzInfo {

    private Long id;

    /**
     * 定时任务名称
     */
    private String name;

    /**
     * 定时任务code标识
     */
    private String code;

    /**
     * 定时任务执行周期
     */
    private String cycle;

    /**
     * 定时任务执行类
     */
    private String className;

    /**
     * 成功执行次数
     */
    private Integer succeed;

    /**
     * 失败执行次数
     */
    private Integer fail;

    /**
     * 是否启用 10-启用 20-禁用
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务状态描述
     */
    private String stateStr;

    /**
     * 上次执行时间
     */
    private Date lastStartTime;


}
