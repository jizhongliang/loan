package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by zouwanli on 16/8/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditCardRecordDetailAnalyze {
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
    @JsonProperty("card_type")
    private String cardType;
    @JsonProperty("account_currency")
    private String accountCurrency;
    @JsonProperty("cutoff_date")
    private String cutoffDate;
    @JsonProperty("credit_limit")
    private BigDecimal creditLimit;
    @JsonProperty("used_credit_line")
    private BigDecimal usedCreditLine;
    @JsonProperty("overdraft_balance")
    private BigDecimal overDraftBalance;
    @JsonProperty("is_actived")
    private boolean isActived = true;
    @JsonProperty("is_closed")
    private boolean isClosed = false;
    @JsonProperty("is_overdue")
    private boolean isOverdue = false;
    @JsonProperty("overdue_month")
    private int overdueMonths = 0;
    @JsonProperty("is_sixtydays_overdraft")
    private boolean isSixtyDaysOverdraft = false;
    @JsonProperty("sixtydays_overdraft_month")
    private int     sixtyDaysOverdraftMonth = 0;
    @JsonProperty("is_nintydays_overdue")
    private boolean isNintyDaysOverdue = false;
    @JsonProperty("nintydays_overdue_month")
    private int nintyDaysOverdueMonth = 0;



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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getCutoffDate() {
        return cutoffDate;
    }

    public void setCutoffDate(String cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getUsedCreditLine() {
        return usedCreditLine;
    }

    public void setUsedCreditLine(BigDecimal usedCreditLine) {
        this.usedCreditLine = usedCreditLine;
    }

    public BigDecimal getOverDraftBalance() {
        return overDraftBalance;
    }

    public void setOverDraftBalance(BigDecimal overDraftBalance) {
        this.overDraftBalance = overDraftBalance;
    }

    public boolean getIsActived() {
        return isActived;
    }

    public void setIsActived(boolean isActived) {
        isActived = isActived;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
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

    public boolean getIsSixtyDaysOverdraft() {
        return isSixtyDaysOverdraft;
    }

    public void setIsSixtyDaysOverdraft(boolean isSixtyDaysOverdraft) {
        this.isSixtyDaysOverdraft = isSixtyDaysOverdraft;
    }

    public int getSixtyDaysOverdraftMonth() {
        return sixtyDaysOverdraftMonth;
    }

    public void setSixtyDaysOverdraftMonth(int sixtyDaysOverdraftMonth) {
        this.sixtyDaysOverdraftMonth = sixtyDaysOverdraftMonth;
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
