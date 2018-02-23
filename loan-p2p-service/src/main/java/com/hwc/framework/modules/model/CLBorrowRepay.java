package com.hwc.framework.modules.model;

import java.math.BigDecimal;
import java.util.Date;

public class CLBorrowRepay {
    private Long id;

    private Long userId;

    private Long borrowId;

    private BigDecimal realAmount;

    private BigDecimal interest;

    private Integer seq;

    private BigDecimal rate;

    private BigDecimal amount;

    private BigDecimal realAmountBalance;

    private BigDecimal repayAmount;

    private Date repayTime;

    private String state;

    private BigDecimal penaltyAmout;

    private Integer penaltyDay;

    private Date createTime;
    
    private String isNotityUser;
    private Date overdueTime;

    private Integer overdue;

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmountBalance() {
        return realAmountBalance;
    }

    public void setRealAmountBalance(BigDecimal realAmountBalance) {
        this.realAmountBalance = realAmountBalance;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getPenaltyAmout() {
        return penaltyAmout;
    }

    public void setPenaltyAmout(BigDecimal penaltyAmout) {
        this.penaltyAmout = penaltyAmout;
    }

    public Integer getPenaltyDay() {
        return penaltyDay;
    }

    public void setPenaltyDay(Integer penaltyDay) {
        this.penaltyDay = penaltyDay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getIsNotityUser() {
		return isNotityUser;
	}

	public void setIsNotityUser(String isNotityUser) {
		this.isNotityUser = isNotityUser;
	}
    
}