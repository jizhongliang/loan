/**  
 * Project Name:api-webhook  
 * File Name:PackageUsageDetail.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午3:04:36  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:PackageUsageDetail <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午3:04:36 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageUsageDetail {
	
	 //private String mobile;
	 private List<PackageUsage> packageUsages = new ArrayList<>();
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
	public List<PackageUsage> getPackageUsages() {
		return packageUsages;
	}
	public void setPackageUsages(List<PackageUsage> packageUsages) {
		this.packageUsages = packageUsages;
	}


}
  
