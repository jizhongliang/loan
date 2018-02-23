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
public class CreditRecordSummaryEntity implements Saveable{
	
	private long id;
	private String userId;
    private String mappingId;
    private String reportNo;  // 报告编号
    private int    creditType;  // 账户类型（1.信用卡 2.住房贷款 3.其它贷款）
    private int    accountNum;  // 账户数
    private int    unsettleUnCancel; // 未结清、未销户账户数
    private int    overdueAccount; // 逾期账户数
    private int    overdueNinety; // 90天以上逾期账户数
    private int    guarantee;   // 为他人担保数
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_creditrecordsummary";

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
  
