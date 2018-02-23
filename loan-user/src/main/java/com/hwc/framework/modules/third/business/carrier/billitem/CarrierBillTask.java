/**  
 * Project Name:api-webhook  
 * File Name:CarrierBillTask.java  
 * Package Name:com.example.service.webhook.business.carrier.billitem  
 * Date:2016年7月22日下午2:25:45  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.billitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**  
 * ClassName:CarrierBillTask <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月22日 下午2:25:45 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CarrierBillTask {
	private String mobile;
	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("task_id")
	private String taskId;
	private List<String> bills;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<String> getBills() {
		return bills;
	}
	public void setBills(List<String> bills) {
		this.bills = bills;
	}
	
	

}
  
