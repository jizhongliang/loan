package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


/**
 * Created by zouwanli on 16/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HousingLoanRecordDetailAnalyze {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("report_no")
    private String reportNo;
    @JsonProperty("recordDetail_autoId")
    private long  recordDetailAutoId;
    @JsonProperty("grant_date")
    private String grantDate;
    @JsonProperty("bank_name")
    private String bankName;
    private String currency;
    @JsonProperty("loan_amount")
    private BigDecimal loanAmount;
    @JsonProperty("loan_item")
    private String loanItem;
    @JsonProperty("expiration_date")
    private String expirationDate;
    @JsonProperty("cutoff_date")
    private String cutoffDate;
    @JsonProperty("is_closeout")
    private boolean isCloseout = false;
    @JsonProperty("remain_balance")
    private BigDecimal remainBalance;
    @JsonProperty("is_overdue")
    private boolean isOverdue = false;
    @JsonProperty("overdue_month")
    private int overdueMonths;
    @JsonProperty("is_nintydays_overdue")
    private boolean isNintyDaysOverdue = false;
    @JsonProperty("nintydays_oversue_month")
    private int nintyDaysOverdueMonth;


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

    public long getRecordDetailAutoId() {
        return recordDetailAutoId;
    }

    public void setRecordDetailAutoId(long recordDetailAutoId) {
        this.recordDetailAutoId = recordDetailAutoId;
    }

    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanItem() {
        return loanItem;
    }

    public void setLoanItem(String loanItem) {
        this.loanItem = loanItem;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCutoffDate() {
        return cutoffDate;
    }

    public void setCutoffDate(String cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    public boolean getIsCloseout() {
        return isCloseout;
    }

    public void setIsCloseout(boolean isCloseout) {
        this.isCloseout = isCloseout;
    }

    public BigDecimal getRemainBalance() {
        return remainBalance;
    }

    public void setRemainBalance(BigDecimal remainBalance) {
        this.remainBalance = remainBalance;
    }

    public boolean getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public int getOverdueMonths() {
        return overdueMonths;
    }

    public void setOverdueMonths(int overdueMonths) {
        this.overdueMonths = overdueMonths;
    }

    public boolean getIsNintyDaysOverdue() {
        return isNintyDaysOverdue;
    }

    public void setIsNintyDaysOverdue(boolean isNintyDaysOverdue) {
        this.isNintyDaysOverdue = isNintyDaysOverdue;
    }

    public int getNintyDaysOverdueMonth() {
        return nintyDaysOverdueMonth;
    }

    public void setNintyDaysOverdueMonth(int nintyDaysOverdueMonth) {
        this.nintyDaysOverdueMonth = nintyDaysOverdueMonth;
    }


  
}
