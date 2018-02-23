package com.hwc.framework.modules.bo;

/**还款参数实体
 * Created by jzl on 2017/12/27.
 */
public class RepayDataResultBo {
    private String payMentTransaction;  //打款流水号，新华金典的
    private String stages;   //第三方分期还款流水号
    private String repaymentTransaction;    //还款流水号
    private String totalAmount;             //还款总金额
    private String repayAmount;             //还款本金金额，单位 元
    private String interest;
    private String overdueFine;             //逾期罚金
    private Integer isLastStages;           //是否为最后一期 1是 0否
    private Integer status;                 //状态:0成功 1失败
    private String remark;                  //说明信息
    private String thirdTransaction;        //第三方订单号  没用到，只是解析返回的

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

    public Integer getIsLastStages() {
        return isLastStages;
    }

    public void setIsLastStages(Integer isLastStages) {
        this.isLastStages = isLastStages;
    }

    public String getThirdTransaction() {
        return thirdTransaction;
    }

    public void setThirdTransaction(String thirdTransaction) {
        this.thirdTransaction = thirdTransaction;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
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

    public String getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(String repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
