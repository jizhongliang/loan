/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.quzrtz.response.QuartzLogListPageResponse;

/**
 * 
 * @author jinlilong
 * @version $Id: QuartzLogListPageRequest.java, v 0.1 2018年1月2日 上午10:09:29 jinlilong Exp $
 */
public class QuartzLogListPageRequest extends ItemsRequest<QuartzLogListPageResponse> {

    /**  */
    public static final String METHOD = "/api/p2p/manager/quartzLog/page";

    /**  */
    private Long               quartzId;

    /**
     * @param method
     */
    public QuartzLogListPageRequest() {
        super(METHOD);
    }

    /**
     * Getter method for property <tt>quartzId</tt>.
     * 
     * @return property value of quartzId
     */
    public Long getQuartzId() {
        return quartzId;
    }

    /**
     * Setter method for property <tt>quartzId</tt>.
     * 
     * @param quartzId value to be assigned to property quartzId
     */
    public void setQuartzId(Long quartzId) {
        this.quartzId = quartzId;
    }

}
