package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by   on 2017/11/9.
 */
public interface PayGatewayService {
    public Response doPay(PayDataDomain pay);

    public Response doPayQuery(PayQueryDomain query);

    public Response payCallback(HttpServletRequest request);
}
