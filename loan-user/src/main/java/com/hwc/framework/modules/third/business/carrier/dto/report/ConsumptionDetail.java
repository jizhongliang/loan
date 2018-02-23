package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumptionDetail {

    @JsonProperty("total_fee_1m")
    private String totalFee_1;
    @JsonProperty("total_fee_3m")
    private String totalFee_3;
    @JsonProperty("total_fee_6m")
    private String totalFee_6;
    @JsonProperty("total_fee_avg_3m")
    private String totalFee_avg_3;
    @JsonProperty("total_fee_avg_6m")
    private String totalFee_avg_6;

    @JsonProperty("web_fee_1m")
    private String webFee_1;
    @JsonProperty("web_fee_3m")
    private String webFee_3;
    @JsonProperty("web_fee_6m")
    private String webFee_6;
    @JsonProperty("web_fee_avg_3m")
    private String webFee_avg_3;
    @JsonProperty("web_fee_avg_6m")
    private String webFee_avg_6;

    @JsonProperty("call_fee_1m")
    private String voiceFee_1;
    @JsonProperty("call_fee_3m")
    private String voiceFee_3;
    @JsonProperty("call_fee_6m")
    private String voiceFee_6;
    @JsonProperty("call_fee_avg_3m")
    private String voiceFee_avg_3;
    @JsonProperty("call_fee_avg_6m")
    private String voiceFee_avg_6;

    @JsonProperty("sms_fee_1m")
    private String smsFee_1;
    @JsonProperty("sms_fee_3m")
    private String smsFee_3;
    @JsonProperty("sms_fee_6m")
    private String smsFee_6;
    @JsonProperty("sms_fee_avg_3m")
    private String smsFee_avg_3;
    @JsonProperty("sms_fee_avg_6m")
    private String smsFee_avg_6;

    @JsonProperty("vas_fee_1m")
    private String vasFee_1;
    @JsonProperty("vas_fee_3m")
    private String vasFee_3;
    @JsonProperty("vas_fee_6m")
    private String vasFee_6;
    @JsonProperty("vas_fee_avg_3m")
    private String vasFee_avg_3;
    @JsonProperty("vas_fee_avg_6m")
    private String vasFee_avg_6;

    @JsonProperty("extra_fee_1m")
    private String extraFee_1;
    @JsonProperty("extra_fee_3m")
    private String extraFee_3;
    @JsonProperty("extra_fee_6m")
    private String extraFee_6;
    @JsonProperty("extra_fee_avg_3m")
    private String extraFee_avg_3;
    @JsonProperty("extra_fee_avg_6m")
    private String extraFee_avg_6;

    @JsonProperty("recharge_amount_1m")
    private String rechargeAmount_1;
    @JsonProperty("recharge_amount_3m")
    private String rechargeAmount_3;
    @JsonProperty("recharge_amount_6m")
    private String rechargeAmount_6;
    @JsonProperty("recharge_amount_avg_3m")
    private String rechargeAmount_avg_3;
    @JsonProperty("recharge_amount_avg_6m")
    private String rechargeAmount_avg_6;
	public String getTotalFee_1() {
		return totalFee_1;
	}
	public void setTotalFee_1(String totalFee_1) {
		this.totalFee_1 = totalFee_1;
	}
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
	public String getTotalFee_avg_3() {
		return totalFee_avg_3;
	}
	public void setTotalFee_avg_3(String totalFee_avg_3) {
		this.totalFee_avg_3 = totalFee_avg_3;
	}
	public String getTotalFee_avg_6() {
		return totalFee_avg_6;
	}
	public void setTotalFee_avg_6(String totalFee_avg_6) {
		this.totalFee_avg_6 = totalFee_avg_6;
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
	public String getWebFee_avg_3() {
		return webFee_avg_3;
	}
	public void setWebFee_avg_3(String webFee_avg_3) {
		this.webFee_avg_3 = webFee_avg_3;
	}
	public String getWebFee_avg_6() {
		return webFee_avg_6;
	}
	public void setWebFee_avg_6(String webFee_avg_6) {
		this.webFee_avg_6 = webFee_avg_6;
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
	public String getVoiceFee_avg_3() {
		return voiceFee_avg_3;
	}
	public void setVoiceFee_avg_3(String voiceFee_avg_3) {
		this.voiceFee_avg_3 = voiceFee_avg_3;
	}
	public String getVoiceFee_avg_6() {
		return voiceFee_avg_6;
	}
	public void setVoiceFee_avg_6(String voiceFee_avg_6) {
		this.voiceFee_avg_6 = voiceFee_avg_6;
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
	public String getSmsFee_avg_3() {
		return smsFee_avg_3;
	}
	public void setSmsFee_avg_3(String smsFee_avg_3) {
		this.smsFee_avg_3 = smsFee_avg_3;
	}
	public String getSmsFee_avg_6() {
		return smsFee_avg_6;
	}
	public void setSmsFee_avg_6(String smsFee_avg_6) {
		this.smsFee_avg_6 = smsFee_avg_6;
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
	public String getVasFee_avg_3() {
		return vasFee_avg_3;
	}
	public void setVasFee_avg_3(String vasFee_avg_3) {
		this.vasFee_avg_3 = vasFee_avg_3;
	}
	public String getVasFee_avg_6() {
		return vasFee_avg_6;
	}
	public void setVasFee_avg_6(String vasFee_avg_6) {
		this.vasFee_avg_6 = vasFee_avg_6;
	}
	public String getExtraFee_1() {
		return extraFee_1;
	}
	public void setExtraFee_1(String extraFee_1) {
		this.extraFee_1 = extraFee_1;
	}
	public String getExtraFee_3() {
		return extraFee_3;
	}
	public void setExtraFee_3(String extraFee_3) {
		this.extraFee_3 = extraFee_3;
	}
	public String getExtraFee_6() {
		return extraFee_6;
	}
	public void setExtraFee_6(String extraFee_6) {
		this.extraFee_6 = extraFee_6;
	}
	public String getExtraFee_avg_3() {
		return extraFee_avg_3;
	}
	public void setExtraFee_avg_3(String extraFee_avg_3) {
		this.extraFee_avg_3 = extraFee_avg_3;
	}
	public String getExtraFee_avg_6() {
		return extraFee_avg_6;
	}
	public void setExtraFee_avg_6(String extraFee_avg_6) {
		this.extraFee_avg_6 = extraFee_avg_6;
	}
	public String getRechargeAmount_1() {
		return rechargeAmount_1;
	}
	public void setRechargeAmount_1(String rechargeAmount_1) {
		this.rechargeAmount_1 = rechargeAmount_1;
	}
	public String getRechargeAmount_3() {
		return rechargeAmount_3;
	}
	public void setRechargeAmount_3(String rechargeAmount_3) {
		this.rechargeAmount_3 = rechargeAmount_3;
	}
	public String getRechargeAmount_6() {
		return rechargeAmount_6;
	}
	public void setRechargeAmount_6(String rechargeAmount_6) {
		this.rechargeAmount_6 = rechargeAmount_6;
	}
	public String getRechargeAmount_avg_3() {
		return rechargeAmount_avg_3;
	}
	public void setRechargeAmount_avg_3(String rechargeAmount_avg_3) {
		this.rechargeAmount_avg_3 = rechargeAmount_avg_3;
	}
	public String getRechargeAmount_avg_6() {
		return rechargeAmount_avg_6;
	}
	public void setRechargeAmount_avg_6(String rechargeAmount_avg_6) {
		this.rechargeAmount_avg_6 = rechargeAmount_avg_6;
	}

}
