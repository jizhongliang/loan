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
 * ClassName: CreditRecordDetailEntity date: 2017年1月11日 上午11:40:38
 * 
 * @author yuandong
 * @version
 * @since JDK 1.6
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class PublicInformationEntity implements Saveable {

	private long id;
	private String userId;
	private String mappingId;
	private String reportNo; // 报告编号
	private int type; // 类型（1.欠税记录 2.民事判决记录 3.强制执行记录 4.行政处罚记录 5.电信欠费记录）
	private String content; // 记录内容
	private Date createTime;
	private Date lastModifyTime;
	private static final String[] keyColumns = { "id" };
	private static final String tableName = "t_publicinformationdetail";

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

}
