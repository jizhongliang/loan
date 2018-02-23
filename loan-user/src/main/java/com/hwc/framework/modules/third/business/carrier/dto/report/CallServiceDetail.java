package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallServiceDetail {

    @JsonProperty("service_num")
    private String servicePhone;
    private String months;
    @JsonProperty("total_num")
    private String totalNum;
    @JsonProperty("total_time")
    private String totalTime;
    @JsonProperty("dial_num")
    private String dialNum;
    @JsonProperty("dialed_num")
    private String dialedNum;
    @JsonProperty("dial_time")
    private String dialTime;
    @JsonProperty("dialed_time")
    private String dialedTime;
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getDialNum() {
		return dialNum;
	}
	public void setDialNum(String dialNum) {
		this.dialNum = dialNum;
	}
	public String getDialedNum() {
		return dialedNum;
	}
	public void setDialedNum(String dialedNum) {
		this.dialedNum = dialedNum;
	}
	public String getDialTime() {
		return dialTime;
	}
	public void setDialTime(String dialTime) {
		this.dialTime = dialTime;
	}
	public String getDialedTime() {
		return dialedTime;
	}
	public void setDialedTime(String dialedTime) {
		this.dialedTime = dialedTime;
	}
    
}
