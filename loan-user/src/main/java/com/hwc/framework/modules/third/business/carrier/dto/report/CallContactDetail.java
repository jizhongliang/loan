package com.hwc.framework.modules.third.business.carrier.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangliang on 16/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallContactDetail {

    @JsonProperty("peer_num")
    private String peerNumber;

    private String city;

    @JsonProperty("call_num_1w")
    private String callNum1Week;

    @JsonProperty("call_num_1m")
    private String callNum1Month;

    @JsonProperty("call_num_3m")
    private String callNum3Month;

    @JsonProperty("call_num_6m")
    private String callNum6Month;

    @JsonProperty("call_time_3m")
    private String callTime3Month;

    @JsonProperty("call_time_6m")
    private String callTime6Month;

    @JsonProperty("dial_num_3m")
    private String dialNum3Month;

    @JsonProperty("dial_num_6m")
    private String dialNum6Month;

    @JsonProperty("dialed_num_3m")
    private String dialedNum3Month;

    @JsonProperty("dialed_num_6m")
    private String dialedNum6Month;

    @JsonProperty("call_num_morning_3m")
    private String callNumMorning3Month;

    @JsonProperty("call_num_morning_6m")
    private String callNumMorning6Month;

    @JsonProperty("call_num_noon_3m")
    private String callNumNoon3Month;

    @JsonProperty("call_num_noon_6m")
    private String callNumNoon6Month;

    @JsonProperty("call_num_afternoon_3m")
    private String callNumAfternoon3Month;

    @JsonProperty("call_num_afternoon_6m")
    private String callNumAfternoon6Month;

    @JsonProperty("call_num_evening_3m")
    private String callNumEvening3Month;

    @JsonProperty("call_num_evening_6m")
    private String callNumEvening6Month;

    @JsonProperty("call_num_night_3m")
    private String callNumNight3Month;

    @JsonProperty("call_num_night_6m")
    private String callNumNight6Month;

    @JsonProperty("call_num_weekday_3m")
    private String callNumWeekday3Month;

    @JsonProperty("call_num_weekday_6m")
    private String callNumWeekday6Month;

    @JsonProperty("call_num_weekend_3m")
    private String callNumWeekend3Month;

    @JsonProperty("call_num_weekend_6m")
    private String callNumWeekend6Month;

    @JsonProperty("call_num_holiday_3m")
    private String callNumHoliday3Month;

    @JsonProperty("call_num_holiday_6m")
    private String callNumHoliday6Month;

    @JsonProperty("call_if_whole_day_3m")
    private String callIfWholeDay3Month;

    @JsonProperty("call_if_whole_day_6m")
    private String callIfWholeDay6Month;

    public CallContactDetail() {}

    public CallContactDetail(String peerNumber) {
        this.peerNumber = peerNumber;
        this.city = "";
        this.callNum1Week = "0";
        this.callNum1Month = "0";
        this.callNum3Month = "0";
        this.callNum6Month = "0";
        this.callTime3Month = "0";
        this.callTime6Month = "0";
        this.dialNum3Month = "0";
        this.dialNum6Month = "0";
        this.dialedNum3Month = "0";
        this.dialedNum6Month = "0";
        this.callNumMorning3Month = "0";
        this.callNumMorning6Month = "0";
        this.callNumNoon3Month = "0";
        this.callNumNoon6Month = "0";
        this.callNumAfternoon3Month = "0";
        this.callNumAfternoon6Month = "0";
        this.callNumEvening3Month = "0";
        this.callNumEvening6Month = "0";
        this.callNumNight3Month = "0";
        this.callNumNight6Month = "0";
        this.callNumWeekday3Month = "0";
        this.callNumWeekday6Month = "0";
        this.callNumWeekend3Month = "0";
        this.callNumWeekend6Month = "0";
        this.callNumHoliday3Month = "0";
        this.callNumHoliday6Month = "0";
        this.callIfWholeDay3Month = "否";
        this. callIfWholeDay6Month = "否";

    }


}
