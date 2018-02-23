/**  
 * Project Name:api-webhook  
 * File Name:MobileBill.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午4:43:49  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hwc.framework.modules.service.base.Saveable;

import java.util.Date;

/**
 * 
 * ClassName: MobileBillEntity    
 * date: 2016年7月25日 下午4:56:10   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class MobileBillEntity implements Saveable {
	
	    private long id;
	    private String userId;
	    private String mobile;
	    private String billMonth;
	    private String billStartDate;
	    private String billEndDate;
	    private Integer baseFee;
	    private Integer extraServiceFee;
	    private Integer voiceFee;
	    private Integer smsFee;
	    private Integer webFee;
	    private Integer extraFee;
	    private Integer totalFee;
	    private Integer discount;
	    private Integer extraDiscount;
	    private Integer actualFee;
	    private Integer paidFee;
	    private Integer unpaidFee;
	    private Integer point;
	    private Integer lastPoint;
	    private String relatedMobiles;
	    private String notes;
	    private Date createTime;
	    private Date lastModifyTime;
	    
	    private static final String[] keyColumns       = { "id" };
	    private static final String   tableName        = "t_mobilebill";

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

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
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


}
  
