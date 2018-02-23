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
public class QueryInformationEntity implements Saveable{
	
	private long id;
	private String userId;
    private String mappingId;
    private String reportNo;  // 报告编号
    private String queryDateTime;  // 记录查询时间
    private String queryOperator;  // 查询操作员
    private String queryReason; // 查询原因(信用卡审批 , 贷后管理 , 贷款审批 ,本人查询）
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_queryinformationdetail";

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

	public String getQueryDateTime() {
		return queryDateTime;
	}

	public void setQueryDateTime(String queryDateTime) {
		this.queryDateTime = queryDateTime;
	}

	public String getQueryOperator() {
		return queryOperator;
	}

	public void setQueryOperator(String queryOperator) {
		this.queryOperator = queryOperator;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
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
  
