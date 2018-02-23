/**  
 * Project Name:api-webhook  
 * File Name:PackageUsage.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午2:56:56  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:PackageUsage <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午2:56:56 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageUsage {
	
//	 @JsonProperty("bill_month")
//	    private String billMonth;

	    @JsonProperty("bill_start_date")
	    private String billStartDate;

	    @JsonProperty("bill_end_date")
	    private String billEndDate;

		private String code;

		private String message;

	    private List<PackageItem> items = new ArrayList<>();

//	    public String getBillMonth() {
//	        return billMonth;
//	    }
//
//	    public void setBillMonth(String billMonth) {
//	        this.billMonth = billMonth;
//	    }

	    public String getBillStartDate() {
	        return billStartDate;
	    }

	    public void setBillStartDate(String billStartDate) {
	        this.billStartDate = billStartDate;
	    }

	    public String getBillEndDate() {
	        return billEndDate;
	    }

	    public void setBillEndDate(String billEndDate) {
	        this.billEndDate = billEndDate;
	    }

	    public List<PackageItem> getItems() {
	        return items;
	    }

	    public void setItems(List<PackageItem> items) {
	        this.items = items;
	    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
  
