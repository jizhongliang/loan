package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zouwanli on 16/7/7.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditRecordSummary {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("report_no")
    private String reportNo;  // 报告编号
    @JsonProperty("credit_type")
    private int    creditType;  // 账户类型（1.信用卡 2.住房贷款 3.其它贷款）
    @JsonProperty("account_num")
    private int    accountNum;  // 账户数
    @JsonProperty("unSettle_unCancel")
    private int    unsettleUnCancel; // 未结清、未销户账户数
    @JsonProperty("overdue_account")
    private int    overdueAccount; // 逾期账户数
    @JsonProperty("overdue_ninety")
    private int    overdueNinety; // 90天以上逾期账户数
    private int    guarantee;   // 为他人担保数


    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public int getCreditType() {
        return creditType;
    }

    public void setCreditType(int creditType) {
        this.creditType = creditType;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getUnsettleUnCancel() {
        return unsettleUnCancel;
    }

    public void setUnsettleUnCancel(int unsettleUnCancel) {
        this.unsettleUnCancel = unsettleUnCancel;
    }

    public int getOverdueAccount() {
        return overdueAccount;
    }

    public void setOverdueAccount(int overdueAccount) {
        this.overdueAccount = overdueAccount;
    }

    public int getOverdueNinety() {
        return overdueNinety;
    }

    public void setOverdueNinety(int overdueNinety) {
        this.overdueNinety = overdueNinety;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

}
