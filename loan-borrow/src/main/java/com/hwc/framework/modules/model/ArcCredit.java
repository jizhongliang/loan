package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "arc_credit")
public class ArcCredit {
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
     * 总额度
     */
    private BigDecimal total;

    /**
     * 额度类型
     */
    @Column(name = "credit_type")
    private Long creditType;

    /**
     * 评分
     */
    private String grade;

    /**
     * 已使用额度
     */
    private BigDecimal used;

    /**
     * 可使用额度
     */
    private BigDecimal unuse;

    /**
     * 状态 10 -正常 20-冻结
     */
    private String state;

    /**
     * 扩展字段
     */
    @Column(name = "req_ext")
    private String reqExt;

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
     * 获取总额度
     *
     * @return total - 总额度
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置总额度
     *
     * @param total 总额度
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取额度类型
     *
     * @return credit_type - 额度类型
     */
    public Long getCreditType() {
        return creditType;
    }

    /**
     * 设置额度类型
     *
     * @param creditType 额度类型
     */
    public void setCreditType(Long creditType) {
        this.creditType = creditType;
    }

    /**
     * 获取评分
     *
     * @return grade - 评分
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置评分
     *
     * @param grade 评分
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取已使用额度
     *
     * @return used - 已使用额度
     */
    public BigDecimal getUsed() {
        return used;
    }

    /**
     * 设置已使用额度
     *
     * @param used 已使用额度
     */
    public void setUsed(BigDecimal used) {
        this.used = used;
    }

    /**
     * 获取可使用额度
     *
     * @return unuse - 可使用额度
     */
    public BigDecimal getUnuse() {
        return unuse;
    }

    /**
     * 设置可使用额度
     *
     * @param unuse 可使用额度
     */
    public void setUnuse(BigDecimal unuse) {
        this.unuse = unuse;
    }

    /**
     * 获取状态 10 -正常 20-冻结
     *
     * @return state - 状态 10 -正常 20-冻结
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态 10 -正常 20-冻结
     *
     * @param state 状态 10 -正常 20-冻结
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取扩展字段
     *
     * @return req_ext - 扩展字段
     */
    public String getReqExt() {
        return reqExt;
    }

    /**
     * 设置扩展字段
     *
     * @param reqExt 扩展字段
     */
    public void setReqExt(String reqExt) {
        this.reqExt = reqExt;
    }
}