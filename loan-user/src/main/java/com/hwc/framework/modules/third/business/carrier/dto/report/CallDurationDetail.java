package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallDurationDetail {

    @JsonProperty("time_step")
    private String timeStep;

    @JsonProperty("total_num")
    private String totalNum;

    @JsonProperty("peer_num")
    private String peerNum;

    @JsonProperty("total_time")
    private String totalTime;

    @JsonProperty("dial_num")
    private String dialNum;

    @JsonProperty("dialed_num")
    private String dialedNum;

    @JsonProperty("latest_call_time")
    private String latestCall;

    @JsonProperty("farthest_call_time")
    private String farthestCall;

    public CallDurationDetail(){

    }

    public CallDurationDetail(String timeStep, String totalNum, String peerNum,
                              String totalTime, String dialNum, String dialedNum,
                              String latestCall, String farthestCall) {
        this.timeStep = timeStep;
        this.totalNum = totalNum;
        this.peerNum = peerNum;
        this.totalTime = totalTime;
        this.dialNum = dialNum;
        this.dialedNum = dialedNum;
        this.latestCall = latestCall;
        this.farthestCall = farthestCall;
    }

	public String getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(String timeStep) {
		this.timeStep = timeStep;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getPeerNum() {
		return peerNum;
	}

	public void setPeerNum(String peerNum) {
		this.peerNum = peerNum;
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

	public String getLatestCall() {
		return latestCall;
	}

	public void setLatestCall(String latestCall) {
		this.latestCall = latestCall;
	}

	public String getFarthestCall() {
		return farthestCall;
	}

	public void setFarthestCall(String farthestCall) {
		this.farthestCall = farthestCall;
	}
    
    
}
