package com.hwc.framework.modules.third.business.zhengxin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by zouwanli on 16/7/12.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditInformation {
    private CreditBaseInfo creditBaseInfo;
    private List<CreditRecordSummary> creditRecordSummaries;
    private List<CreditRecordDetail>  creditRecordDetails;
    private List<PublicInformationDetail> publicInformationDetails;
    private List<QueryInformationDetail>  queryInformationDetails;
    private List<CreditCardRecordDetailAnalyze> creditCardRecordDetailAnalyzes;
    private List<HousingLoanRecordDetailAnalyze> housingLoanRecordDetailAnalyzes;
    private List<OtherLoanRecordDetailAnalyze> otherLoanRecordDetailAnalyzes;

    public CreditBaseInfo getCreditBaseInfo() {
        return creditBaseInfo;
    }

    public void setCreditBaseInfo(CreditBaseInfo creditBaseInfo) {
        this.creditBaseInfo = creditBaseInfo;
    }

    public List<CreditRecordSummary> getCreditRecordSummaries() {
        return creditRecordSummaries;
    }

    public void setCreditRecordSummaries(List<CreditRecordSummary> creditRecordSummaries) {
        this.creditRecordSummaries = creditRecordSummaries;
    }

    public List<CreditRecordDetail> getCreditRecordDetails() {
        return creditRecordDetails;
    }

    public void setCreditRecordDetails(List<CreditRecordDetail> creditRecordDetails) {
        this.creditRecordDetails = creditRecordDetails;
    }

    public List<PublicInformationDetail> getPublicInformationDetails() {
        return publicInformationDetails;
    }

    public void setPublicInformationDetails(List<PublicInformationDetail> publicInformationDetails) {
        this.publicInformationDetails = publicInformationDetails;
    }

    public List<QueryInformationDetail> getQueryInformationDetails() {
        return queryInformationDetails;
    }

    public void setQueryInformationDetails(List<QueryInformationDetail> queryInformationDetails) {
        this.queryInformationDetails = queryInformationDetails;
    }

    public List<CreditCardRecordDetailAnalyze> getCreditCardRecordDetailAnalyzes() {
        return creditCardRecordDetailAnalyzes;
    }

    public void setCreditCardRecordDetailAnalyzes(List<CreditCardRecordDetailAnalyze> creditCardRecordDetailAnalyzes) {
        this.creditCardRecordDetailAnalyzes = creditCardRecordDetailAnalyzes;
    }

    public List<HousingLoanRecordDetailAnalyze> getHousingLoanRecordDetailAnalyzes() {
        return housingLoanRecordDetailAnalyzes;
    }

    public void setHousingLoanRecordDetailAnalyzes(List<HousingLoanRecordDetailAnalyze> housingLoanRecordDetailAnalyzes) {
        this.housingLoanRecordDetailAnalyzes = housingLoanRecordDetailAnalyzes;
    }

    public List<OtherLoanRecordDetailAnalyze> getOtherLoanRecordDetailAnalyzes() {
        return otherLoanRecordDetailAnalyzes;
    }

    public void setOtherLoanRecordDetailAnalyzes(List<OtherLoanRecordDetailAnalyze> otherLoanRecordDetailAnalyzes) {
        this.otherLoanRecordDetailAnalyzes = otherLoanRecordDetailAnalyzes;
    }
}
