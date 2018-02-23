package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageSysDelResponse;

public class ManageSysDelRequest extends RequestBase<ManageSysDelResponse> {

    public static final String METHOD = "/manage/sys/del";

    private Long id;

    public ManageSysDelRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 