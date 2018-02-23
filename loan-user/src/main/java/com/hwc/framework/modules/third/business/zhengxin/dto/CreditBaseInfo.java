package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zouwanli on 16/7/7.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditBaseInfo {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("report_no")
    private String reportNo;   // 报告编号
    @JsonProperty("user_name")
    private String userName;  // 用户姓名
    @JsonProperty("idCard_type")
    private String idCardType;  // 证件类型
    @JsonProperty("idCard_no")
    private String idCardNo;  // 证件号码
    @JsonProperty("query_time")
    private String queryTime;  // 查询时间
    @JsonProperty("report_time")
    private String reportTime;  // 报告时间
    @JsonProperty("marital_status")
    private String maritalStatus;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


}
