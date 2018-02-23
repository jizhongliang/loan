/**  
 * Project Name:api-webhook  
 * File Name:MobileBasic.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月22日下午3:05:21  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:MobileBasic <br/>  
 * Function: 运营商基本信息 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月22日 下午3:05:21 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileBasic {
	private String mobile;

	private String name;

	private String idcard;

	private String carrier;

	private String province;

	private String city;

	@JsonProperty("open_time")
    private String openTime;

	private String level;

	@JsonProperty("package_name")
    private String packageName;

	private Integer state;

	@JsonProperty("available_balance")
    private Integer availableBalance;

	@JsonProperty("last_modify_time")
	private String lastModifyTime;

	private List<PackageUsage> packages = new ArrayList<>();

	private List<FamilyNet> families = new ArrayList<>();

	private List<MobileRecharge> recharges = new ArrayList<>();

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
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
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

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public List<PackageUsage> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageUsage> packages) {
		this.packages = packages;
	}

	public List<FamilyNet> getFamilies() {
		return families;
	}

	public void setFamilies(List<FamilyNet> families) {
		this.families = families;
	}

	public List<MobileRecharge> getRecharges() {
		return recharges;
	}

	public void setRecharges(List<MobileRecharge> recharges) {
		this.recharges = recharges;
	}
}
  
