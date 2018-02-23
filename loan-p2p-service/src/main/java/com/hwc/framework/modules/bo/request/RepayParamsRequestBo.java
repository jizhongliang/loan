package com.hwc.framework.modules.bo.request;

import java.util.List;

/**还款请求参数实体
 * Created by jzl on 2017/12/27.
 */
public class RepayParamsRequestBo {
    private String payMentTransaction;  //放款流水号，新华金典流水号
    private String totalAmount;         //还款总金额
    private String repayAmount;         //还款金额
    private String stages;              //  分期计划中的id，分期流水号
    private Integer isLastStages;       //是否为最后一期还款：1是 0否
    private String interest;            //当前利息 单位 元
    private String fine;                //罚金
    private Integer isOverdue;          //是否含有逾期 1：是 0：否
    private List<RepayParamsOverduesBo> overdues;   //逾期详情

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

    public List<RepayParamsOverduesBo> getOverdues() {
        return overdues;
    }

    public void setOverdues(List<RepayParamsOverduesBo> overdues) {
        this.overdues = overdues;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getIsLastStages() {
        return isLastStages;
    }

    public void setIsLastStages(Integer isLastStages) {
        this.isLastStages = isLastStages;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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

    public String getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(String repayAmount) {
        this.repayAmount = repayAmount;
    }
}
