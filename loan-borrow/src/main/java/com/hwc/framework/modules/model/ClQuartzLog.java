package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_quartz_log")
public class ClQuartzLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 定时任务id
     */
    @Column(name = "quartz_id")
    private Long quartzId;

    /**
     * 启动时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 任务用时
     */
    private Integer time;

    /**
     * 执行是否成功 10-成功 20-失败
     */
    private String result;

    /**
     * 备注信息
     */
    private String remark;

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
     * 获取定时任务id
     *
     * @return quartz_id - 定时任务id
     */
    public Long getQuartzId() {
        return quartzId;
    }

    /**
     * 设置定时任务id
     *
     * @param quartzId 定时任务id
     */
    public void setQuartzId(Long quartzId) {
        this.quartzId = quartzId;
    }

    /**
     * 获取启动时间
     *
     * @return start_time - 启动时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置启动时间
     *
     * @param startTime 启动时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取任务用时
     *
     * @return time - 任务用时
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置任务用时
     *
     * @param time 任务用时
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 获取执行是否成功 10-成功 20-失败
     *
     * @return result - 执行是否成功 10-成功 20-失败
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置执行是否成功 10-成功 20-失败
     *
     * @param result 执行是否成功 10-成功 20-失败
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取备注信息
     *
     * @return remark - 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}