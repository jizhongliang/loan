/**  
 * Project Name:api-webhook  
 * File Name:MobileVoiceCall.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午5:24:57  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hwc.framework.modules.service.base.Saveable;

import java.util.Date;

/**  
 * MobileVoiceCallEntity <br/>  
 * Function:充值记录数据库实体类 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午5:24:57 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class MobileRechargeEntity implements Saveable {
	
	private long id;
	private String userId;
	private String mobile;
	private String billMonth;
    private String rechargeTime;
    private Integer amount;
    private String type;
    private Date createTime;
    private Date lastModifyTime;
    
    
    
    
    private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_mobilerecharge";

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

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(String rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
  
