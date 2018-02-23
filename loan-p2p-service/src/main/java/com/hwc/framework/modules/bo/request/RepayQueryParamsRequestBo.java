package com.hwc.framework.modules.bo.request;

/**
 * Created by jzl on 2017/12/28.
 */
public class RepayQueryParamsRequestBo {
    private String payMentTransaction;  //打款流水号
    private String repaymentTransaction;    //还款流水号

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
}
