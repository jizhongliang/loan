/**  
 * Project Name:api-webhook  
 * File Name:MobileRechargeDetail.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午9:18:44  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:MobileRechargeDetail <br/>  
 * Function: 充值记录－moxie接口值 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午9:18:44 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MobileRechargeDetail {
	//private Integer size;
    // @JsonProperty("total_size")
    //private Integer totalSize;
    //private String mobile;
    private List<MobileRecharge> recharges = new ArrayList<>();
//	public Integer getSize() {
//		return size;
//	}
//	public void setSize(Integer size) {
//		this.size = size;
//	}
//	public Integer getTotalSize() {
//		return totalSize;
//	}
//	public void setTotalSize(Integer totalSize) {
//		this.totalSize = totalSize;
//	}
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
	public List<MobileRecharge> getRecharges() {
		return recharges;
	}
	public void setRecharges(List<MobileRecharge> recharges) {
		this.recharges = recharges;
	}

}
  
