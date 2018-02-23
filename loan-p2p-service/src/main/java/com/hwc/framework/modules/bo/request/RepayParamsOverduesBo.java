package com.hwc.framework.modules.bo.request;

import java.math.BigDecimal;
import java.util.Date;

/**还款请求实体中的逾期参数实体,即逾期详情
 * Created by jzl on 2018/1/4.
 */
public class RepayParamsOverduesBo {
    private String overdueStages;       //逾期的期数，可以存分期流水，repayId
    private Date expiryDate;            //还款日期，即该期应还款日期
    private BigDecimal overdueAmount;   //逾期本金，单位：元，即本期应换本金
    private BigDecimal overdueInterest; //逾期利息
    private BigDecimal overdueFine;     //逾期罚金

    public String getOverdueStages() {
        return overdueStages;
    }

    public void setOverdueStages(String overdueStages) {
        this.overdueStages = overdueStages;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }
}
