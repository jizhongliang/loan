package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveDegree {
    @JsonProperty("call_day_3m")
    private String callDay_3;
    @JsonProperty("dial_num_3m")
    private String dialNum_3;
    @JsonProperty("dialed_num_3m")
    private String dialedNum_3;
    @JsonProperty("dial_duration_3m")
    private String dialDuration_3;
    @JsonProperty("dialed_duration_3m")
    private String dialedDuration_3;

    @JsonProperty("call_day_6m")
    private String callDay_6;
    @JsonProperty("dial_num_6m")
    private String dialNum_6;
    @JsonProperty("dialed_num_6m")
    private String dialedNum_6;
    @JsonProperty("dial_duration_6m")
    private String dialDuration_6;
    @JsonProperty("dialed_duration_6m")
    private String dialedDuration_6;
	public String getCallDay_3() {
		return callDay_3;
	}
	public void setCallDay_3(String callDay_3) {
		this.callDay_3 = callDay_3;
	}
	public String getDialNum_3() {
		return dialNum_3;
	}
	public void setDialNum_3(String dialNum_3) {
		this.dialNum_3 = dialNum_3;
	}
	public String getDialedNum_3() {
		return dialedNum_3;
	}
	public void setDialedNum_3(String dialedNum_3) {
		this.dialedNum_3 = dialedNum_3;
	}
	public String getDialDuration_3() {
		return dialDuration_3;
	}
	public void setDialDuration_3(String dialDuration_3) {
		this.dialDuration_3 = dialDuration_3;
	}
	public String getDialedDuration_3() {
		return dialedDuration_3;
	}
	public void setDialedDuration_3(String dialedDuration_3) {
		this.dialedDuration_3 = dialedDuration_3;
	}
	public String getCallDay_6() {
		return callDay_6;
	}
	public void setCallDay_6(String callDay_6) {
		this.callDay_6 = callDay_6;
	}
	public String getDialNum_6() {
		return dialNum_6;
	}
	public void setDialNum_6(String dialNum_6) {
		this.dialNum_6 = dialNum_6;
	}
	public String getDialedNum_6() {
		return dialedNum_6;
	}
	public void setDialedNum_6(String dialedNum_6) {
		this.dialedNum_6 = dialedNum_6;
	}
	public String getDialDuration_6() {
		return dialDuration_6;
	}
	public void setDialDuration_6(String dialDuration_6) {
		this.dialDuration_6 = dialDuration_6;
	}
	public String getDialedDuration_6() {
		return dialedDuration_6;
	}
	public void setDialedDuration_6(String dialedDuration_6) {
		this.dialedDuration_6 = dialedDuration_6;
	}

}
