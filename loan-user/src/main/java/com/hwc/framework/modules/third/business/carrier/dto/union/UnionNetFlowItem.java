package com.hwc.framework.modules.third.business.carrier.dto.union;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yajun on 12/5/16.
 */
public class UnionNetFlowItem {

    @JsonProperty("details_id")
    private String detailsId;

    private String time;

    @JsonProperty("duration")
    private Integer durationInSecond;

    @JsonProperty("subflow")
    private Integer durationInFlow;

    @JsonProperty("net_type")
    private String netType;

    @JsonProperty("service_name")
    private String serviceName;

    private String location;

    private Integer fee;

	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getDurationInSecond() {
		return durationInSecond;
	}

	public void setDurationInSecond(Integer durationInSecond) {
		this.durationInSecond = durationInSecond;
	}

	public Integer getDurationInFlow() {
		return durationInFlow;
	}

	public void setDurationInFlow(Integer durationInFlow) {
		this.durationInFlow = durationInFlow;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}


}
