package com.hwc.loan.sdk.core.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.core.response.SysConfigH5Response;

public class SysConfigH5Request extends RequestBase<SysConfigH5Response> {

    public static final String METHOD = "/api/sys/config/h5";

    private String packageName;
    private String type;

    public SysConfigH5Request() {
        super(METHOD);
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

} 