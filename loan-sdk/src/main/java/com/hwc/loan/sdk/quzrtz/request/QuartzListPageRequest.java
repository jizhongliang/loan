package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.quzrtz.response.QuartzListPageResponse;

public class QuartzListPageRequest extends ItemsRequest<QuartzListPageResponse> {

    public static final String METHOD = "/api/p2p/manager/quartz/page";

    public QuartzListPageRequest() {
        super(METHOD);
    }

}
