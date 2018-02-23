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
public class OtherLoanRecordDetailAnalyzeEntity implements Saveable{
	
	private long id;
	private String userId;
    private String mappingId;
    private String reportNo;
    private long  recordDetailAutoId;
    private String grantDate;
    private String financeCorporation;
    private String currency;
    private BigDecimal loanAmount;
    private String loanItem;
    private String expirationDate;
    private String cutoffDate;
    private boolean isCloseout;
    private BigDecimal remainBalance;
    private boolean isOverdue;
    private BigDecimal overdueAmount; // 逾期金额
    private int overdueMonths;
    private boolean isNintyDaysOverdue;
    private int nintyDaysOverdueMonth;
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_otherloanrecorddetailanalyze";

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

	public String getFinanceCorporation() {
		return financeCorporation;
	}

	public void setFinanceCorporation(String financeCorporation) {
		this.financeCorporation = financeCorporation;
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

	public boolean isCloseout() {
		return isCloseout;
	}

	public void setCloseout(boolean isCloseout) {
		this.isCloseout = isCloseout;
	}

	public BigDecimal getRemainBalance() {
		return remainBalance;
	}

	public void setRemainBalance(BigDecimal remainBalance) {
		this.remainBalance = remainBalance;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public BigDecimal getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public int getOverdueMonths() {
		return overdueMonths;
	}

	public void setOverdueMonths(int overdueMonths) {
		this.overdueMonths = overdueMonths;
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
  
