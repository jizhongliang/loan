package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

public class SysChannelAppListRequest extends ItemsRequest {

    private  String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
