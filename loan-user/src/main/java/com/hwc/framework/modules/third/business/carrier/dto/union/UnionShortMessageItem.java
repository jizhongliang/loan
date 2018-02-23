package com.hwc.framework.modules.third.business.carrier.dto.union;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yajun on 12/5/16.
 */
public class UnionShortMessageItem {

    @JsonProperty("details_id")
    private String detailsId;

    private String time;

    @JsonProperty("peer_number")
    private String peerNumber;

    private String location;

    @JsonProperty("send_type")
    private String sendType;

    @JsonProperty("msg_type")
    private String msgType;

    @JsonProperty("service_name")
    private String serviceName;

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

	public String getPeerNumber() {
		return peerNumber;
	}

	public void setPeerNumber(String peerNumber) {
		this.peerNumber = peerNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}
    
    
}
