package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_quartz_info")
public class ClQuartzInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "class_name")
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
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取定时任务名称
     *
     * @return name - 定时任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置定时任务名称
     *
     * @param name 定时任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取定时任务code标识
     *
     * @return code - 定时任务code标识
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置定时任务code标识
     *
     * @param code 定时任务code标识
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取定时任务执行周期
     *
     * @return cycle - 定时任务执行周期
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * 设置定时任务执行周期
     *
     * @param cycle 定时任务执行周期
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * 获取定时任务执行类
     *
     * @return class_name - 定时任务执行类
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置定时任务执行类
     *
     * @param className 定时任务执行类
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取成功执行次数
     *
     * @return succeed - 成功执行次数
     */
    public Integer getSucceed() {
        return succeed;
    }

    /**
     * 设置成功执行次数
     *
     * @param succeed 成功执行次数
     */
    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    /**
     * 获取失败执行次数
     *
     * @return fail - 失败执行次数
     */
    public Integer getFail() {
        return fail;
    }

    /**
     * 设置失败执行次数
     *
     * @param fail 失败执行次数
     */
    public void setFail(Integer fail) {
        this.fail = fail;
    }

    /**
     * 获取是否启用 10-启用 20-禁用
     *
     * @return state - 是否启用 10-启用 20-禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置是否启用 10-启用 20-禁用
     *
     * @param state 是否启用 10-启用 20-禁用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}