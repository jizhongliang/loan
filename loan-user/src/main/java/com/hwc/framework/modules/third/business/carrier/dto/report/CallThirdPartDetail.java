package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by zhangliang on 16/11/23.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallThirdPartDetail {

    @JsonProperty("call_110_num_1m")
    private String numbersOf110_1;
    @JsonProperty("call_110_num_3m")
    private String numbersOf110_3;
    @JsonProperty("call_110_num_6m")
    private String numbersOf110_6;
    @JsonProperty("call_110_num_avg_3m")
    private String numbersOf110_avg_3;
    @JsonProperty("call_110_num_avg_6m")
    private String numbersOf110_avg_6;

    @JsonProperty("call_110_time_1m")
    private String timesOf110_1;
    @JsonProperty("call_110_time_3m")
    private String timesOf110_3;
    @JsonProperty("call_110_time_6m")
    private String timesOf110_6;
    @JsonProperty("call_110_time_avg_3m")
    private String timesOf110_avg_3;
    @JsonProperty("call_110_time_avg_6m")
    private String timesOf110_avg_6;

    @JsonProperty("call_120_num_1m")
    private String numbersOf120_1;
    @JsonProperty("call_120_num_3m")
    private String numbersOf120_3;
    @JsonProperty("call_120_num_6m")
    private String numbersOf120_6;
    @JsonProperty("call_120_num_avg_3m")
    private String numbersOf120_avg_3;
    @JsonProperty("call_120_num_avg_6m")
    private String numbersOf120_avg_6;

    @JsonProperty("call_120_time_1m")
    private String timesOf120_1;
    @JsonProperty("call_120_time_3m")
    private String timesOf120_3;
    @JsonProperty("call_120_time_6m")
    private String timesOf120_6;
    @JsonProperty("call_120_time_avg_3m")
    private String timesOf120_avg_3;
    @JsonProperty("call_120_time_avg_6m")
    private String timesOf120_avg_6;

    @JsonProperty("call_loan_num_1m")
    private String numbersOfLoanFirm_1;
    @JsonProperty("call_loan_num_3m")
    private String numbersOfLoanFirm_3;
    @JsonProperty("call_loan_num_6m")
    private String numbersOfLoanFirm_6;
    @JsonProperty("call_loan_num_avg_3m")
    private String numbersOfLoanFirm_avg_3;
    @JsonProperty("call_loan_num_avg_6m")
    private String numbersOfLoanFirm_avg_6;

    @JsonProperty("call_loan_time_1m")
    private String timesOfLoanFirm_1;
    @JsonProperty("call_loan_time_3m")
    private String timesOfLoanFirm_3;
    @JsonProperty("call_loan_time_6m")
    private String timesOfLoanFirm_6;
    @JsonProperty("call_loan_time_avg_3m")
    private String timesOfLoanFirm_avg_3;
    @JsonProperty("call_loan_time_avg_6m")
    private String timesOfLoanFirm_avg_6;

    @JsonProperty("call_credit_card_num_1m")
    private String numbersOfCreditCardCenter_1;
    @JsonProperty("call_credit_card_num_3m")
    private String numbersOfCreditCardCenter_3;
    @JsonProperty("call_credit_card_num_6m")
    private String numbersOfCreditCardCenter_6;
    @JsonProperty("call_credit_card_num_avg_3m")
    private String numbersOfCreditCardCenter_avg_3;
    @JsonProperty("call_credit_card_num_avg_6m")
    private String numbersOfCreditCardCenter_avg_6;

    @JsonProperty("call_credit_card_time_1m")
    private String timesOfCreditCardCenter_1;
    @JsonProperty("call_credit_card_time_3m")
    private String timesOfCreditCardCenter_3;
    @JsonProperty("call_credit_card_time_6m")
    private String timesOfCreditCardCenter_6;
    @JsonProperty("call_credit_card_time_avg_3m")
    private String timesOfCreditCardCenter_avg_3;
    @JsonProperty("call_credit_card_time_avg_6m")
    private String timesOfCreditCardCenter_avg_6;

    @JsonProperty("call_macon_num_1m")
    private String numbersOfMacon_1;
    @JsonProperty("call_macon_num_3m")
    private String numbersOfMacon_3;
    @JsonProperty("call_macon_num_6m")
    private String numbersOfMacon_6;
    @JsonProperty("call_macon_num_avg_3m")
    private String numbersOfMacon_avg_3;
    @JsonProperty("call_macon_num_avg_6m")
    private String numbersOfMacon_avg_6;

    @JsonProperty("call_macon_time_1m")
    private String timesOfMacon_1;
    @JsonProperty("call_macon_time_3m")
    private String timesOfMacon_3;
    @JsonProperty("call_macon_time_6m")
    private String timesOfMacon_6;
    @JsonProperty("call_macon_time_avg_3m")
    private String timesOfMacon_avg_3;
    @JsonProperty("call_macon_time_avg_6m")
    private String timesOfMacon_avg_6;

    @JsonProperty("call_collection_firm_num_1m")
    private String numbersOfCollectionFirm_1;
    @JsonProperty("call_collection_firm_num_3m")
    private String numbersOfCollectionFirm_3;
    @JsonProperty("call_collection_firm_num_6m")
    private String numbersOfCollectionFirm_6;
    @JsonProperty("call_collection_firm_num_avg_3m")
    private String numbersOfCollectionFirm_avg_3;
    @JsonProperty("call_collection_firm_num_avg_6m")
    private String numbersOfCollectionFirm_avg_6;

    @JsonProperty("call_collection_firm_time_1m")
    private String timesOfCollectionFirm_1;
    @JsonProperty("call_collection_firm_time_3m")
    private String timesOfCollectionFirm_3;
    @JsonProperty("call_collection_firm_time_6m")
    private String timesOfCollectionFirm_6;
    @JsonProperty("call_collection_firm_time_avg_3m")
    private String timesOfCollectionFirm_avg_3;
    @JsonProperty("call_collection_firm_time_avg_6m")
    private String timesOfCollectionFirm_avg_6;

    @JsonProperty("call_lawyer_num_1m")
    private String numbersOfLawyer_1;
    @JsonProperty("call_lawyer_num_3m")
    private String numbersOfLawyer_3;
    @JsonProperty("call_lawyer_num_6m")
    private String numbersOfLawyer_6;

    @JsonProperty("call_lawyer_time_1m")
    private String timesOfLawyer_1;
    @JsonProperty("call_lawyer_time_3m")
    private String timesOfLawyer_3;
    @JsonProperty("call_lawyer_time_6m")
    private String timesOfLawyer_6;

    @JsonProperty("call_bank_num_1m")
    private String numbersOfBank_1;
    @JsonProperty("call_bank_num_3m")
    private String numbersOfBank_3;
    @JsonProperty("call_bank_num_6m")
    private String numbersOfBank_6;

    @JsonProperty("call_bank_time_1m")
    private String timesOfBank_1;
    @JsonProperty("call_bank_time_3m")
    private String timesOfBank_3;
    @JsonProperty("call_bank_time_6m")
    private String timesOfBank_6;

    @JsonProperty("call_agency_num_1m")
    private String numbersOfAgency_1;
    @JsonProperty("call_agency_num_3m")
    private String numbersOfAgency_3;
    @JsonProperty("call_agency_num_6m")
    private String numbersOfAgency_6;

    @JsonProperty("call_agency_time_1m")
    private String timesOfAgency_1;
    @JsonProperty("call_agency_time_3m")
    private String timesOfAgency_3;
    @JsonProperty("call_agency_time_6m")
    private String timesOfAgency_6;

    @JsonProperty("call_fraud_num_1m")
    private String numbersOfFraud_1;
    @JsonProperty("call_fraud_num_3m")
    private String numbersOfFraud_3;
    @JsonProperty("call_fraud_num_6m")
    private String numbersOfFraud_6;

    @JsonProperty("call_fraud_time_1m")
    private String timesOfFraud_1;
    @JsonProperty("call_fraud_time_3m")
    private String timesOfFraud_3;
    @JsonProperty("call_fraud_time_6m")
    private String timesOfFraud_6;

    @JsonProperty("call_nuisance_num_1m")
    private String numbersOfNuisance_1;
    @JsonProperty("call_nuisance_num_3m")
    private String numbersOfNuisance_3;
    @JsonProperty("call_nuisance_num_6m")
    private String numbersOfNuisance_6;

    @JsonProperty("call_nuisance_time_1m")
    private String timesOfNuisance_1;
    @JsonProperty("call_nuisance_time_3m")
    private String timesOfNuisance_3;
    @JsonProperty("call_nuisance_time_6m")
    private String timesOfNuisance_6;

    @JsonProperty("call_railway_airway_num_1m")
    private String numbersOfRailwayAirway_1;
    @JsonProperty("call_railway_airway_num_3m")
    private String numbersOfRailwayAirway_3;
    @JsonProperty("call_railway_airway_num_6m")
    private String numbersOfRailwayAirway_6;

    @JsonProperty("call_railway_airway_time_1m")
    private String timesOfRailwayAirway_1;
    @JsonProperty("call_railway_airway_time_3m")
    private String timesOfRailwayAirway_3;
    @JsonProperty("call_railway_airway_time_6m")
    private String timesOfRailwayAirway_6;

    @JsonProperty("call_special_service_num_1m")
    private String numbersOfSpecialService_1;
    @JsonProperty("call_special_service_num_3m")
    private String numbersOfSpecialService_3;
    @JsonProperty("call_special_service_num_6m")
    private String numbersOfSpecialService_6;

    @JsonProperty("call_special_service_time_1m")
    private String timesOfSpecialService_1;
    @JsonProperty("call_special_service_time_3m")
    private String timesOfSpecialService_3;
    @JsonProperty("call_special_service_time_6m")
    private String timesOfSpecialService_6;

    @JsonProperty("call_express_num_1m")
    private String numbersOfExpress_1;
    @JsonProperty("call_express_num_3m")
    private String numbersOfExpress_3;
    @JsonProperty("call_express_num_6m")
    private String numbersOfExpress_6;

    @JsonProperty("call_express_time_1m")
    private String timesOfExpress_1;
    @JsonProperty("call_express_time_3m")
    private String timesOfExpress_3;
    @JsonProperty("call_express_time_6m")
    private String timesOfExpress_6;

    @JsonProperty("call_ins_fin_num_1m")
    private String numbersOfInsFin_1;
    @JsonProperty("call_ins_fin_num_3m")
    private String numbersOfInsFin_3;
    @JsonProperty("call_ins_fin_num_6m")
    private String numbersOfInsFin_6;

    @JsonProperty("call_ins_fin_time_1m")
    private String timesOfInsFin_1;
    @JsonProperty("call_ins_fin_time_3m")
    private String timesOfInsFin_3;
    @JsonProperty("call_ins_fin_time_6m")
    private String timesOfInsFin_6;

    @JsonProperty("call_car_firm_num_1m")
    private String numbersOfCarFirm_1;
    @JsonProperty("call_car_firm_num_3m")
    private String numbersOfCarFirm_3;
    @JsonProperty("call_car_firm_num_6m")
    private String numbersOfCarFirm_6;

    @JsonProperty("call_car_firm_time_1m")
    private String timesOfCarFirm_1;
    @JsonProperty("call_car_firm_time_3m")
    private String timesOfCarFirm_3;
    @JsonProperty("call_car_firm_time_6m")
    private String timesOfCarFirm_6;

    @JsonProperty("call_carrier_num_1m")
    private String numbersOfCarrier_1;
    @JsonProperty("call_carrier_num_3m")
    private String numbersOfCarrier_3;
    @JsonProperty("call_carrier_num_6m")
    private String numbersOfCarrier_6;

    @JsonProperty("call_carrier_time_1m")
    private String timesOfCarrier_1;
    @JsonProperty("call_carrier_time_3m")
    private String timesOfCarrier_3;
    @JsonProperty("call_carrier_time_6m")
    private String timesOfCarrier_6;

}
