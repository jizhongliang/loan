package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_opinion")
public class ClOpinion {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 意见
     */
    private String opinion;

    /**
     * 管理员标识
     */
    @Column(name = "sys_user_id")
    private Long sysUserId;

    /**
     * 反馈
     */
    private String feedback;

    /**
     * 状态 10待确认，20已确认
     */
    private String state;

    /**
     * create_time
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "confirm_time")
    private Date confirmTime;

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
     * 获取用户标识
     *
     * @return user_id - 用户标识
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取意见
     *
     * @return opinion - 意见
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * 设置意见
     *
     * @param opinion 意见
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * 获取管理员标识
     *
     * @return sys_user_id - 管理员标识
     */
    public Long getSysUserId() {
        return sysUserId;
    }

    /**
     * 设置管理员标识
     *
     * @param sysUserId 管理员标识
     */
    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * 获取反馈
     *
     * @return feedback - 反馈
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置反馈
     *
     * @param feedback 反馈
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * 获取状态 10待确认，20已确认
     *
     * @return state - 状态 10待确认，20已确认
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态 10待确认，20已确认
     *
     * @param state 状态 10待确认，20已确认
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取create_time
     *
     * @return create_time - create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置create_time
     *
     * @param createTime create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return confirm_time
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * @param confirmTime
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
}