package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/23.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallFamilyDetail {

    @JsonProperty("is_family_net_member_1m")
    private String isFamilyNetMember_1;
    @JsonProperty("is_family_net_member_3m")
    private String isFamilyNetMember_3;
    @JsonProperty("is_family_net_member_6m")
    private String isFamilyNetMember_6;

    @JsonProperty("is_family_net_master_1m")
    private String isFamilyNetMaster_1;
    @JsonProperty("is_family_net_master_3m")
    private String isFamilyNetMaster_3;
    @JsonProperty("is_family_net_master_6m")
    private String isFamilyNetMaster_6;

    @JsonProperty("recharge_month_1m")
    private String rechargeMonths_1;
    @JsonProperty("recharge_month_3m")
    private String rechargeMonths_3;
    @JsonProperty("recharge_month_6m")
    private String rechargeMonths_6;

    @JsonProperty("is_address_match_phone_1m")
    private String isAddressMatchPhone_1;
    @JsonProperty("is_address_match_phone_3m")
    private String isAddressMatchPhone_3;
    @JsonProperty("is_address_match_phone_6m")
    private String isAddressMatchPhone_6;

    @JsonProperty("num_of_call_less_1m")
    private String numbersOfVoiceCallLess_1;
    @JsonProperty("num_of_call_less_3m")
    private String numbersOfVoiceCallLess_3;
    @JsonProperty("num_of_call_less_6m")
    private String numbersOfVoiceCallLess_6;

    @JsonProperty("num_of_call_more_1m")
    private String numbersOfVoiceCallMore_1;
    @JsonProperty("num_of_call_more_3m")
    private String numbersOfVoiceCallMore_3;
    @JsonProperty("num_of_call_more_6m")
    private String numbersOfVoiceCallMore_6;

    @JsonProperty("unpaid_month_1m")
    private String unpaidMonths_1;
    @JsonProperty("unpaid_month_3m")
    private String unpaidMonths_3;
    @JsonProperty("unpaid_month_6m")
    private String unpaidMonths_6;

    @JsonProperty("live_month_1m")
    private String liveMonths_1;
    @JsonProperty("live_month_3m")
    private String liveMonths_3;
    @JsonProperty("live_month_6m")
    private String liveMonths_6;
	public String getIsFamilyNetMember_1() {
		return isFamilyNetMember_1;
	}
	public void setIsFamilyNetMember_1(String isFamilyNetMember_1) {
		this.isFamilyNetMember_1 = isFamilyNetMember_1;
	}
	public String getIsFamilyNetMember_3() {
		return isFamilyNetMember_3;
	}
	public void setIsFamilyNetMember_3(String isFamilyNetMember_3) {
		this.isFamilyNetMember_3 = isFamilyNetMember_3;
	}
	public String getIsFamilyNetMember_6() {
		return isFamilyNetMember_6;
	}
	public void setIsFamilyNetMember_6(String isFamilyNetMember_6) {
		this.isFamilyNetMember_6 = isFamilyNetMember_6;
	}
	public String getIsFamilyNetMaster_1() {
		return isFamilyNetMaster_1;
	}
	public void setIsFamilyNetMaster_1(String isFamilyNetMaster_1) {
		this.isFamilyNetMaster_1 = isFamilyNetMaster_1;
	}
	public String getIsFamilyNetMaster_3() {
		return isFamilyNetMaster_3;
	}
	public void setIsFamilyNetMaster_3(String isFamilyNetMaster_3) {
		this.isFamilyNetMaster_3 = isFamilyNetMaster_3;
	}
	public String getIsFamilyNetMaster_6() {
		return isFamilyNetMaster_6;
	}
	public void setIsFamilyNetMaster_6(String isFamilyNetMaster_6) {
		this.isFamilyNetMaster_6 = isFamilyNetMaster_6;
	}
	public String getRechargeMonths_1() {
		return rechargeMonths_1;
	}
	public void setRechargeMonths_1(String rechargeMonths_1) {
		this.rechargeMonths_1 = rechargeMonths_1;
	}
	public String getRechargeMonths_3() {
		return rechargeMonths_3;
	}
	public void setRechargeMonths_3(String rechargeMonths_3) {
		this.rechargeMonths_3 = rechargeMonths_3;
	}
	public String getRechargeMonths_6() {
		return rechargeMonths_6;
	}
	public void setRechargeMonths_6(String rechargeMonths_6) {
		this.rechargeMonths_6 = rechargeMonths_6;
	}
	public String getIsAddressMatchPhone_1() {
		return isAddressMatchPhone_1;
	}
	public void setIsAddressMatchPhone_1(String isAddressMatchPhone_1) {
		this.isAddressMatchPhone_1 = isAddressMatchPhone_1;
	}
	public String getIsAddressMatchPhone_3() {
		return isAddressMatchPhone_3;
	}
	public void setIsAddressMatchPhone_3(String isAddressMatchPhone_3) {
		this.isAddressMatchPhone_3 = isAddressMatchPhone_3;
	}
	public String getIsAddressMatchPhone_6() {
		return isAddressMatchPhone_6;
	}
	public void setIsAddressMatchPhone_6(String isAddressMatchPhone_6) {
		this.isAddressMatchPhone_6 = isAddressMatchPhone_6;
	}
	public String getNumbersOfVoiceCallLess_1() {
		return numbersOfVoiceCallLess_1;
	}
	public void setNumbersOfVoiceCallLess_1(String numbersOfVoiceCallLess_1) {
		this.numbersOfVoiceCallLess_1 = numbersOfVoiceCallLess_1;
	}
	public String getNumbersOfVoiceCallLess_3() {
		return numbersOfVoiceCallLess_3;
	}
	public void setNumbersOfVoiceCallLess_3(String numbersOfVoiceCallLess_3) {
		this.numbersOfVoiceCallLess_3 = numbersOfVoiceCallLess_3;
	}
	public String getNumbersOfVoiceCallLess_6() {
		return numbersOfVoiceCallLess_6;
	}
	public void setNumbersOfVoiceCallLess_6(String numbersOfVoiceCallLess_6) {
		this.numbersOfVoiceCallLess_6 = numbersOfVoiceCallLess_6;
	}
	public String getNumbersOfVoiceCallMore_1() {
		return numbersOfVoiceCallMore_1;
	}
	public void setNumbersOfVoiceCallMore_1(String numbersOfVoiceCallMore_1) {
		this.numbersOfVoiceCallMore_1 = numbersOfVoiceCallMore_1;
	}
	public String getNumbersOfVoiceCallMore_3() {
		return numbersOfVoiceCallMore_3;
	}
	public void setNumbersOfVoiceCallMore_3(String numbersOfVoiceCallMore_3) {
		this.numbersOfVoiceCallMore_3 = numbersOfVoiceCallMore_3;
	}
	public String getNumbersOfVoiceCallMore_6() {
		return numbersOfVoiceCallMore_6;
	}
	public void setNumbersOfVoiceCallMore_6(String numbersOfVoiceCallMore_6) {
		this.numbersOfVoiceCallMore_6 = numbersOfVoiceCallMore_6;
	}
	public String getUnpaidMonths_1() {
		return unpaidMonths_1;
	}
	public void setUnpaidMonths_1(String unpaidMonths_1) {
		this.unpaidMonths_1 = unpaidMonths_1;
	}
	public String getUnpaidMonths_3() {
		return unpaidMonths_3;
	}
	public void setUnpaidMonths_3(String unpaidMonths_3) {
		this.unpaidMonths_3 = unpaidMonths_3;
	}
	public String getUnpaidMonths_6() {
		return unpaidMonths_6;
	}
	public void setUnpaidMonths_6(String unpaidMonths_6) {
		this.unpaidMonths_6 = unpaidMonths_6;
	}
	public String getLiveMonths_1() {
		return liveMonths_1;
	}
	public void setLiveMonths_1(String liveMonths_1) {
		this.liveMonths_1 = liveMonths_1;
	}
	public String getLiveMonths_3() {
		return liveMonths_3;
	}
	public void setLiveMonths_3(String liveMonths_3) {
		this.liveMonths_3 = liveMonths_3;
	}
	public String getLiveMonths_6() {
		return liveMonths_6;
	}
	public void setLiveMonths_6(String liveMonths_6) {
		this.liveMonths_6 = liveMonths_6;
	}

}
