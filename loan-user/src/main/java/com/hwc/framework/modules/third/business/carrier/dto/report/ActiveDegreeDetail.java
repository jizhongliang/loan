package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveDegreeDetail {

    @JsonProperty("call_day_1m")
    private String voiceCallDay_1;
    @JsonProperty("call_day_3m")
    private String voiceCallDay_3;
    @JsonProperty("call_day_6m")
    private String voiceCallDay_6;
    @JsonProperty("call_day_avg_3m")
    private String voiceCallDay_avg_3;
    @JsonProperty("call_day_avg_6m")
    private String voiceCallDay_avg_6;


    @JsonProperty("sms_day_1m")
    private String shortMessageDay_1;
    @JsonProperty("sms_day_3m")
    private String shortMessageDay_3;
    @JsonProperty("sms_day_6m")
    private String shortMessageDay_6;
    @JsonProperty("sms_day_avg_3m")
    private String shortMessageDay_avg_3;
    @JsonProperty("sms_day_avg_6m")
    private String shortMessageDay_avg_6;

    @JsonProperty("recharge_time_1m")
    private String rechargeTimes_1;
    @JsonProperty("recharge_time_3m")
    private String rechargeTimes_3;
    @JsonProperty("recharge_time_6m")
    private String rechargeTimes_6;
    @JsonProperty("recharge_time_avg_3m")
    private String rechargeTimes_avg_3;
    @JsonProperty("recharge_time_avg_6m")
    private String rechargeTimes_avg_6;


    @JsonProperty("call_time_1m")
    private String callTimes_1;
    @JsonProperty("call_time_3m")
    private String callTimes_3;
    @JsonProperty("call_time_6m")
    private String callTimes_6;
    @JsonProperty("call_time_avg_3m")
    private String callTimes_avg_3;
    @JsonProperty("call_time_avg_6m")
    private String callTimes_avg_6;

    @JsonProperty("sms_time_1m")
    private String shortMessageTimes_1;
    @JsonProperty("sms_time_3m")
    private String shortMessageTimes_3;
    @JsonProperty("sms_time_6m")
    private String shortMessageTimes_6;
    @JsonProperty("sms_time_avg_3m")
    private String shortMessageTimes_avg_3;
    @JsonProperty("sms_time_avg_6m")
    private String shortMessageTimes_avg_6;

    @JsonProperty("flow_total_1m")
    private String packageTotal_1;
    @JsonProperty("flow_total_3m")
    private String packageTotal_3;
    @JsonProperty("flow_total_6m")
    private String packageTotal_6;
    @JsonProperty("flow_total_avg_3m")
    private String packageTotal_avg_3;
    @JsonProperty("flow_total_avg_6m")
    private String packageTotal_avg_6;

    @JsonProperty("flow_used_1m")
    private String packageUsage_1;
    @JsonProperty("flow_used_3m")
    private String packageUsage_3;
    @JsonProperty("flow_used_6m")
    private String packageUsage_6;
    @JsonProperty("flow_used_avg_3m")
    private String packageUsage_avg_3;
    @JsonProperty("flow_used_avg_6m")
    private String packageUsage_avg_6;

    @JsonProperty("call_avg_duration_1m")
    private String callAvgDuration_1;
    @JsonProperty("call_avg_duration_3m")
    private String callAvgDuration_3;
    @JsonProperty("call_avg_duration_6m")
    private String callAvgDuration_6;

    @JsonProperty("no_dial_day_1m")
    private String noDialDay_1;
    @JsonProperty("no_dial_day_3m")
    private String noDialDay_3;
    @JsonProperty("no_dial_day_6m")
    private String noDialDay_6;
    @JsonProperty("no_dial_day_avg_3m")
    private String noDialDay_avg_3;
    @JsonProperty("no_dial_day_avg_6m")
    private String noDialDay_avg_6;

    @JsonProperty("no_dial_day_percent_1m")
    private String noDialDayPercent_1;
    @JsonProperty("no_dial_day_percent_3m")
    private String noDialDayPercent_3;
    @JsonProperty("no_dial_day_percent_6m")
    private String noDialDayPercent_6;

    @JsonProperty("no_call_day_1m")
    private String noCallDay_1;
    @JsonProperty("no_call_day_3m")
    private String noCallDay_3;
    @JsonProperty("no_call_day_6m")
    private String noCallDay_6;
    @JsonProperty("no_call_day_avg_3m")
    private String noCallDay_avg_3;
    @JsonProperty("no_call_day_avg_6m")
    private String noCallDay_avg_6;

    @JsonProperty("no_call_day_percent_1m")
    private String noCallDayPercent_1;
    @JsonProperty("no_call_day_percent_3m")
    private String noCallDayPercent_3;
    @JsonProperty("no_call_day_percent_6m")
    private String noCallDayPercent_6;

    @JsonProperty("power_on_max_day_1m")
    private String powerOnMaxDay_1;
    @JsonProperty("power_off_day_1m")
    private String powerOffDay_1;
    @JsonProperty("power_off_day_percent_1m")
    private String powerOffDayPercent_1;
    @JsonProperty("continue_power_off_num_1m")
    private String continuePowerOffNum_1;
	public String getVoiceCallDay_1() {
		return voiceCallDay_1;
	}
	public void setVoiceCallDay_1(String voiceCallDay_1) {
		this.voiceCallDay_1 = voiceCallDay_1;
	}
	public String getVoiceCallDay_3() {
		return voiceCallDay_3;
	}
	public void setVoiceCallDay_3(String voiceCallDay_3) {
		this.voiceCallDay_3 = voiceCallDay_3;
	}
	public String getVoiceCallDay_6() {
		return voiceCallDay_6;
	}
	public void setVoiceCallDay_6(String voiceCallDay_6) {
		this.voiceCallDay_6 = voiceCallDay_6;
	}
	public String getVoiceCallDay_avg_3() {
		return voiceCallDay_avg_3;
	}
	public void setVoiceCallDay_avg_3(String voiceCallDay_avg_3) {
		this.voiceCallDay_avg_3 = voiceCallDay_avg_3;
	}
	public String getVoiceCallDay_avg_6() {
		return voiceCallDay_avg_6;
	}
	public void setVoiceCallDay_avg_6(String voiceCallDay_avg_6) {
		this.voiceCallDay_avg_6 = voiceCallDay_avg_6;
	}
	public String getShortMessageDay_1() {
		return shortMessageDay_1;
	}
	public void setShortMessageDay_1(String shortMessageDay_1) {
		this.shortMessageDay_1 = shortMessageDay_1;
	}
	public String getShortMessageDay_3() {
		return shortMessageDay_3;
	}
	public void setShortMessageDay_3(String shortMessageDay_3) {
		this.shortMessageDay_3 = shortMessageDay_3;
	}
	public String getShortMessageDay_6() {
		return shortMessageDay_6;
	}
	public void setShortMessageDay_6(String shortMessageDay_6) {
		this.shortMessageDay_6 = shortMessageDay_6;
	}
	public String getShortMessageDay_avg_3() {
		return shortMessageDay_avg_3;
	}
	public void setShortMessageDay_avg_3(String shortMessageDay_avg_3) {
		this.shortMessageDay_avg_3 = shortMessageDay_avg_3;
	}
	public String getShortMessageDay_avg_6() {
		return shortMessageDay_avg_6;
	}
	public void setShortMessageDay_avg_6(String shortMessageDay_avg_6) {
		this.shortMessageDay_avg_6 = shortMessageDay_avg_6;
	}
	public String getRechargeTimes_1() {
		return rechargeTimes_1;
	}
	public void setRechargeTimes_1(String rechargeTimes_1) {
		this.rechargeTimes_1 = rechargeTimes_1;
	}
	public String getRechargeTimes_3() {
		return rechargeTimes_3;
	}
	public void setRechargeTimes_3(String rechargeTimes_3) {
		this.rechargeTimes_3 = rechargeTimes_3;
	}
	public String getRechargeTimes_6() {
		return rechargeTimes_6;
	}
	public void setRechargeTimes_6(String rechargeTimes_6) {
		this.rechargeTimes_6 = rechargeTimes_6;
	}
	public String getRechargeTimes_avg_3() {
		return rechargeTimes_avg_3;
	}
	public void setRechargeTimes_avg_3(String rechargeTimes_avg_3) {
		this.rechargeTimes_avg_3 = rechargeTimes_avg_3;
	}
	public String getRechargeTimes_avg_6() {
		return rechargeTimes_avg_6;
	}
	public void setRechargeTimes_avg_6(String rechargeTimes_avg_6) {
		this.rechargeTimes_avg_6 = rechargeTimes_avg_6;
	}
	public String getCallTimes_1() {
		return callTimes_1;
	}
	public void setCallTimes_1(String callTimes_1) {
		this.callTimes_1 = callTimes_1;
	}
	public String getCallTimes_3() {
		return callTimes_3;
	}
	public void setCallTimes_3(String callTimes_3) {
		this.callTimes_3 = callTimes_3;
	}
	public String getCallTimes_6() {
		return callTimes_6;
	}
	public void setCallTimes_6(String callTimes_6) {
		this.callTimes_6 = callTimes_6;
	}
	public String getCallTimes_avg_3() {
		return callTimes_avg_3;
	}
	public void setCallTimes_avg_3(String callTimes_avg_3) {
		this.callTimes_avg_3 = callTimes_avg_3;
	}
	public String getCallTimes_avg_6() {
		return callTimes_avg_6;
	}
	public void setCallTimes_avg_6(String callTimes_avg_6) {
		this.callTimes_avg_6 = callTimes_avg_6;
	}
	public String getShortMessageTimes_1() {
		return shortMessageTimes_1;
	}
	public void setShortMessageTimes_1(String shortMessageTimes_1) {
		this.shortMessageTimes_1 = shortMessageTimes_1;
	}
	public String getShortMessageTimes_3() {
		return shortMessageTimes_3;
	}
	public void setShortMessageTimes_3(String shortMessageTimes_3) {
		this.shortMessageTimes_3 = shortMessageTimes_3;
	}
	public String getShortMessageTimes_6() {
		return shortMessageTimes_6;
	}
	public void setShortMessageTimes_6(String shortMessageTimes_6) {
		this.shortMessageTimes_6 = shortMessageTimes_6;
	}
	public String getShortMessageTimes_avg_3() {
		return shortMessageTimes_avg_3;
	}
	public void setShortMessageTimes_avg_3(String shortMessageTimes_avg_3) {
		this.shortMessageTimes_avg_3 = shortMessageTimes_avg_3;
	}
	public String getShortMessageTimes_avg_6() {
		return shortMessageTimes_avg_6;
	}
	public void setShortMessageTimes_avg_6(String shortMessageTimes_avg_6) {
		this.shortMessageTimes_avg_6 = shortMessageTimes_avg_6;
	}
	public String getPackageTotal_1() {
		return packageTotal_1;
	}
	public void setPackageTotal_1(String packageTotal_1) {
		this.packageTotal_1 = packageTotal_1;
	}
	public String getPackageTotal_3() {
		return packageTotal_3;
	}
	public void setPackageTotal_3(String packageTotal_3) {
		this.packageTotal_3 = packageTotal_3;
	}
	public String getPackageTotal_6() {
		return packageTotal_6;
	}
	public void setPackageTotal_6(String packageTotal_6) {
		this.packageTotal_6 = packageTotal_6;
	}
	public String getPackageTotal_avg_3() {
		return packageTotal_avg_3;
	}
	public void setPackageTotal_avg_3(String packageTotal_avg_3) {
		this.packageTotal_avg_3 = packageTotal_avg_3;
	}
	public String getPackageTotal_avg_6() {
		return packageTotal_avg_6;
	}
	public void setPackageTotal_avg_6(String packageTotal_avg_6) {
		this.packageTotal_avg_6 = packageTotal_avg_6;
	}
	public String getPackageUsage_1() {
		return packageUsage_1;
	}
	public void setPackageUsage_1(String packageUsage_1) {
		this.packageUsage_1 = packageUsage_1;
	}
	public String getPackageUsage_3() {
		return packageUsage_3;
	}
	public void setPackageUsage_3(String packageUsage_3) {
		this.packageUsage_3 = packageUsage_3;
	}
	public String getPackageUsage_6() {
		return packageUsage_6;
	}
	public void setPackageUsage_6(String packageUsage_6) {
		this.packageUsage_6 = packageUsage_6;
	}
	public String getPackageUsage_avg_3() {
		return packageUsage_avg_3;
	}
	public void setPackageUsage_avg_3(String packageUsage_avg_3) {
		this.packageUsage_avg_3 = packageUsage_avg_3;
	}
	public String getPackageUsage_avg_6() {
		return packageUsage_avg_6;
	}
	public void setPackageUsage_avg_6(String packageUsage_avg_6) {
		this.packageUsage_avg_6 = packageUsage_avg_6;
	}
	public String getCallAvgDuration_1() {
		return callAvgDuration_1;
	}
	public void setCallAvgDuration_1(String callAvgDuration_1) {
		this.callAvgDuration_1 = callAvgDuration_1;
	}
	public String getCallAvgDuration_3() {
		return callAvgDuration_3;
	}
	public void setCallAvgDuration_3(String callAvgDuration_3) {
		this.callAvgDuration_3 = callAvgDuration_3;
	}
	public String getCallAvgDuration_6() {
		return callAvgDuration_6;
	}
	public void setCallAvgDuration_6(String callAvgDuration_6) {
		this.callAvgDuration_6 = callAvgDuration_6;
	}
	public String getNoDialDay_1() {
		return noDialDay_1;
	}
	public void setNoDialDay_1(String noDialDay_1) {
		this.noDialDay_1 = noDialDay_1;
	}
	public String getNoDialDay_3() {
		return noDialDay_3;
	}
	public void setNoDialDay_3(String noDialDay_3) {
		this.noDialDay_3 = noDialDay_3;
	}
	public String getNoDialDay_6() {
		return noDialDay_6;
	}
	public void setNoDialDay_6(String noDialDay_6) {
		this.noDialDay_6 = noDialDay_6;
	}
	public String getNoDialDay_avg_3() {
		return noDialDay_avg_3;
	}
	public void setNoDialDay_avg_3(String noDialDay_avg_3) {
		this.noDialDay_avg_3 = noDialDay_avg_3;
	}
	public String getNoDialDay_avg_6() {
		return noDialDay_avg_6;
	}
	public void setNoDialDay_avg_6(String noDialDay_avg_6) {
		this.noDialDay_avg_6 = noDialDay_avg_6;
	}
	public String getNoDialDayPercent_1() {
		return noDialDayPercent_1;
	}
	public void setNoDialDayPercent_1(String noDialDayPercent_1) {
		this.noDialDayPercent_1 = noDialDayPercent_1;
	}
	public String getNoDialDayPercent_3() {
		return noDialDayPercent_3;
	}
	public void setNoDialDayPercent_3(String noDialDayPercent_3) {
		this.noDialDayPercent_3 = noDialDayPercent_3;
	}
	public String getNoDialDayPercent_6() {
		return noDialDayPercent_6;
	}
	public void setNoDialDayPercent_6(String noDialDayPercent_6) {
		this.noDialDayPercent_6 = noDialDayPercent_6;
	}
	public String getNoCallDay_1() {
		return noCallDay_1;
	}
	public void setNoCallDay_1(String noCallDay_1) {
		this.noCallDay_1 = noCallDay_1;
	}
	public String getNoCallDay_3() {
		return noCallDay_3;
	}
	public void setNoCallDay_3(String noCallDay_3) {
		this.noCallDay_3 = noCallDay_3;
	}
	public String getNoCallDay_6() {
		return noCallDay_6;
	}
	public void setNoCallDay_6(String noCallDay_6) {
		this.noCallDay_6 = noCallDay_6;
	}
	public String getNoCallDay_avg_3() {
		return noCallDay_avg_3;
	}
	public void setNoCallDay_avg_3(String noCallDay_avg_3) {
		this.noCallDay_avg_3 = noCallDay_avg_3;
	}
	public String getNoCallDay_avg_6() {
		return noCallDay_avg_6;
	}
	public void setNoCallDay_avg_6(String noCallDay_avg_6) {
		this.noCallDay_avg_6 = noCallDay_avg_6;
	}
	public String getNoCallDayPercent_1() {
		return noCallDayPercent_1;
	}
	public void setNoCallDayPercent_1(String noCallDayPercent_1) {
		this.noCallDayPercent_1 = noCallDayPercent_1;
	}
	public String getNoCallDayPercent_3() {
		return noCallDayPercent_3;
	}
	public void setNoCallDayPercent_3(String noCallDayPercent_3) {
		this.noCallDayPercent_3 = noCallDayPercent_3;
	}
	public String getNoCallDayPercent_6() {
		return noCallDayPercent_6;
	}
	public void setNoCallDayPercent_6(String noCallDayPercent_6) {
		this.noCallDayPercent_6 = noCallDayPercent_6;
	}
	public String getPowerOnMaxDay_1() {
		return powerOnMaxDay_1;
	}
	public void setPowerOnMaxDay_1(String powerOnMaxDay_1) {
		this.powerOnMaxDay_1 = powerOnMaxDay_1;
	}
	public String getPowerOffDay_1() {
		return powerOffDay_1;
	}
	public void setPowerOffDay_1(String powerOffDay_1) {
		this.powerOffDay_1 = powerOffDay_1;
	}
	public String getPowerOffDayPercent_1() {
		return powerOffDayPercent_1;
	}
	public void setPowerOffDayPercent_1(String powerOffDayPercent_1) {
		this.powerOffDayPercent_1 = powerOffDayPercent_1;
	}
	public String getContinuePowerOffNum_1() {
		return continuePowerOffNum_1;
	}
	public void setContinuePowerOffNum_1(String continuePowerOffNum_1) {
		this.continuePowerOffNum_1 = continuePowerOffNum_1;
	}


}
