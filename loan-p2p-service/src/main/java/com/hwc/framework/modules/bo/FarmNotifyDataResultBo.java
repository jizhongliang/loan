package com.hwc.framework.modules.bo;

/**放款回调通知，data详细参数result参数实体
 * Created by jzl on 2017/12/26.
 */
public class FarmNotifyDataResultBo {
    private String payMentTransaction;  //打款流水号，新华金典订单号
    private String thirdTransaction;    //第三方借款流水号，我方订单号
    private String payAmount;           //借款金额，单位：元
    private Integer status;             //状态：0打款成功 1打款失败
    private String remark;              //结果描述

    public String getPayMentTransaction() {
        return payMentTransaction;
    }

    public void setPayMentTransaction(String payMentTransaction) {
        this.payMentTransaction = payMentTransaction;
    }

    public String getThirdTransaction() {
        return thirdTransaction;
    }

    public void setThirdTransaction(String thirdTransaction) {
        this.thirdTransaction = thirdTransaction;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
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
