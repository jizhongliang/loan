package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zengdongping on 16/11/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumptionAnalysis {
    @JsonProperty("total_fee_3m")
    private String totalFee_3;
    @JsonProperty("total_fee_6m")
    private String totalFee_6;

    @JsonProperty("recharge_time_3m")
    private String rechargeTimes_3;
    @JsonProperty("recharge_time_6m")
    private String rechargeTimes_6;

    @JsonProperty("recharge_max_amount_3m")
    private String rechargeMaxAmount_3;
    @JsonProperty("recharge_max_amount_6m")
    private String rechargeMaxAmount_6;

    @JsonProperty("bill_latest_certification_day")
    private String billLatestCertificationDay;

    @JsonProperty("web_fee_1m")
    private String webFee_1;
    @JsonProperty("web_fee_3m")
    private String webFee_3;
    @JsonProperty("web_fee_6m")
    private String webFee_6;
    @JsonProperty("web_fee_avg_3m")
    private String webFee_Avg_3;
    @JsonProperty("web_fee_avg_6m")
    private String webFee_Avg_6;

    @JsonProperty("sms_fee_1m")
    private String smsFee_1;
    @JsonProperty("sms_fee_3m")
    private String smsFee_3;
    @JsonProperty("sms_fee_6m")
    private String smsFee_6;
    @JsonProperty("sms_fee_avg_3m")
    private String smsFee_Avg_3;
    @JsonProperty("sms_fee_avg_6m")
    private String smsFee_Avg_6;

    @JsonProperty("call_fee_1m")
    private String voiceFee_1;
    @JsonProperty("call_fee_3m")
    private String voiceFee_3;
    @JsonProperty("call_fee_6m")
    private String voiceFee_6;
    @JsonProperty("call_fee_avg_3m")
    private String voiceFee_Avg_3;
    @JsonProperty("call_fee_avg_6m")
    private String voiceFee_Avg_6;

    @JsonProperty("vas_fee_1m")
    private String vasFee_1;
    @JsonProperty("vas_fee_3m")
    private String vasFee_3;
    @JsonProperty("vas_fee_6m")
    private String vasFee_6;
    @JsonProperty("vas_fee_avg_3m")
    private String vasFee_Avg_3;
    @JsonProperty("vas_fee_avg_6m")
    private String vasFee_Avg_6;
	public String getTotalFee_3() {
		return totalFee_3;
	}
	public void setTotalFee_3(String totalFee_3) {
		this.totalFee_3 = totalFee_3;
	}
	public String getTotalFee_6() {
		return totalFee_6;
	}
	public void setTotalFee_6(String totalFee_6) {
		this.totalFee_6 = totalFee_6;
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
	public String getRechargeMaxAmount_3() {
		return rechargeMaxAmount_3;
	}
	public void setRechargeMaxAmount_3(String rechargeMaxAmount_3) {
		this.rechargeMaxAmount_3 = rechargeMaxAmount_3;
	}
	public String getRechargeMaxAmount_6() {
		return rechargeMaxAmount_6;
	}
	public void setRechargeMaxAmount_6(String rechargeMaxAmount_6) {
		this.rechargeMaxAmount_6 = rechargeMaxAmount_6;
	}
	public String getBillLatestCertificationDay() {
		return billLatestCertificationDay;
	}
	public void setBillLatestCertificationDay(String billLatestCertificationDay) {
		this.billLatestCertificationDay = billLatestCertificationDay;
	}
	public String getWebFee_1() {
		return webFee_1;
	}
	public void setWebFee_1(String webFee_1) {
		this.webFee_1 = webFee_1;
	}
	public String getWebFee_3() {
		return webFee_3;
	}
	public void setWebFee_3(String webFee_3) {
		this.webFee_3 = webFee_3;
	}
	public String getWebFee_6() {
		return webFee_6;
	}
	public void setWebFee_6(String webFee_6) {
		this.webFee_6 = webFee_6;
	}
	public String getWebFee_Avg_3() {
		return webFee_Avg_3;
	}
	public void setWebFee_Avg_3(String webFee_Avg_3) {
		this.webFee_Avg_3 = webFee_Avg_3;
	}
	public String getWebFee_Avg_6() {
		return webFee_Avg_6;
	}
	public void setWebFee_Avg_6(String webFee_Avg_6) {
		this.webFee_Avg_6 = webFee_Avg_6;
	}
	public String getSmsFee_1() {
		return smsFee_1;
	}
	public void setSmsFee_1(String smsFee_1) {
		this.smsFee_1 = smsFee_1;
	}
	public String getSmsFee_3() {
		return smsFee_3;
	}
	public void setSmsFee_3(String smsFee_3) {
		this.smsFee_3 = smsFee_3;
	}
	public String getSmsFee_6() {
		return smsFee_6;
	}
	public void setSmsFee_6(String smsFee_6) {
		this.smsFee_6 = smsFee_6;
	}
	public String getSmsFee_Avg_3() {
		return smsFee_Avg_3;
	}
	public void setSmsFee_Avg_3(String smsFee_Avg_3) {
		this.smsFee_Avg_3 = smsFee_Avg_3;
	}
	public String getSmsFee_Avg_6() {
		return smsFee_Avg_6;
	}
	public void setSmsFee_Avg_6(String smsFee_Avg_6) {
		this.smsFee_Avg_6 = smsFee_Avg_6;
	}
	public String getVoiceFee_1() {
		return voiceFee_1;
	}
	public void setVoiceFee_1(String voiceFee_1) {
		this.voiceFee_1 = voiceFee_1;
	}
	public String getVoiceFee_3() {
		return voiceFee_3;
	}
	public void setVoiceFee_3(String voiceFee_3) {
		this.voiceFee_3 = voiceFee_3;
	}
	public String getVoiceFee_6() {
		return voiceFee_6;
	}
	public void setVoiceFee_6(String voiceFee_6) {
		this.voiceFee_6 = voiceFee_6;
	}
	public String getVoiceFee_Avg_3() {
		return voiceFee_Avg_3;
	}
	public void setVoiceFee_Avg_3(String voiceFee_Avg_3) {
		this.voiceFee_Avg_3 = voiceFee_Avg_3;
	}
	public String getVoiceFee_Avg_6() {
		return voiceFee_Avg_6;
	}
	public void setVoiceFee_Avg_6(String voiceFee_Avg_6) {
		this.voiceFee_Avg_6 = voiceFee_Avg_6;
	}
	public String getVasFee_1() {
		return vasFee_1;
	}
	public void setVasFee_1(String vasFee_1) {
		this.vasFee_1 = vasFee_1;
	}
	public String getVasFee_3() {
		return vasFee_3;
	}
	public void setVasFee_3(String vasFee_3) {
		this.vasFee_3 = vasFee_3;
	}
	public String getVasFee_6() {
		return vasFee_6;
	}
	public void setVasFee_6(String vasFee_6) {
		this.vasFee_6 = vasFee_6;
	}
	public String getVasFee_Avg_3() {
		return vasFee_Avg_3;
	}
	public void setVasFee_Avg_3(String vasFee_Avg_3) {
		this.vasFee_Avg_3 = vasFee_Avg_3;
	}
	public String getVasFee_Avg_6() {
		return vasFee_Avg_6;
	}
	public void setVasFee_Avg_6(String vasFee_Avg_6) {
		this.vasFee_Avg_6 = vasFee_Avg_6;
	}


}
