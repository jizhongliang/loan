package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource {

    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("data_type")
    private String dataType;
    @JsonProperty("data_gain_time")
    private String dataGainTime;
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataGainTime() {
		return dataGainTime;
	}
	public void setDataGainTime(String dataGainTime) {
		this.dataGainTime = dataGainTime;
	}
    
    
}
