package com.hwc.framework.modules.bo;

/**
 * Created by jzl on 2017/12/28.
 */
public class RepayQueryDataResultBo {
    private String payMentTransaction;
    private String repaymentTransaction;
    private Integer status; //0成功 1失败 2还款中
    private String totalAmount;
    private String repayAmount;
    private String interest;
    private String overdueFine;
    private String remark;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(String overdueFine) {
        this.overdueFine = overdueFine;
    }

    public String getPayMentTransaction() {
        return payMentTransaction;
    }

    public void setPayMentTransaction(String payMentTransaction) {
        this.payMentTransaction = payMentTransaction;
    }

    public String getRepaymentTransaction() {
        return repaymentTransaction;
    }

    public void setRepaymentTransaction(String repaymentTransaction) {
        this.repaymentTransaction = repaymentTransaction;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(String repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
