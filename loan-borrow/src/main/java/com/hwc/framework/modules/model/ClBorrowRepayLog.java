package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_borrow_repay_log")
public class ClBorrowRepayLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 还款计划id
     */
    @Column(name = "repay_id")
    private Long repayId;

    /**
     * 借款订单id
     */
    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 实际还款金额
     */
    private BigDecimal amount;

    /**
     * 逾期天数
     */
    @Column(name = "penalty_day")
    private Integer penaltyDay;

    /**
     * 逾期罚金
     */
    @Column(name = "penalty_amout")
    private BigDecimal penaltyAmout;

    /**
     * 还款方式   10代扣，20银行卡转账，30支付宝转账
     */
    @Column(name = "repay_way")
    private String repayWay;

    /**
     * 还款账号
     */
    @Column(name = "repay_account")
    private String repayAccount;

    /**
     * 还款流水号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 退还或补扣金额
     */
    @Column(name = "refund_deduction")
    private BigDecimal refundDeduction;

    /**
     * 退还或补扣支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 实际还款时间
     */
    @Column(name = "repay_time")
    private Date repayTime;

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
     * 获取还款计划id
     *
     * @return repay_id - 还款计划id
     */
    public Long getRepayId() {
        return repayId;
    }

    /**
     * 设置还款计划id
     *
     * @param repayId 还款计划id
     */
    public void setRepayId(Long repayId) {
        this.repayId = repayId;
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
     * 获取实际还款金额
     *
     * @return amount - 实际还款金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置实际还款金额
     *
     * @param amount 实际还款金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 获取还款方式   10代扣，20银行卡转账，30支付宝转账
     *
     * @return repay_way - 还款方式   10代扣，20银行卡转账，30支付宝转账
     */
    public String getRepayWay() {
        return repayWay;
    }

    /**
     * 设置还款方式   10代扣，20银行卡转账，30支付宝转账
     *
     * @param repayWay 还款方式   10代扣，20银行卡转账，30支付宝转账
     */
    public void setRepayWay(String repayWay) {
        this.repayWay = repayWay;
    }

    /**
     * 获取还款账号
     *
     * @return repay_account - 还款账号
     */
    public String getRepayAccount() {
        return repayAccount;
    }

    /**
     * 设置还款账号
     *
     * @param repayAccount 还款账号
     */
    public void setRepayAccount(String repayAccount) {
        this.repayAccount = repayAccount;
    }

    /**
     * 获取还款流水号
     *
     * @return serial_number - 还款流水号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置还款流水号
     *
     * @param serialNumber 还款流水号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取退还或补扣金额
     *
     * @return refund_deduction - 退还或补扣金额
     */
    public BigDecimal getRefundDeduction() {
        return refundDeduction;
    }

    /**
     * 设置退还或补扣金额
     *
     * @param refundDeduction 退还或补扣金额
     */
    public void setRefundDeduction(BigDecimal refundDeduction) {
        this.refundDeduction = refundDeduction;
    }

    /**
     * 获取退还或补扣支付时间
     *
     * @return pay_time - 退还或补扣支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置退还或补扣支付时间
     *
     * @param payTime 退还或补扣支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取实际还款时间
     *
     * @return repay_time - 实际还款时间
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置实际还款时间
     *
     * @param repayTime 实际还款时间
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
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