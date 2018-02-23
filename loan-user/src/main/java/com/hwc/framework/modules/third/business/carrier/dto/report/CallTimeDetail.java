package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallTimeDetail {

    @JsonProperty("max_time_1m")
    private String maxTime_1;
    @JsonProperty("max_time_3m")
    private String maxTime_3;
    @JsonProperty("max_time_6m")
    private String maxTime_6;

    @JsonProperty("min_time_1m")
    private String minTime_1;
    @JsonProperty("min_time_3m")
    private String minTime_3;
    @JsonProperty("min_time_6m")
    private String minTime_6;

    @JsonProperty("call_time_in_1_min_1m")
    private String time1_1;
    @JsonProperty("call_time_in_1_min_3m")
    private String time1_3;
    @JsonProperty("call_time_in_1_min_6m")
    private String time1_6;
    @JsonProperty("call_time_in_1_min_avg_3m")
    private String time1_avg_3;
    @JsonProperty("call_time_in_1_min_avg_6m")
    private String time1_avg_6;

    @JsonProperty("call_time_in_5_min_1m")
    private String time2_1;
    @JsonProperty("call_time_in_5_min_3m")
    private String time2_3;
    @JsonProperty("call_time_in_5_min_6m")
    private String time2_6;
    @JsonProperty("call_time_in_5_min_avg_3m")
    private String time2_avg_3;
    @JsonProperty("call_time_in_5_min_avg_6m")
    private String time2_avg_6;

    @JsonProperty("call_time_in_10_min_1m")
    private String time3_1;
    @JsonProperty("call_time_in_10_min_3m")
    private String time3_3;
    @JsonProperty("call_time_in_10_min_6m")
    private String time3_6;
    @JsonProperty("call_time_in_10_min_avg_3m")
    private String time3_avg_3;
    @JsonProperty("call_time_in_10_min_avg_6m")
    private String time3_avg_6;

    @JsonProperty("call_time_over_10_min_1m")
    private String time4_1;
    @JsonProperty("call_time_over_10_min_3m")
    private String time4_3;
    @JsonProperty("call_time_over_10_min_6m")
    private String time4_6;
    @JsonProperty("call_time_over_10_min_avg_3m")
    private String time4_avg_3;
    @JsonProperty("call_time_over_10_min_avg_6m")
    private String time4_avg_6;

    @JsonProperty("in_day_time_1m")
    private String daytimeTime_1;
    @JsonProperty("in_day_time_3m")
    private String daytimeTime_3;
    @JsonProperty("in_day_time_6m")
    private String daytimeTime_6;
    @JsonProperty("in_day_time_avg_3m")
    private String daytimeTime_avg_3;
    @JsonProperty("in_day_time_avg_6m")
    private String daytimeTime_avg_6;


    @JsonProperty("in_night_time_1m")
    private String nightTime_1;
    @JsonProperty("in_night_time_3m")
    private String nightTime_3;
    @JsonProperty("in_night_time_6m")
    private String nightTime_6;
    @JsonProperty("in_night_time_avg_3m")
    private String nightTime_avg_3;
    @JsonProperty("in_night_time_avg_6m")
    private String nightTime_avg_6;

    @JsonProperty("local_time_1m")
    private String localTime_1;
    @JsonProperty("local_time_3m")
    private String localTime_3;
    @JsonProperty("local_time_6m")
    private String localTime_6;
    @JsonProperty("local_time_avg_3m")
    private String localTime_avg_3;
    @JsonProperty("local_time_avg_6m")
    private String localTime_avg_6;

    @JsonProperty("remote_time_1m")
    private String remoteTime_1;
    @JsonProperty("remote_time_3m")
    private String remoteTime_3;
    @JsonProperty("remote_time_6m")
    private String remoteTime_6;
    @JsonProperty("remote_time_avg_3m")
    private String remoteTime_avg_3;
    @JsonProperty("remote_time_avg_6m")
    private String remoteTime_avg_6;

    @JsonProperty("in_day_num_1m")
    private String daytimeNum_1;
    @JsonProperty("in_day_num_3m")
    private String daytimeNum_3;
    @JsonProperty("in_day_num_6m")
    private String daytimeNum_6;
    @JsonProperty("in_day_num_avg_3m")
    private String daytimeNum_avg_3;
    @JsonProperty("in_day_num_avg_6m")
    private String daytimeNum_avg_6;

    @JsonProperty("in_night_num_1m")
    private String nightNum_1;
    @JsonProperty("in_night_num_3m")
    private String nightNum_3;
    @JsonProperty("in_night_num_6m")
    private String nightNum_6;
    @JsonProperty("in_night_num_avg_3m")
    private String nightNum_avg_3;
    @JsonProperty("in_night_num_avg_6m")
    private String nightNum_avg_6;

    @JsonProperty("local_num_1m")
    private String localNum_1;
    @JsonProperty("local_num_3m")
    private String localNum_3;
    @JsonProperty("local_num_6m")
    private String localNum_6;
    @JsonProperty("local_num_avg_3m")
    private String localNum_avg_3;
    @JsonProperty("local_num_avg_6m")
    private String localNum_avg_6;

    @JsonProperty("remote_num_1m")
    private String remoteNum_1;
    @JsonProperty("remote_num_3m")
    private String remoteNum_3;
    @JsonProperty("remote_num_6m")
    private String remoteNum_6;
    @JsonProperty("remote_num_avg_3m")
    private String remoteNum_avg_3;
    @JsonProperty("remote_num_avg_6m")
    private String remoteNum_avg_6;
	public String getMaxTime_1() {
		return maxTime_1;
	}
	public void setMaxTime_1(String maxTime_1) {
		this.maxTime_1 = maxTime_1;
	}
	public String getMaxTime_3() {
		return maxTime_3;
	}
	public void setMaxTime_3(String maxTime_3) {
		this.maxTime_3 = maxTime_3;
	}
	public String getMaxTime_6() {
		return maxTime_6;
	}
	public void setMaxTime_6(String maxTime_6) {
		this.maxTime_6 = maxTime_6;
	}
	public String getMinTime_1() {
		return minTime_1;
	}
	public void setMinTime_1(String minTime_1) {
		this.minTime_1 = minTime_1;
	}
	public String getMinTime_3() {
		return minTime_3;
	}
	public void setMinTime_3(String minTime_3) {
		this.minTime_3 = minTime_3;
	}
	public String getMinTime_6() {
		return minTime_6;
	}
	public void setMinTime_6(String minTime_6) {
		this.minTime_6 = minTime_6;
	}
	public String getTime1_1() {
		return time1_1;
	}
	public void setTime1_1(String time1_1) {
		this.time1_1 = time1_1;
	}
	public String getTime1_3() {
		return time1_3;
	}
	public void setTime1_3(String time1_3) {
		this.time1_3 = time1_3;
	}
	public String getTime1_6() {
		return time1_6;
	}
	public void setTime1_6(String time1_6) {
		this.time1_6 = time1_6;
	}
	public String getTime1_avg_3() {
		return time1_avg_3;
	}
	public void setTime1_avg_3(String time1_avg_3) {
		this.time1_avg_3 = time1_avg_3;
	}
	public String getTime1_avg_6() {
		return time1_avg_6;
	}
	public void setTime1_avg_6(String time1_avg_6) {
		this.time1_avg_6 = time1_avg_6;
	}
	public String getTime2_1() {
		return time2_1;
	}
	public void setTime2_1(String time2_1) {
		this.time2_1 = time2_1;
	}
	public String getTime2_3() {
		return time2_3;
	}
	public void setTime2_3(String time2_3) {
		this.time2_3 = time2_3;
	}
	public String getTime2_6() {
		return time2_6;
	}
	public void setTime2_6(String time2_6) {
		this.time2_6 = time2_6;
	}
	public String getTime2_avg_3() {
		return time2_avg_3;
	}
	public void setTime2_avg_3(String time2_avg_3) {
		this.time2_avg_3 = time2_avg_3;
	}
	public String getTime2_avg_6() {
		return time2_avg_6;
	}
	public void setTime2_avg_6(String time2_avg_6) {
		this.time2_avg_6 = time2_avg_6;
	}
	public String getTime3_1() {
		return time3_1;
	}
	public void setTime3_1(String time3_1) {
		this.time3_1 = time3_1;
	}
	public String getTime3_3() {
		return time3_3;
	}
	public void setTime3_3(String time3_3) {
		this.time3_3 = time3_3;
	}
	public String getTime3_6() {
		return time3_6;
	}
	public void setTime3_6(String time3_6) {
		this.time3_6 = time3_6;
	}
	public String getTime3_avg_3() {
		return time3_avg_3;
	}
	public void setTime3_avg_3(String time3_avg_3) {
		this.time3_avg_3 = time3_avg_3;
	}
	public String getTime3_avg_6() {
		return time3_avg_6;
	}
	public void setTime3_avg_6(String time3_avg_6) {
		this.time3_avg_6 = time3_avg_6;
	}
	public String getTime4_1() {
		return time4_1;
	}
	public void setTime4_1(String time4_1) {
		this.time4_1 = time4_1;
	}
	public String getTime4_3() {
		return time4_3;
	}
	public void setTime4_3(String time4_3) {
		this.time4_3 = time4_3;
	}
	public String getTime4_6() {
		return time4_6;
	}
	public void setTime4_6(String time4_6) {
		this.time4_6 = time4_6;
	}
	public String getTime4_avg_3() {
		return time4_avg_3;
	}
	public void setTime4_avg_3(String time4_avg_3) {
		this.time4_avg_3 = time4_avg_3;
	}
	public String getTime4_avg_6() {
		return time4_avg_6;
	}
	public void setTime4_avg_6(String time4_avg_6) {
		this.time4_avg_6 = time4_avg_6;
	}
	public String getDaytimeTime_1() {
		return daytimeTime_1;
	}
	public void setDaytimeTime_1(String daytimeTime_1) {
		this.daytimeTime_1 = daytimeTime_1;
	}
	public String getDaytimeTime_3() {
		return daytimeTime_3;
	}
	public void setDaytimeTime_3(String daytimeTime_3) {
		this.daytimeTime_3 = daytimeTime_3;
	}
	public String getDaytimeTime_6() {
		return daytimeTime_6;
	}
	public void setDaytimeTime_6(String daytimeTime_6) {
		this.daytimeTime_6 = daytimeTime_6;
	}
	public String getDaytimeTime_avg_3() {
		return daytimeTime_avg_3;
	}
	public void setDaytimeTime_avg_3(String daytimeTime_avg_3) {
		this.daytimeTime_avg_3 = daytimeTime_avg_3;
	}
	public String getDaytimeTime_avg_6() {
		return daytimeTime_avg_6;
	}
	public void setDaytimeTime_avg_6(String daytimeTime_avg_6) {
		this.daytimeTime_avg_6 = daytimeTime_avg_6;
	}
	public String getNightTime_1() {
		return nightTime_1;
	}
	public void setNightTime_1(String nightTime_1) {
		this.nightTime_1 = nightTime_1;
	}
	public String getNightTime_3() {
		return nightTime_3;
	}
	public void setNightTime_3(String nightTime_3) {
		this.nightTime_3 = nightTime_3;
	}
	public String getNightTime_6() {
		return nightTime_6;
	}
	public void setNightTime_6(String nightTime_6) {
		this.nightTime_6 = nightTime_6;
	}
	public String getNightTime_avg_3() {
		return nightTime_avg_3;
	}
	public void setNightTime_avg_3(String nightTime_avg_3) {
		this.nightTime_avg_3 = nightTime_avg_3;
	}
	public String getNightTime_avg_6() {
		return nightTime_avg_6;
	}
	public void setNightTime_avg_6(String nightTime_avg_6) {
		this.nightTime_avg_6 = nightTime_avg_6;
	}
	public String getLocalTime_1() {
		return localTime_1;
	}
	public void setLocalTime_1(String localTime_1) {
		this.localTime_1 = localTime_1;
	}
	public String getLocalTime_3() {
		return localTime_3;
	}
	public void setLocalTime_3(String localTime_3) {
		this.localTime_3 = localTime_3;
	}
	public String getLocalTime_6() {
		return localTime_6;
	}
	public void setLocalTime_6(String localTime_6) {
		this.localTime_6 = localTime_6;
	}
	public String getLocalTime_avg_3() {
		return localTime_avg_3;
	}
	public void setLocalTime_avg_3(String localTime_avg_3) {
		this.localTime_avg_3 = localTime_avg_3;
	}
	public String getLocalTime_avg_6() {
		return localTime_avg_6;
	}
	public void setLocalTime_avg_6(String localTime_avg_6) {
		this.localTime_avg_6 = localTime_avg_6;
	}
	public String getRemoteTime_1() {
		return remoteTime_1;
	}
	public void setRemoteTime_1(String remoteTime_1) {
		this.remoteTime_1 = remoteTime_1;
	}
	public String getRemoteTime_3() {
		return remoteTime_3;
	}
	public void setRemoteTime_3(String remoteTime_3) {
		this.remoteTime_3 = remoteTime_3;
	}
	public String getRemoteTime_6() {
		return remoteTime_6;
	}
	public void setRemoteTime_6(String remoteTime_6) {
		this.remoteTime_6 = remoteTime_6;
	}
	public String getRemoteTime_avg_3() {
		return remoteTime_avg_3;
	}
	public void setRemoteTime_avg_3(String remoteTime_avg_3) {
		this.remoteTime_avg_3 = remoteTime_avg_3;
	}
	public String getRemoteTime_avg_6() {
		return remoteTime_avg_6;
	}
	public void setRemoteTime_avg_6(String remoteTime_avg_6) {
		this.remoteTime_avg_6 = remoteTime_avg_6;
	}
	public String getDaytimeNum_1() {
		return daytimeNum_1;
	}
	public void setDaytimeNum_1(String daytimeNum_1) {
		this.daytimeNum_1 = daytimeNum_1;
	}
	public String getDaytimeNum_3() {
		return daytimeNum_3;
	}
	public void setDaytimeNum_3(String daytimeNum_3) {
		this.daytimeNum_3 = daytimeNum_3;
	}
	public String getDaytimeNum_6() {
		return daytimeNum_6;
	}
	public void setDaytimeNum_6(String daytimeNum_6) {
		this.daytimeNum_6 = daytimeNum_6;
	}
	public String getDaytimeNum_avg_3() {
		return daytimeNum_avg_3;
	}
	public void setDaytimeNum_avg_3(String daytimeNum_avg_3) {
		this.daytimeNum_avg_3 = daytimeNum_avg_3;
	}
	public String getDaytimeNum_avg_6() {
		return daytimeNum_avg_6;
	}
	public void setDaytimeNum_avg_6(String daytimeNum_avg_6) {
		this.daytimeNum_avg_6 = daytimeNum_avg_6;
	}
	public String getNightNum_1() {
		return nightNum_1;
	}
	public void setNightNum_1(String nightNum_1) {
		this.nightNum_1 = nightNum_1;
	}
	public String getNightNum_3() {
		return nightNum_3;
	}
	public void setNightNum_3(String nightNum_3) {
		this.nightNum_3 = nightNum_3;
	}
	public String getNightNum_6() {
		return nightNum_6;
	}
	public void setNightNum_6(String nightNum_6) {
		this.nightNum_6 = nightNum_6;
	}
	public String getNightNum_avg_3() {
		return nightNum_avg_3;
	}
	public void setNightNum_avg_3(String nightNum_avg_3) {
		this.nightNum_avg_3 = nightNum_avg_3;
	}
	public String getNightNum_avg_6() {
		return nightNum_avg_6;
	}
	public void setNightNum_avg_6(String nightNum_avg_6) {
		this.nightNum_avg_6 = nightNum_avg_6;
	}
	public String getLocalNum_1() {
		return localNum_1;
	}
	public void setLocalNum_1(String localNum_1) {
		this.localNum_1 = localNum_1;
	}
	public String getLocalNum_3() {
		return localNum_3;
	}
	public void setLocalNum_3(String localNum_3) {
		this.localNum_3 = localNum_3;
	}
	public String getLocalNum_6() {
		return localNum_6;
	}
	public void setLocalNum_6(String localNum_6) {
		this.localNum_6 = localNum_6;
	}
	public String getLocalNum_avg_3() {
		return localNum_avg_3;
	}
	public void setLocalNum_avg_3(String localNum_avg_3) {
		this.localNum_avg_3 = localNum_avg_3;
	}
	public String getLocalNum_avg_6() {
		return localNum_avg_6;
	}
	public void setLocalNum_avg_6(String localNum_avg_6) {
		this.localNum_avg_6 = localNum_avg_6;
	}
	public String getRemoteNum_1() {
		return remoteNum_1;
	}
	public void setRemoteNum_1(String remoteNum_1) {
		this.remoteNum_1 = remoteNum_1;
	}
	public String getRemoteNum_3() {
		return remoteNum_3;
	}
	public void setRemoteNum_3(String remoteNum_3) {
		this.remoteNum_3 = remoteNum_3;
	}
	public String getRemoteNum_6() {
		return remoteNum_6;
	}
	public void setRemoteNum_6(String remoteNum_6) {
		this.remoteNum_6 = remoteNum_6;
	}
	public String getRemoteNum_avg_3() {
		return remoteNum_avg_3;
	}
	public void setRemoteNum_avg_3(String remoteNum_avg_3) {
		this.remoteNum_avg_3 = remoteNum_avg_3;
	}
	public String getRemoteNum_avg_6() {
		return remoteNum_avg_6;
	}
	public void setRemoteNum_avg_6(String remoteNum_avg_6) {
		this.remoteNum_avg_6 = remoteNum_avg_6;
	}



}
