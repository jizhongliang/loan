/**  
 * Project Name:alipay-worker-server  
 * File Name:DeliverAddress.java  
 * Package Name:com.moxie.cloud.services.alipayworker.entity.taobao  
 * Date:2016年6月13日下午4:47:34  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.zhengxin.entity;

import com.hwc.framework.modules.service.base.Saveable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

/**  
 * ClassName:DeliverAddress <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年6月13日 下午4:47:34 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class CreditCardRecordDetailAnalyzeEntity implements Saveable{
	
	private long id;
	private String userId;
    private String mappingId;
    private String reportNo;
    private long  recordDetailAutoId;
    private String grantDate;
    private String bankName;
    private String cardType;
    private String accountCurrency;
    private String cutoffDate;
    private BigDecimal creditLimit;
    private BigDecimal usedCreditLine;
    private BigDecimal overDraftBalance;
    private boolean isActived;
    private boolean isClosed;
    private boolean isOverdue;
    private int overdueMonths;
    private boolean isSixtyDaysOverdraft;
    private int     sixtyDaysOverdraftMonth;
    private boolean isNintyDaysOverdue;
    private int nintyDaysOverdueMonth;
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_creditcardrecorddetailanalyze";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String[] getKeyColumns() {
        return keyColumns;
    }
    
    
    @Override
    public String getPrimaryVal() {
        return String.valueOf(id);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public boolean isActived() {
		return isActived;
	}

	public void setActived(boolean isActived) {
		this.isActived = isActived;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public int getOverdueMonths() {
		return overdueMonths;
	}

	public void setOverdueMonths(int overdueMonths) {
		this.overdueMonths = overdueMonths;
	}

	public boolean isSixtyDaysOverdraft() {
		return isSixtyDaysOverdraft;
	}

	public void setSixtyDaysOverdraft(boolean isSixtyDaysOverdraft) {
		this.isSixtyDaysOverdraft = isSixtyDaysOverdraft;
	}

	public int getSixtyDaysOverdraftMonth() {
		return sixtyDaysOverdraftMonth;
	}

	public void setSixtyDaysOverdraftMonth(int sixtyDaysOverdraftMonth) {
		this.sixtyDaysOverdraftMonth = sixtyDaysOverdraftMonth;
	}

	public boolean isNintyDaysOverdue() {
		return isNintyDaysOverdue;
	}

	public void setNintyDaysOverdue(boolean isNintyDaysOverdue) {
		this.isNintyDaysOverdue = isNintyDaysOverdue;
	}

	public int getNintyDaysOverdueMonth() {
		return nintyDaysOverdueMonth;
	}

	public void setNintyDaysOverdueMonth(int nintyDaysOverdueMonth) {
		this.nintyDaysOverdueMonth = nintyDaysOverdueMonth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}
  
