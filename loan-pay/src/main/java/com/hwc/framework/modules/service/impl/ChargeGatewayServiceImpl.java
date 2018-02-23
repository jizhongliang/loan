package com.hwc.framework.modules.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.service.BaoFuChargeService;
import com.hwc.framework.modules.service.ChargeGatewayService;
import com.hwc.framework.modules.third.PayNotifyService;

/**
 * Created by   on 2017/11/10.
 */
@Service
public class ChargeGatewayServiceImpl implements ChargeGatewayService {
    Logger                     logger = LoggerFactory.getLogger(BaoFuChargeServiceImpl.class);
    @Autowired
    private BaoFuChargeService chargeService;
    @Autowired
    private PayNotifyService   notifyService;

    public Response doCharge(ChargeDataDomain domain) {
        try {

            logger.info("还款请求数据,borrowId=>" + domain.getBorrow_id() + ",data=>"
                        + JSONObject.toJSONString(domain));
            Response response = chargeService.charge(domain);
            logger.info("还款返回数据,borrowId=>" + domain.getBorrow_id() + ",data=>"
                        + JSONObject.toJSONString(response));
            if (response.getSuccess()) {
                ChargeRespBean bean = (ChargeRespBean) response.getData();
                if ("O".equals(bean.getState())) {
                    notifyService.chargeSuccess(bean);
                } else if ("F".equals(bean.getState())) {
                    notifyService.chargeError((ChargeRespBean) response.getData());
                }
            } else {
                // notifyService.chargeError((ChargeRespBean) response.getData());
                //
            }
            return response;
        } catch (Exception ex) {

            return Response.fail(ex.getMessage());
        }
    }

    public Response doChargeQuery(ChargeQueryDomain domain) {
        try {
            Response<ChargeRespBean> response = chargeService.chargeQuery(domain);
            if (response.getSuccess()) {
                ChargeRespBean bean = response.getData();
                if ("O".equals(bean.getState())) {
                    notifyService.chargeSuccess(bean);
                } else if ("F".equals(bean.getState())) {
                    notifyService.chargeError(response.getData());
                }
            } else {
                //notifyService.chargeError(response.getData());
                //
            }
            return response;
        } catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }
}
