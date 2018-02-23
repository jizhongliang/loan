package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendCircle {

    @JsonProperty("friend_num_3m")
    private String friendNum3m;
    @JsonProperty("good_friend_num_3m")
    private String goodFriendNum3m;
    @JsonProperty("friend_city_center_3m")
    private String friendCityCenter3m;
    @JsonProperty("is_city_match_friend_city_center_3m")
    private String isCityMatchFriendCityCenter3m;
    @JsonProperty("inter_peer_num_3m")
    private String interPeerNum3m;
    @JsonProperty("friend_num_6m")
    private String friendNum6m;
    @JsonProperty("good_friend_num_6m")
    private String goodFriendNum6m;
    @JsonProperty("friend_city_center_6m")
    private String friendCityCenter6m;
    @JsonProperty("is_city_match_friend_city_center_6m")
    private String isCityMatchFriendCityCenter6m;
    @JsonProperty("inter_peer_num_6m")
    private String interPeerNum6m;
    @JsonProperty("call_num_top3_3m")
    private List<CallNumTop> callNumTop3m;
    @JsonProperty("call_num_top3_6m")
    private List<CallNumTop> callNumTop6m;
    @JsonProperty("call_location_top3_3m")
    private List<CallLocationTop> callLocationTop3m;
    @JsonProperty("call_location_top3_6m")
    private List<CallLocationTop> callLocationTop6m;
    @JsonProperty("designated_contact")
    private List<CallContactDetail> designatedContact;
    @JsonProperty("risk_analysis")
    private RiskAnalysis riskAnalysis;
    @JsonProperty("active_degree")
    private ActiveDegree activeDegree;
    @JsonProperty("consumption_analysis")
    private ConsumptionAnalysis consumptionAnalysis;
    @JsonProperty("roam_analysis")
    private List<RoamAnalysis> roamAnalysis;
    @JsonProperty("call_analysis")
    private CallAnalysis callAnalysis;
    @JsonProperty("call_contact_detail")
    private List<CallContactDetail> callContactDetail;
    @JsonProperty("call_duration_detail_3m")
    private List<CallDurationDetail> callDurationDetail3m;
    @JsonProperty("call_duration_detail_6m")
    private List<CallDurationDetail> callDurationDetail6m;
    @JsonProperty("call_location_detail_3m")
    private List<CallLocationDetail> callLocationDetail3m;
    @JsonProperty("call_location_detail_6m")
    private List<CallLocationDetail> callLocationDetail6m;
    @JsonProperty("call_time_detail")
    private  CallTimeDetail callTimeDetail;
    @JsonProperty("call_third_part_detail")
    private  CallThirdPartDetail callThirdPartDetail;
    @JsonProperty("call_family_detail")
    private  CallFamilyDetail callFamilyDetail;
	public String getFriendNum3m() {
		return friendNum3m;
	}
	public void setFriendNum3m(String friendNum3m) {
		this.friendNum3m = friendNum3m;
	}
	public String getGoodFriendNum3m() {
		return goodFriendNum3m;
	}
	public void setGoodFriendNum3m(String goodFriendNum3m) {
		this.goodFriendNum3m = goodFriendNum3m;
	}
	public String getFriendCityCenter3m() {
		return friendCityCenter3m;
	}
	public void setFriendCityCenter3m(String friendCityCenter3m) {
		this.friendCityCenter3m = friendCityCenter3m;
	}
	public String getIsCityMatchFriendCityCenter3m() {
		return isCityMatchFriendCityCenter3m;
	}
	public void setIsCityMatchFriendCityCenter3m(String isCityMatchFriendCityCenter3m) {
		this.isCityMatchFriendCityCenter3m = isCityMatchFriendCityCenter3m;
	}
	public String getInterPeerNum3m() {
		return interPeerNum3m;
	}
	public void setInterPeerNum3m(String interPeerNum3m) {
		this.interPeerNum3m = interPeerNum3m;
	}
	public String getFriendNum6m() {
		return friendNum6m;
	}
	public void setFriendNum6m(String friendNum6m) {
		this.friendNum6m = friendNum6m;
	}
	public String getGoodFriendNum6m() {
		return goodFriendNum6m;
	}
	public void setGoodFriendNum6m(String goodFriendNum6m) {
		this.goodFriendNum6m = goodFriendNum6m;
	}
	public String getFriendCityCenter6m() {
		return friendCityCenter6m;
	}
	public void setFriendCityCenter6m(String friendCityCenter6m) {
		this.friendCityCenter6m = friendCityCenter6m;
	}
	public String getIsCityMatchFriendCityCenter6m() {
		return isCityMatchFriendCityCenter6m;
	}
	public void setIsCityMatchFriendCityCenter6m(String isCityMatchFriendCityCenter6m) {
		this.isCityMatchFriendCityCenter6m = isCityMatchFriendCityCenter6m;
	}
	public String getInterPeerNum6m() {
		return interPeerNum6m;
	}
	public void setInterPeerNum6m(String interPeerNum6m) {
		this.interPeerNum6m = interPeerNum6m;
	}
	public List<CallNumTop> getCallNumTop3m() {
		return callNumTop3m;
	}
	public void setCallNumTop3m(List<CallNumTop> callNumTop3m) {
		this.callNumTop3m = callNumTop3m;
	}
	public List<CallNumTop> getCallNumTop6m() {
		return callNumTop6m;
	}
	public void setCallNumTop6m(List<CallNumTop> callNumTop6m) {
		this.callNumTop6m = callNumTop6m;
	}
	public List<CallLocationTop> getCallLocationTop3m() {
		return callLocationTop3m;
	}
	public void setCallLocationTop3m(List<CallLocationTop> callLocationTop3m) {
		this.callLocationTop3m = callLocationTop3m;
	}
	public List<CallLocationTop> getCallLocationTop6m() {
		return callLocationTop6m;
	}
	public void setCallLocationTop6m(List<CallLocationTop> callLocationTop6m) {
		this.callLocationTop6m = callLocationTop6m;
	}
	public List<CallContactDetail> getDesignatedContact() {
		return designatedContact;
	}
	public void setDesignatedContact(List<CallContactDetail> designatedContact) {
		this.designatedContact = designatedContact;
	}
	public RiskAnalysis getRiskAnalysis() {
		return riskAnalysis;
	}
	public void setRiskAnalysis(RiskAnalysis riskAnalysis) {
		this.riskAnalysis = riskAnalysis;
	}
	public ActiveDegree getActiveDegree() {
		return activeDegree;
	}
	public void setActiveDegree(ActiveDegree activeDegree) {
		this.activeDegree = activeDegree;
	}
	public ConsumptionAnalysis getConsumptionAnalysis() {
		return consumptionAnalysis;
	}
	public void setConsumptionAnalysis(ConsumptionAnalysis consumptionAnalysis) {
		this.consumptionAnalysis = consumptionAnalysis;
	}
	public List<RoamAnalysis> getRoamAnalysis() {
		return roamAnalysis;
	}
	public void setRoamAnalysis(List<RoamAnalysis> roamAnalysis) {
		this.roamAnalysis = roamAnalysis;
	}
	public CallAnalysis getCallAnalysis() {
		return callAnalysis;
	}
	public void setCallAnalysis(CallAnalysis callAnalysis) {
		this.callAnalysis = callAnalysis;
	}
	public List<CallContactDetail> getCallContactDetail() {
		return callContactDetail;
	}
	public void setCallContactDetail(List<CallContactDetail> callContactDetail) {
		this.callContactDetail = callContactDetail;
	}
	public List<CallDurationDetail> getCallDurationDetail3m() {
		return callDurationDetail3m;
	}
	public void setCallDurationDetail3m(List<CallDurationDetail> callDurationDetail3m) {
		this.callDurationDetail3m = callDurationDetail3m;
	}
	public List<CallDurationDetail> getCallDurationDetail6m() {
		return callDurationDetail6m;
	}
	public void setCallDurationDetail6m(List<CallDurationDetail> callDurationDetail6m) {
		this.callDurationDetail6m = callDurationDetail6m;
	}
	public List<CallLocationDetail> getCallLocationDetail3m() {
		return callLocationDetail3m;
	}
	public void setCallLocationDetail3m(List<CallLocationDetail> callLocationDetail3m) {
		this.callLocationDetail3m = callLocationDetail3m;
	}
	public List<CallLocationDetail> getCallLocationDetail6m() {
		return callLocationDetail6m;
	}
	public void setCallLocationDetail6m(List<CallLocationDetail> callLocationDetail6m) {
		this.callLocationDetail6m = callLocationDetail6m;
	}
	public CallTimeDetail getCallTimeDetail() {
		return callTimeDetail;
	}
	public void setCallTimeDetail(CallTimeDetail callTimeDetail) {
		this.callTimeDetail = callTimeDetail;
	}
	public CallThirdPartDetail getCallThirdPartDetail() {
		return callThirdPartDetail;
	}
	public void setCallThirdPartDetail(CallThirdPartDetail callThirdPartDetail) {
		this.callThirdPartDetail = callThirdPartDetail;
	}
	public CallFamilyDetail getCallFamilyDetail() {
		return callFamilyDetail;
	}
	public void setCallFamilyDetail(CallFamilyDetail callFamilyDetail) {
		this.callFamilyDetail = callFamilyDetail;
	}

}
