package com.hwc.framework.modules.model;

import cn.freesoft.utils.FsUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_borrow_repay")
public class ClBorrowRepay {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 借款订单id
     */
    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 应还本金
     */
    @Column(name = "real_amount")
    private BigDecimal realAmount;

    /**
     * 利息
     */
    private BigDecimal interest;

    private Integer seq;

    /**
     * 利率 百分之
     */
    private BigDecimal rate;

    /**
     * 还款金额
     */
    private BigDecimal amount;

    /**
     * 剩余本金
     */
    @Column(name = "real_amount_balance")
    private BigDecimal realAmountBalance;

    /**
     * 实际还款金额
     */
    @Column(name = "repay_amount")
    private BigDecimal repayAmount;

    /**
     * 应还款时间
     */
    @Column(name = "repay_time")
    private Date repayTime;

    /**
     * 还款状态 10-已还款 20-未还款,30 部分还款
     */
    private String state;

    /**
     * 逾期罚金
     */
    @Column(name = "penalty_amout")
    private BigDecimal penaltyAmout;

    /**
     * 逾期天数
     */
    @Column(name = "penalty_day")
    private Integer penaltyDay;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取借款订单id
     *
     * @return borrow_id - 借款订单id
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * 设置借款订单id
     *
     * @param borrowId 借款订单id
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * 获取应还本金
     *
     * @return real_amount - 应还本金
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 设置应还本金
     *
     * @param realAmount 应还本金
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 获取利息
     *
     * @return interest - 利息
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置利息
     *
     * @param interest 利息
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * @return seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取利率 百分之
     *
     * @return rate - 利率 百分之
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置利率 百分之
     *
     * @param rate 利率 百分之
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取还款金额
     *
     * @return amount - 还款金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置还款金额
     *
     * @param amount 还款金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取剩余本金
     *
     * @return real_amount_balance - 剩余本金
     */
    public BigDecimal getRealAmountBalance() {
        return realAmountBalance;
    }

    /**
     * 设置剩余本金
     *
     * @param realAmountBalance 剩余本金
     */
    public void setRealAmountBalance(BigDecimal realAmountBalance) {
        this.realAmountBalance = realAmountBalance;
    }

    /**
     * 获取实际还款金额
     *
     * @return repay_amount - 实际还款金额
     */
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    /**
     * 设置实际还款金额
     *
     * @param repayAmount 实际还款金额
     */
    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    /**
     * 获取应还款时间
     *
     * @return repay_time - 应还款时间
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置应还款时间
     *
     * @param repayTime 应还款时间
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    /**
     * 获取还款状态 10-已还款 20-未还款,30 部分还款
     *
     * @return state - 还款状态 10-已还款 20-未还款,30 部分还款
     */
    public String getState() {
        return state;
    }

    /**
     * 设置还款状态 10-已还款 20-未还款,30 部分还款
     *
     * @param state 还款状态 10-已还款 20-未还款,30 部分还款
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取逾期罚金
     *
     * @return penalty_amout - 逾期罚金
     */
    public BigDecimal getPenaltyAmout() {
        return penaltyAmout;
    }

    /**
     * 设置逾期罚金
     *
     * @param penaltyAmout 逾期罚金
     */
    public void setPenaltyAmout(BigDecimal penaltyAmout) {
        this.penaltyAmout = penaltyAmout;
    }

    /**
     * 获取逾期天数
     *
     * @return penalty_day - 逾期天数
     */
    public Integer getPenaltyDay() {
        return penaltyDay;
    }

    /**
     * 设置逾期天数
     *
     * @param penaltyDay 逾期天数
     */
    public void setPenaltyDay(Integer penaltyDay) {
        this.penaltyDay = penaltyDay;
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

    public Double getTotalAmount() {
        return FsUtils.addDouble(amount == null ? 0 : amount.doubleValue(), penaltyAmout == null ? 0 : penaltyAmout.doubleValue());
    }
}