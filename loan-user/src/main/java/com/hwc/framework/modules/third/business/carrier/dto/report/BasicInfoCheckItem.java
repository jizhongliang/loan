package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicInfoCheckItem {

    @JsonProperty("check_item")
    private String checkItem;
    private String result;

    public BasicInfoCheckItem() {
    }

    public BasicInfoCheckItem(String checkItem, String result) {
        this.checkItem = checkItem;
        this.result = result;
    }

	public String getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
