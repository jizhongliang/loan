/**  
 * Project Name:api-webhook  
 * File Name:MobileVoiceCall.java  
 * Package Name:com.example.service.webhook.business.carrier.dto  
 * Date:2016年7月25日下午5:24:57  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**  
 * ClassName:MobileVoiceCall <br/>  
 * Function:通话记录 <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月25日 下午5:24:57 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MobileVoiceCall {
	@JsonProperty("details_id")
	private String detailsId;
	private String time;
    @JsonProperty("peer_number")
    private String peerNumber;
    private String location;
    @JsonProperty("location_type")
    private String locationType;
    @JsonProperty("duration")
    private Integer durationInSecond;
    @JsonProperty("dial_type")
    private String dialType;
    private Integer fee;

	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPeerNumber() {
		return peerNumber;
	}
	public void setPeerNumber(String peerNumber) {
		this.peerNumber = peerNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Integer getDurationInSecond() {
		return durationInSecond;
	}
	public void setDurationInSecond(Integer durationInSecond) {
		this.durationInSecond = durationInSecond;
	}
	public String getDialType() {
		return dialType;
	}
	public void setDialType(String dialType) {
		this.dialType = dialType;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}


}
  
