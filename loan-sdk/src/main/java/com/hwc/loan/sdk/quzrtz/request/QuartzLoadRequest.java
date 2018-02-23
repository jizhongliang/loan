package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.quzrtz.response.QuartzExecuteResponse;

public class QuartzLoadRequest extends RequestBase<QuartzExecuteResponse> {

    public static final String METHOD = "/api/p2p/manager/quartz/load";

    public QuartzLoadRequest() {
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
