package com.hwc.framework.modules.third.business.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by zengdongping on 16/10/26.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class VoiceCallList {
    private String mobile;
    private List<MobileVoiceCallDetail> list ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<MobileVoiceCallDetail> getList() {
        return list;
    }

    public void setList(List<MobileVoiceCallDetail> list) {
        this.list = list;
    }
}
