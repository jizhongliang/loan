package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskCheckItem {

    private String item;
    @JsonProperty("call_num_3m")
    private String callNum3m;
    @JsonProperty("call_time_3m")
    private String callTime3m;
    @JsonProperty("call_num_6m")
    private String callNum6m;
    @JsonProperty("call_time_6m")
    private String callTime6m;

    public RiskCheckItem() {
    }

    public RiskCheckItem(String item, String callNum3m, String callTime3m, String callNum6m, String callTime6m) {
        this.item = item;
        this.callNum3m = callNum3m;
        this.callTime3m = callTime3m;
        this.callNum6m = callNum6m;
        this.callTime6m = callTime6m;
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCallNum3m() {
		return callNum3m;
	}

	public void setCallNum3m(String callNum3m) {
		this.callNum3m = callNum3m;
	}

	public String getCallTime3m() {
		return callTime3m;
	}

	public void setCallTime3m(String callTime3m) {
		this.callTime3m = callTime3m;
	}

	public String getCallNum6m() {
		return callNum6m;
	}

	public void setCallNum6m(String callNum6m) {
		this.callNum6m = callNum6m;
	}

	public String getCallTime6m() {
		return callTime6m;
	}

	public void setCallTime6m(String callTime6m) {
		this.callTime6m = callTime6m;
	}

}
