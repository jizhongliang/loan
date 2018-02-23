package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/12/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoamDetail {

    @JsonProperty("roam_day")
    private String roamDay;
    @JsonProperty("roam_location")
    private String roamLocation;
	public String getRoamDay() {
		return roamDay;
	}
	public void setRoamDay(String roamDay) {
		this.roamDay = roamDay;
	}
	public String getRoamLocation() {
		return roamLocation;
	}
	public void setRoamLocation(String roamLocation) {
		this.roamLocation = roamLocation;
	}
    
    
}
