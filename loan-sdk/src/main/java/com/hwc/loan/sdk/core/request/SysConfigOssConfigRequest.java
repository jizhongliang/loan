package com.hwc.loan.sdk.core.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.core.response.SysConfigOssConfigResponse;

public class SysConfigOssConfigRequest extends RequestBase<SysConfigOssConfigResponse> {

    public static final String METHOD = "/api/sys/config/ossConfig";

    private String id;

    public SysConfigOssConfigRequest() {
        super(METHOD);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

} 