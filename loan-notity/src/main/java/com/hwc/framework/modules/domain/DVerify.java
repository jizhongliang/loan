package com.hwc.framework.modules.domain;

import lombok.Data;

public class DVerify {

    private String imei;

    private String vImageCode;

    private String type;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getvImageCode() {
        return vImageCode;
    }

    public void setvImageCode(String vImageCode) {
        this.vImageCode = vImageCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
