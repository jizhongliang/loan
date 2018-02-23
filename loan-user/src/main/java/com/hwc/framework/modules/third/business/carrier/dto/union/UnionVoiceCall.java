package com.hwc.framework.modules.third.business.carrier.dto.union;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yajun on 12/5/16.
 */
public class UnionVoiceCall {

    @JsonProperty("bill_month")
    private String billMonth;

    @JsonProperty("total_size")
    private Integer totalSize;

    @JsonProperty("items")
    private List<UnionVoiceCallItem> voiceCallItems = new ArrayList<>();

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public List<UnionVoiceCallItem> getVoiceCallItems() {
		return voiceCallItems;
	}

	public void setVoiceCallItems(List<UnionVoiceCallItem> voiceCallItems) {
		this.voiceCallItems = voiceCallItems;
	}
    
    
}
