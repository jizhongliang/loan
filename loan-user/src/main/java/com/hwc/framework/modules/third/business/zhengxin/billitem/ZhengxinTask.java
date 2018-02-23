/**  
 * Project Name:api-webhook  
 * File Name:TaobaoTask.java  
 * Package Name:com.example.service.webhook.business.taobao.billitem  
 * Date:2016年8月11日下午9:16:59  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.zhengxin.billitem;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * ClassName: ZhengxinTask    
 * date: 2017年1月10日 下午7:39:24   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
public class ZhengxinTask {
	@JsonProperty("task_id")
	private String taskId;
	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("mapping_id")
	private String mappingId;
	@JsonProperty("report_no")
	private String reportNo;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	

}
  
