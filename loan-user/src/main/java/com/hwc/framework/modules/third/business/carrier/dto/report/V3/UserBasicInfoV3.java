package com.hwc.framework.modules.third.business.carrier.dto.report.V3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasicInfoV3 {

    private String name;
    private String idcard;
    @JsonProperty("name_from_custom")
    private String nameFromCustom;
    @JsonProperty("idcard_from_custom")
    private String idcardFromCustom;
    private String mobile;
    private String gender;
    private String age;
    private String constellation;
    private String email;
    private String address;
    @JsonProperty("native_place")
    private String nativePlace;
    @JsonProperty("live_address")
    private String liveAddress;
    @JsonProperty("work_address")
    private String workAddress;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getNameFromCustom() {
		return nameFromCustom;
	}
	public void setNameFromCustom(String nameFromCustom) {
		this.nameFromCustom = nameFromCustom;
	}
	public String getIdcardFromCustom() {
		return idcardFromCustom;
	}
	public void setIdcardFromCustom(String idcardFromCustom) {
		this.idcardFromCustom = idcardFromCustom;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
    
    
}
