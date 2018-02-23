package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zouwanli on 16/7/7.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryInformationDetail {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("report_no")
    private String reportNo;  // 报告编号
    @JsonProperty("query_dateTime")
    private String queryDateTime;  // 记录查询时间
    @JsonProperty("query_operator")
    private String queryOperator;  // 查询操作员
    @JsonProperty("query_reason")
    private String queryReason; // 查询原因(信用卡审批 , 贷后管理 , 贷款审批 ,本人查询）


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

    public String getQueryDateTime() {
        return queryDateTime;
    }

    public void setQueryDateTime(String queryDateTime) {
        this.queryDateTime = queryDateTime;
    }

    public String getQueryOperator() {
        return queryOperator;
    }

    public void setQueryOperator(String queryOperator) {
        this.queryOperator = queryOperator;
    }

    public String getQueryReason() {
        return queryReason;
    }

    public void setQueryReason(String queryReason) {
        this.queryReason = queryReason;
    }

}
