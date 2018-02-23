package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;

/**
 * Created by   on 2017/11/8.
 */
public interface BaoFuChargeService {
    Response charge(ChargeDataDomain domain) throws Exception;

    Response chargeQuery(ChargeQueryDomain domain) throws Exception;
}
