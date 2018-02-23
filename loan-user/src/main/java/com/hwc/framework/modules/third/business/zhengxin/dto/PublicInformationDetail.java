package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zouwanli on 16/7/7.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PublicInformationDetail {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("report_no")
    private String reportNo;  // 报告编号
    private int    type;   // 类型（1.欠税记录 2.民事判决记录 3.强制执行记录 4.行政处罚记录 5.电信欠费记录）
    private String content;  // 记录内容

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
