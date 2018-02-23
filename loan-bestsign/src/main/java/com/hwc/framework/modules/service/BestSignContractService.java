/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.alibaba.fastjson.JSONArray;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.BestSignContract;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/18
 */
public interface BestSignContractService extends Service<BestSignContract> {

    Response createContract(ContractDomian domian, JSONArray jsonArray);

    Response createContract(ContractDomian domian);

    Response finish(String contractId);

    Response createMortgageContract(ContractDomian domian);

    String getCreditContractsListUrl();
}
