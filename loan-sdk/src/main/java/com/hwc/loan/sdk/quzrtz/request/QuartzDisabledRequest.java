package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.quzrtz.response.QuartzDisabledResponse;

public class QuartzDisabledRequest extends RequestBase<QuartzDisabledResponse> {

    public static final String METHOD = "/api/p2p/manager/quartz/delete";

    public QuartzDisabledRequest() {
        super(METHOD);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
