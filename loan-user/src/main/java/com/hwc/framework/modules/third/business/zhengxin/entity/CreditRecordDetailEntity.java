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
 * 
 * ClassName: CreditRecordDetailEntity    
 * date: 2017年1月11日 上午11:40:38   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class CreditRecordDetailEntity implements Saveable{
	
	private long id;
	private Long autoId;
	private String userId;
    private String mappingId;
    private String reportNo;  // 报告编号
    private int creditType;  // 信贷类型（1.信用卡 2.住房贷款 3.其它贷款）
    private int accountType;  // 账户类型（1.发生过逾期的贷记卡账户 2.从未逾期过的贷记卡及透支未超过60天的准贷记卡账户 3.从未逾期过的账户 4.透支超过60天的准贷记卡账户 5.发生过逾期的账户）
    private String content; // 记录内容
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_creditrecorddetail";

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

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Long getAutoId() {
		return autoId;
	}

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}

	

	

	
    
    
}
  
