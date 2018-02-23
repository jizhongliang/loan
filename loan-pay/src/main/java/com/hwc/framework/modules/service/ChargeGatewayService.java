package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;

/**
 * Created by   on 2017/11/10.
 */
public interface ChargeGatewayService {
    public Response doCharge(ChargeDataDomain domain);

	public Response doChargeQuery(ChargeQueryDomain domain);
}
