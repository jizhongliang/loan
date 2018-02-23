package com.hwc.framework.modules.third.business.carrier.dto.report.V3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hwc.framework.modules.third.business.carrier.dto.report.*;

import java.util.List;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportBasicV3 {

    private String code;

    private String message;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("user_basic_info")
    private UserBasicInfoV3 userBasicInfo;

    @JsonProperty("data_source")
    private DataSource dataSource;

    @JsonProperty("basic_info_check_items")
    private List<BasicInfoCheckItem> basicInfoCheckItems;

    @JsonProperty("friend_circle")
    private FriendCircle friendCircle;

    @JsonProperty("call_service_detail")
    private List<CallServiceDetail> callServiceDetail;

    @JsonProperty("active_degree_detail")
    private ActiveDegreeDetail activeDegreeDetail;

    @JsonProperty("consumption_detail")
    private ConsumptionDetail consumptionDetail;

    @JsonProperty("roam_detail")
    private List<RoamDetail> roamDetail;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public UserBasicInfoV3 getUserBasicInfo() {
		return userBasicInfo;
	}

	public void setUserBasicInfo(UserBasicInfoV3 userBasicInfo) {
		this.userBasicInfo = userBasicInfo;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<BasicInfoCheckItem> getBasicInfoCheckItems() {
		return basicInfoCheckItems;
	}

	public void setBasicInfoCheckItems(List<BasicInfoCheckItem> basicInfoCheckItems) {
		this.basicInfoCheckItems = basicInfoCheckItems;
	}

	public FriendCircle getFriendCircle() {
		return friendCircle;
	}

	public void setFriendCircle(FriendCircle friendCircle) {
		this.friendCircle = friendCircle;
	}

	public List<CallServiceDetail> getCallServiceDetail() {
		return callServiceDetail;
	}

	public void setCallServiceDetail(List<CallServiceDetail> callServiceDetail) {
		this.callServiceDetail = callServiceDetail;
	}

	public ActiveDegreeDetail getActiveDegreeDetail() {
		return activeDegreeDetail;
	}

	public void setActiveDegreeDetail(ActiveDegreeDetail activeDegreeDetail) {
		this.activeDegreeDetail = activeDegreeDetail;
	}

	public ConsumptionDetail getConsumptionDetail() {
		return consumptionDetail;
	}

	public void setConsumptionDetail(ConsumptionDetail consumptionDetail) {
		this.consumptionDetail = consumptionDetail;
	}

	public List<RoamDetail> getRoamDetail() {
		return roamDetail;
	}

	public void setRoamDetail(List<RoamDetail> roamDetail) {
		this.roamDetail = roamDetail;
	}

}
