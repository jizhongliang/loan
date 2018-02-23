/**  
 * Project Name:api-webhook  
 * File Name:MobileBill.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午4:43:49  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**  
 * ClassName:MobileBill <br/>  
 * Function: 账单 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午4:43:49 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MobileBill {
		private String code;
		private String message;
	    @JsonProperty("bill_month")
	    private String billMonth;
	    @JsonProperty("bill_start_date")
	    private String billStartDate;
	    @JsonProperty("bill_end_date")
	    private String billEndDate;

	    @JsonProperty("base_fee")
	    private Integer baseFee;
	    @JsonProperty("extra_service_fee")
	    private Integer extraServiceFee;
	    @JsonProperty("voice_fee")
	    private Integer voiceFee;
	    @JsonProperty("sms_fee")
	    private Integer smsFee;
	    @JsonProperty("web_fee")
	    private Integer webFee;
	    @JsonProperty("extra_fee")
	    private Integer extraFee;
	    @JsonProperty("total_fee")
	    private Integer totalFee;
	    @JsonProperty("discount")
	    private Integer discount;
	    @JsonProperty("extra_discount")
	    private Integer extraDiscount;
	    @JsonProperty("actualFee")
	    private Integer actualFee;
	    @JsonProperty("paid_fee")
	    private Integer paidFee;
	    @JsonProperty("unpaid_fee")
	    private Integer unpaidFee;
	    private Integer point;
	    @JsonProperty("last_point")
	    private Integer lastPoint;
	    @JsonProperty("related_mobiles")
	    private String relatedMobiles;
	    @JsonProperty("notes")
	    private String notes;

	    public String getBillMonth() {
	        return billMonth;
	    }

	    public void setBillMonth(String billMonth) {
	        this.billMonth = billMonth;
	    }

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

	    public Integer getBaseFee() {
	        return baseFee;
	    }

	    public void setBaseFee(Integer baseFee) {
	        this.baseFee = baseFee;
	    }

	    public Integer getExtraServiceFee() {
	        return extraServiceFee;
	    }

	    public void setExtraServiceFee(Integer extraServiceFee) {
	        this.extraServiceFee = extraServiceFee;
	    }

	    public Integer getVoiceFee() {
	        return voiceFee;
	    }

	    public void setVoiceFee(Integer voiceFee) {
	        this.voiceFee = voiceFee;
	    }

	    public Integer getSmsFee() {
	        return smsFee;
	    }

	    public void setSmsFee(Integer smsFee) {
	        this.smsFee = smsFee;
	    }

	    public Integer getWebFee() {
	        return webFee;
	    }

	    public void setWebFee(Integer webFee) {
	        this.webFee = webFee;
	    }

	    public Integer getExtraFee() {
	        return extraFee;
	    }

	    public void setExtraFee(Integer extraFee) {
	        this.extraFee = extraFee;
	    }

	    public Integer getTotalFee() {
	        return totalFee;
	    }

	    public void setTotalFee(Integer totalFee) {
	        this.totalFee = totalFee;
	    }

	    public Integer getDiscount() {
	        return discount;
	    }

	    public void setDiscount(Integer discount) {
	        this.discount = discount;
	    }

	    public Integer getExtraDiscount() {
	        return extraDiscount;
	    }

	    public void setExtraDiscount(Integer extraDiscount) {
	        this.extraDiscount = extraDiscount;
	    }

	    public Integer getActualFee() {
	        return actualFee;
	    }

	    public void setActualFee(Integer actualFee) {
	        this.actualFee = actualFee;
	    }

	    public Integer getPaidFee() {
	        return paidFee;
	    }

	    public void setPaidFee(Integer paidFee) {
	        this.paidFee = paidFee;
	    }

	    public Integer getUnpaidFee() {
	        return unpaidFee;
	    }

	    public void setUnpaidFee(Integer unpaidFee) {
	        this.unpaidFee = unpaidFee;
	    }

	    public Integer getPoint() {
	        return point;
	    }

	    public void setPoint(Integer point) {
	        this.point = point;
	    }

	    public Integer getLastPoint() {
	        return lastPoint;
	    }

	    public void setLastPoint(Integer lastPoint) {
	        this.lastPoint = lastPoint;
	    }

	    public String getRelatedMobiles() {
	        return relatedMobiles;
	    }

	    public void setRelatedMobiles(String relatedMobiles) {
	        this.relatedMobiles = relatedMobiles;
	    }

	    public String getNotes() {
	        return notes;
	    }

	    public void setNotes(String notes) {
	        this.notes = notes;
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
  
