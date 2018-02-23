package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zouwanli on 16/7/7.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditRecordDetail {
    @JsonProperty("mapping_id")
    private String mappingId;
    @JsonProperty("auto_id")
    private Long autoId;
    @JsonProperty("report_no")
    private String reportNo;  // 报告编号
    @JsonProperty("credit_type")
    private int creditType;  // 信贷类型（1.信用卡 2.住房贷款 3.其它贷款）
    @JsonProperty("account_type")
    private int accountType;  // 账户类型（1.发生过逾期的贷记卡账户 2.从未逾期过的贷记卡及透支未超过60天的准贷记卡账户 3.从未逾期过的账户 4.透支超过60天的准贷记卡账户 5.发生过逾期的账户）
    private String content; // 记录内容

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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getCreditType() {
        return creditType;
    }

    public void setCreditType(int creditType) {
        this.creditType = creditType;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

	public Long getAutoId() {
		return autoId;
	}

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
    
    
}
