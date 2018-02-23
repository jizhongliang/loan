package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskAnalysis {

    @JsonProperty("in_time")
    private String inTime;
    @JsonProperty("mobile_silence_3m")
    private String mobileSilence_3;
    @JsonProperty("arrearage_risk_3m")
    private String arrearageRisk_3;
    @JsonProperty("binding_risk")
    private String bindingRisk;
    @JsonProperty("mobile_silence_6m")
    private String mobileSilence_6;
    @JsonProperty("arrearage_risk_6m")
    private String arrearageRisk_6;
    @JsonProperty("is_name_and_idcard_in_court_black")
    private String isNameAndIdcardInCourtBlack;
    @JsonProperty("is_name_and_idcard_in_finance_black")
    private String isNameAndIdcardInFinanceBlack;
    @JsonProperty("is_name_and_mobile_in_finance_black")
    private String isNameAndMobileInFinanceBlack;
    @JsonProperty("risk_check_item")
    private List<RiskCheckItem> riskCheckItem;
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getMobileSilence_3() {
		return mobileSilence_3;
	}
	public void setMobileSilence_3(String mobileSilence_3) {
		this.mobileSilence_3 = mobileSilence_3;
	}
	public String getArrearageRisk_3() {
		return arrearageRisk_3;
	}
	public void setArrearageRisk_3(String arrearageRisk_3) {
		this.arrearageRisk_3 = arrearageRisk_3;
	}
	public String getBindingRisk() {
		return bindingRisk;
	}
	public void setBindingRisk(String bindingRisk) {
		this.bindingRisk = bindingRisk;
	}
	public String getMobileSilence_6() {
		return mobileSilence_6;
	}
	public void setMobileSilence_6(String mobileSilence_6) {
		this.mobileSilence_6 = mobileSilence_6;
	}
	public String getArrearageRisk_6() {
		return arrearageRisk_6;
	}
	public void setArrearageRisk_6(String arrearageRisk_6) {
		this.arrearageRisk_6 = arrearageRisk_6;
	}
	public String getIsNameAndIdcardInCourtBlack() {
		return isNameAndIdcardInCourtBlack;
	}
	public void setIsNameAndIdcardInCourtBlack(String isNameAndIdcardInCourtBlack) {
		this.isNameAndIdcardInCourtBlack = isNameAndIdcardInCourtBlack;
	}
	public String getIsNameAndIdcardInFinanceBlack() {
		return isNameAndIdcardInFinanceBlack;
	}
	public void setIsNameAndIdcardInFinanceBlack(String isNameAndIdcardInFinanceBlack) {
		this.isNameAndIdcardInFinanceBlack = isNameAndIdcardInFinanceBlack;
	}
	public String getIsNameAndMobileInFinanceBlack() {
		return isNameAndMobileInFinanceBlack;
	}
	public void setIsNameAndMobileInFinanceBlack(String isNameAndMobileInFinanceBlack) {
		this.isNameAndMobileInFinanceBlack = isNameAndMobileInFinanceBlack;
	}
	public List<RiskCheckItem> getRiskCheckItem() {
		return riskCheckItem;
	}
	public void setRiskCheckItem(List<RiskCheckItem> riskCheckItem) {
		this.riskCheckItem = riskCheckItem;
	}

}
