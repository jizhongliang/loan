package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/12/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoamAnalysis {
    @JsonProperty("roam_location")
    private String roamLocation;

    @JsonProperty("roam_day_num_3m")
    private String roamDayNum_3;
    @JsonProperty("roam_day_num_6m")
    private String roamDayNum_6;

    @JsonProperty("continue_roam_num_3m")
    private String continueRoamNum_3;
    @JsonProperty("continue_roam_num_6m")
    private String continueRoamNum_6;

    @JsonProperty("max_roam_day_num_3m")
    private String maxRoamDayNum_3;
    @JsonProperty("max_roam_day_num_6m")
    private String maxRoamDayNum_6;
	public String getRoamLocation() {
		return roamLocation;
	}
	public void setRoamLocation(String roamLocation) {
		this.roamLocation = roamLocation;
	}
	public String getRoamDayNum_3() {
		return roamDayNum_3;
	}
	public void setRoamDayNum_3(String roamDayNum_3) {
		this.roamDayNum_3 = roamDayNum_3;
	}
	public String getRoamDayNum_6() {
		return roamDayNum_6;
	}
	public void setRoamDayNum_6(String roamDayNum_6) {
		this.roamDayNum_6 = roamDayNum_6;
	}
	public String getContinueRoamNum_3() {
		return continueRoamNum_3;
	}
	public void setContinueRoamNum_3(String continueRoamNum_3) {
		this.continueRoamNum_3 = continueRoamNum_3;
	}
	public String getContinueRoamNum_6() {
		return continueRoamNum_6;
	}
	public void setContinueRoamNum_6(String continueRoamNum_6) {
		this.continueRoamNum_6 = continueRoamNum_6;
	}
	public String getMaxRoamDayNum_3() {
		return maxRoamDayNum_3;
	}
	public void setMaxRoamDayNum_3(String maxRoamDayNum_3) {
		this.maxRoamDayNum_3 = maxRoamDayNum_3;
	}
	public String getMaxRoamDayNum_6() {
		return maxRoamDayNum_6;
	}
	public void setMaxRoamDayNum_6(String maxRoamDayNum_6) {
		this.maxRoamDayNum_6 = maxRoamDayNum_6;
	}

}
