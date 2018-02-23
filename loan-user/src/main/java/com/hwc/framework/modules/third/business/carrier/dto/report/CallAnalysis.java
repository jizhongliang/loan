package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallAnalysis {

    @JsonProperty("total_num_1m")
    private String totalNum_1;
    @JsonProperty("total_num_3m")
    private String totalNum_3;
    @JsonProperty("total_num_6m")
    private String totalNum_6;
    @JsonProperty("total_num_avg_3m")
    private String totalNum_Avg_3;
    @JsonProperty("total_num_avg_6m")
    private String totalNum_Avg_6;

    @JsonProperty("total_time_1m")
    private String totalTime_1;
    @JsonProperty("total_time_3m")
    private String totalTime_3;
    @JsonProperty("total_time_6m")
    private String totalTime_6;
    @JsonProperty("total_time_avg_3m")
    private String totalTime_Avg_3;
    @JsonProperty("total_time_avg_6m")
    private String totalTime_Avg_6;

    @JsonProperty("total_peer_num_1m")
    private String totalPeerNum_1;
    @JsonProperty("total_peer_num_3m")
    private String totalPeerNum_3;
    @JsonProperty("total_peer_num_6m")
    private String totalPeerNum_6;
    @JsonProperty("total_peer_num_avg_3m")
    private String totalPeerNum_Avg_3;
    @JsonProperty("total_peer_num_avg_6m")
    private String totalPeerNum_Avg_6;

    @JsonProperty("total_city_num_1m")
    private String totalCityNum_1;
    @JsonProperty("total_city_num_3m")
    private String totalCityNum_3;
    @JsonProperty("total_city_num_6m")
    private String totalCityNum_6;
    @JsonProperty("total_city_num_avg_3m")
    private String totalCityNum_Avg_3;
    @JsonProperty("total_city_num_avg_6m")
    private String totalCityNum_Avg_6;

    @JsonProperty("total_dial_num_1m")
    private String totalDialNum_1;
    @JsonProperty("total_dial_num_3m")
    private String totalDialNum_3;
    @JsonProperty("total_dial_num_6m")
    private String totalDialNum_6;
    @JsonProperty("total_dial_num_avg_3m")
    private String totalDialNum_Avg_3;
    @JsonProperty("total_dial_num_avg_6m")
    private String totalDialNum_Avg_6;

    @JsonProperty("total_dialed_num_1m")
    private String totalDialedNum_1;
    @JsonProperty("total_dialed_num_3m")
    private String totalDialedNum_3;
    @JsonProperty("total_dialed_num_6m")
    private String totalDialedNum_6;
    @JsonProperty("total_dialed_num_avg_3m")
    private String totalDialedNum_Avg_3;
    @JsonProperty("total_dialed_num_avg_6m")
    private String totalDialedNum_Avg_6;

    @JsonProperty("total_dial_peer_num_1m")
    private String totalDialPeerNum_1;
    @JsonProperty("total_dial_peer_num_3m")
    private String totalDialPeerNum_3;
    @JsonProperty("total_dial_peer_num_6m")
    private String totalDialPeerNum_6;
    @JsonProperty("total_dial_peer_num_avg_3")
    private String totalDialPeerNum_Avg_3;
    @JsonProperty("total_dial_peer_num_avg_6")
    private String totalDialPeerNum_Avg_6;

    @JsonProperty("total_dialed_peer_num_1m")
    private String totalDialedPeerNum_1;
    @JsonProperty("total_dialed_peer_num_3m")
    private String totalDialedPeerNum_3;
    @JsonProperty("total_dialed_peer_num_6m")
    private String totalDialedPeerNum_6;
    @JsonProperty("total_dialed_peer_num_avg_3m")
    private String totalDialedPeerNum_Avg_3;
    @JsonProperty("total_dialed_peer_num_avg_6m")
    private String totalDialedPeerNum_Avg_6;
	public String getTotalNum_1() {
		return totalNum_1;
	}
	public void setTotalNum_1(String totalNum_1) {
		this.totalNum_1 = totalNum_1;
	}
	public String getTotalNum_3() {
		return totalNum_3;
	}
	public void setTotalNum_3(String totalNum_3) {
		this.totalNum_3 = totalNum_3;
	}
	public String getTotalNum_6() {
		return totalNum_6;
	}
	public void setTotalNum_6(String totalNum_6) {
		this.totalNum_6 = totalNum_6;
	}
	public String getTotalNum_Avg_3() {
		return totalNum_Avg_3;
	}
	public void setTotalNum_Avg_3(String totalNum_Avg_3) {
		this.totalNum_Avg_3 = totalNum_Avg_3;
	}
	public String getTotalNum_Avg_6() {
		return totalNum_Avg_6;
	}
	public void setTotalNum_Avg_6(String totalNum_Avg_6) {
		this.totalNum_Avg_6 = totalNum_Avg_6;
	}
	public String getTotalTime_1() {
		return totalTime_1;
	}
	public void setTotalTime_1(String totalTime_1) {
		this.totalTime_1 = totalTime_1;
	}
	public String getTotalTime_3() {
		return totalTime_3;
	}
	public void setTotalTime_3(String totalTime_3) {
		this.totalTime_3 = totalTime_3;
	}
	public String getTotalTime_6() {
		return totalTime_6;
	}
	public void setTotalTime_6(String totalTime_6) {
		this.totalTime_6 = totalTime_6;
	}
	public String getTotalTime_Avg_3() {
		return totalTime_Avg_3;
	}
	public void setTotalTime_Avg_3(String totalTime_Avg_3) {
		this.totalTime_Avg_3 = totalTime_Avg_3;
	}
	public String getTotalTime_Avg_6() {
		return totalTime_Avg_6;
	}
	public void setTotalTime_Avg_6(String totalTime_Avg_6) {
		this.totalTime_Avg_6 = totalTime_Avg_6;
	}
	public String getTotalPeerNum_1() {
		return totalPeerNum_1;
	}
	public void setTotalPeerNum_1(String totalPeerNum_1) {
		this.totalPeerNum_1 = totalPeerNum_1;
	}
	public String getTotalPeerNum_3() {
		return totalPeerNum_3;
	}
	public void setTotalPeerNum_3(String totalPeerNum_3) {
		this.totalPeerNum_3 = totalPeerNum_3;
	}
	public String getTotalPeerNum_6() {
		return totalPeerNum_6;
	}
	public void setTotalPeerNum_6(String totalPeerNum_6) {
		this.totalPeerNum_6 = totalPeerNum_6;
	}
	public String getTotalPeerNum_Avg_3() {
		return totalPeerNum_Avg_3;
	}
	public void setTotalPeerNum_Avg_3(String totalPeerNum_Avg_3) {
		this.totalPeerNum_Avg_3 = totalPeerNum_Avg_3;
	}
	public String getTotalPeerNum_Avg_6() {
		return totalPeerNum_Avg_6;
	}
	public void setTotalPeerNum_Avg_6(String totalPeerNum_Avg_6) {
		this.totalPeerNum_Avg_6 = totalPeerNum_Avg_6;
	}
	public String getTotalCityNum_1() {
		return totalCityNum_1;
	}
	public void setTotalCityNum_1(String totalCityNum_1) {
		this.totalCityNum_1 = totalCityNum_1;
	}
	public String getTotalCityNum_3() {
		return totalCityNum_3;
	}
	public void setTotalCityNum_3(String totalCityNum_3) {
		this.totalCityNum_3 = totalCityNum_3;
	}
	public String getTotalCityNum_6() {
		return totalCityNum_6;
	}
	public void setTotalCityNum_6(String totalCityNum_6) {
		this.totalCityNum_6 = totalCityNum_6;
	}
	public String getTotalCityNum_Avg_3() {
		return totalCityNum_Avg_3;
	}
	public void setTotalCityNum_Avg_3(String totalCityNum_Avg_3) {
		this.totalCityNum_Avg_3 = totalCityNum_Avg_3;
	}
	public String getTotalCityNum_Avg_6() {
		return totalCityNum_Avg_6;
	}
	public void setTotalCityNum_Avg_6(String totalCityNum_Avg_6) {
		this.totalCityNum_Avg_6 = totalCityNum_Avg_6;
	}
	public String getTotalDialNum_1() {
		return totalDialNum_1;
	}
	public void setTotalDialNum_1(String totalDialNum_1) {
		this.totalDialNum_1 = totalDialNum_1;
	}
	public String getTotalDialNum_3() {
		return totalDialNum_3;
	}
	public void setTotalDialNum_3(String totalDialNum_3) {
		this.totalDialNum_3 = totalDialNum_3;
	}
	public String getTotalDialNum_6() {
		return totalDialNum_6;
	}
	public void setTotalDialNum_6(String totalDialNum_6) {
		this.totalDialNum_6 = totalDialNum_6;
	}
	public String getTotalDialNum_Avg_3() {
		return totalDialNum_Avg_3;
	}
	public void setTotalDialNum_Avg_3(String totalDialNum_Avg_3) {
		this.totalDialNum_Avg_3 = totalDialNum_Avg_3;
	}
	public String getTotalDialNum_Avg_6() {
		return totalDialNum_Avg_6;
	}
	public void setTotalDialNum_Avg_6(String totalDialNum_Avg_6) {
		this.totalDialNum_Avg_6 = totalDialNum_Avg_6;
	}
	public String getTotalDialedNum_1() {
		return totalDialedNum_1;
	}
	public void setTotalDialedNum_1(String totalDialedNum_1) {
		this.totalDialedNum_1 = totalDialedNum_1;
	}
	public String getTotalDialedNum_3() {
		return totalDialedNum_3;
	}
	public void setTotalDialedNum_3(String totalDialedNum_3) {
		this.totalDialedNum_3 = totalDialedNum_3;
	}
	public String getTotalDialedNum_6() {
		return totalDialedNum_6;
	}
	public void setTotalDialedNum_6(String totalDialedNum_6) {
		this.totalDialedNum_6 = totalDialedNum_6;
	}
	public String getTotalDialedNum_Avg_3() {
		return totalDialedNum_Avg_3;
	}
	public void setTotalDialedNum_Avg_3(String totalDialedNum_Avg_3) {
		this.totalDialedNum_Avg_3 = totalDialedNum_Avg_3;
	}
	public String getTotalDialedNum_Avg_6() {
		return totalDialedNum_Avg_6;
	}
	public void setTotalDialedNum_Avg_6(String totalDialedNum_Avg_6) {
		this.totalDialedNum_Avg_6 = totalDialedNum_Avg_6;
	}
	public String getTotalDialPeerNum_1() {
		return totalDialPeerNum_1;
	}
	public void setTotalDialPeerNum_1(String totalDialPeerNum_1) {
		this.totalDialPeerNum_1 = totalDialPeerNum_1;
	}
	public String getTotalDialPeerNum_3() {
		return totalDialPeerNum_3;
	}
	public void setTotalDialPeerNum_3(String totalDialPeerNum_3) {
		this.totalDialPeerNum_3 = totalDialPeerNum_3;
	}
	public String getTotalDialPeerNum_6() {
		return totalDialPeerNum_6;
	}
	public void setTotalDialPeerNum_6(String totalDialPeerNum_6) {
		this.totalDialPeerNum_6 = totalDialPeerNum_6;
	}
	public String getTotalDialPeerNum_Avg_3() {
		return totalDialPeerNum_Avg_3;
	}
	public void setTotalDialPeerNum_Avg_3(String totalDialPeerNum_Avg_3) {
		this.totalDialPeerNum_Avg_3 = totalDialPeerNum_Avg_3;
	}
	public String getTotalDialPeerNum_Avg_6() {
		return totalDialPeerNum_Avg_6;
	}
	public void setTotalDialPeerNum_Avg_6(String totalDialPeerNum_Avg_6) {
		this.totalDialPeerNum_Avg_6 = totalDialPeerNum_Avg_6;
	}
	public String getTotalDialedPeerNum_1() {
		return totalDialedPeerNum_1;
	}
	public void setTotalDialedPeerNum_1(String totalDialedPeerNum_1) {
		this.totalDialedPeerNum_1 = totalDialedPeerNum_1;
	}
	public String getTotalDialedPeerNum_3() {
		return totalDialedPeerNum_3;
	}
	public void setTotalDialedPeerNum_3(String totalDialedPeerNum_3) {
		this.totalDialedPeerNum_3 = totalDialedPeerNum_3;
	}
	public String getTotalDialedPeerNum_6() {
		return totalDialedPeerNum_6;
	}
	public void setTotalDialedPeerNum_6(String totalDialedPeerNum_6) {
		this.totalDialedPeerNum_6 = totalDialedPeerNum_6;
	}
	public String getTotalDialedPeerNum_Avg_3() {
		return totalDialedPeerNum_Avg_3;
	}
	public void setTotalDialedPeerNum_Avg_3(String totalDialedPeerNum_Avg_3) {
		this.totalDialedPeerNum_Avg_3 = totalDialedPeerNum_Avg_3;
	}
	public String getTotalDialedPeerNum_Avg_6() {
		return totalDialedPeerNum_Avg_6;
	}
	public void setTotalDialedPeerNum_Avg_6(String totalDialedPeerNum_Avg_6) {
		this.totalDialedPeerNum_Avg_6 = totalDialedPeerNum_Avg_6;
	}

}
