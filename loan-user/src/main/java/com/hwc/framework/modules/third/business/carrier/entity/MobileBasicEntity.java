/**  
 * Project Name:api-webhook  
 * File Name:MobileBasic.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月22日下午3:05:21  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hwc.framework.modules.service.base.Saveable;

import java.util.Date;

/**  
 * 手机基本信息
 * ClassName:MobileBasic <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月22日 下午3:05:21 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(value = { "primaryVal", "keyColumns", "tableName" }, ignoreUnknown = true)
public class MobileBasicEntity implements Saveable {
	
	private long id;
	private String mobile;
	private String userId;
    private String name;
	private String idCard;
    private String carrier;
    private String province;
    private String city;
    private Date openTime;
    private String level;
    private String packageName;
    private Integer state;
    private Integer availableBalance;
    private Date createTime;
    private Date lastModifyTime;
    
    
    private static final String[] keyColumns       = { "id" };
    private static final String   tableName        = "t_mobilebasic";

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(Integer availableBalance) {
		this.availableBalance = availableBalance;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
  
