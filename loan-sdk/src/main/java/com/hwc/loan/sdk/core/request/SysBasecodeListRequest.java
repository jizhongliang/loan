package com.hwc.loan.sdk.core.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.core.response.SysBasecodeListResponse;

public class SysBasecodeListRequest extends RequestBase<SysBasecodeListResponse> {

    public static final String METHOD = "/api/sys/basecodeList";

    private String id;

    public SysBasecodeListRequest() {
        super(METHOD);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

} 