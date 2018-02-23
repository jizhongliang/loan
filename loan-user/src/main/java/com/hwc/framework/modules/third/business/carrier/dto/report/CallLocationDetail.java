package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zengdongping on 16/11/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallLocationDetail {

    private String city;

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

    @JsonProperty("dialed_time")
    private String dialedTime;

    @JsonProperty("dial_time")
    private String dialTime;

    @JsonProperty("dial_time_avg")
    private String dialAvgTime;

    @JsonProperty("dialed_time_avg")
    private String dialedAvgTime;

    @JsonProperty("dial_num_percent")
    private String dialNumPercent;

    @JsonProperty("dialed_num_percent")
    private String dialedNumPercent;

    @JsonProperty("dial_time_percent")
    private String dialTimePercent;

    @JsonProperty("dialed_time_percent")
    private String dialedTimePercent;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getDialedTime() {
		return dialedTime;
	}

	public void setDialedTime(String dialedTime) {
		this.dialedTime = dialedTime;
	}

	public String getDialTime() {
		return dialTime;
	}

	public void setDialTime(String dialTime) {
		this.dialTime = dialTime;
	}

	public String getDialAvgTime() {
		return dialAvgTime;
	}

	public void setDialAvgTime(String dialAvgTime) {
		this.dialAvgTime = dialAvgTime;
	}

	public String getDialedAvgTime() {
		return dialedAvgTime;
	}

	public void setDialedAvgTime(String dialedAvgTime) {
		this.dialedAvgTime = dialedAvgTime;
	}

	public String getDialNumPercent() {
		return dialNumPercent;
	}

	public void setDialNumPercent(String dialNumPercent) {
		this.dialNumPercent = dialNumPercent;
	}

	public String getDialedNumPercent() {
		return dialedNumPercent;
	}

	public void setDialedNumPercent(String dialedNumPercent) {
		this.dialedNumPercent = dialedNumPercent;
	}

	public String getDialTimePercent() {
		return dialTimePercent;
	}

	public void setDialTimePercent(String dialTimePercent) {
		this.dialTimePercent = dialTimePercent;
	}

	public String getDialedTimePercent() {
		return dialedTimePercent;
	}

	public void setDialedTimePercent(String dialedTimePercent) {
		this.dialedTimePercent = dialedTimePercent;
	}

}
