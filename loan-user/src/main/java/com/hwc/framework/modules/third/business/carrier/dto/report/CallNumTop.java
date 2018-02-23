package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallNumTop {

    private String city;
    @JsonProperty("peer_number")
    private String peerNumber;
    @JsonProperty("call_num")
    private String callNum;
    @JsonProperty("call_time")
    private String callTime;
    @JsonProperty("dial_num")
    private String dialNum;
    @JsonProperty("dialed_num")
    private String dialedNum;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPeerNumber() {
		return peerNumber;
	}
	public void setPeerNumber(String peerNumber) {
		this.peerNumber = peerNumber;
	}
	public String getCallNum() {
		return callNum;
	}
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
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

}
