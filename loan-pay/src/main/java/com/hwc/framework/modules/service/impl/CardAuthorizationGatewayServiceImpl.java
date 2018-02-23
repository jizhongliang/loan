package com.hwc.framework.modules.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.service.BindCardService;
import com.hwc.framework.modules.service.CardAuthorizationGatewayService;
import com.hwc.framework.modules.third.PayNotifyService;

/**
 * Created by   on 2017/11/10.
 */
@Service
public class CardAuthorizationGatewayServiceImpl implements CardAuthorizationGatewayService {
    Logger                   logger = LoggerFactory.getLogger(BaoFuChargeServiceImpl.class);
    @Autowired
    private BindCardService  cardService;

    @Autowired
    private PayNotifyService notifyService;

    public Response cardAuthorization(BindCardDomain domain) {
        try {
            Response<BankCardAuthoriztionRespBean> response = cardService.preBindCard(domain);

            return response;
        } catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    public Response confimCard(BindCardDomain domain) {
        try {
            Response<BankCardAuthoriztionRespBean> response = cardService.confirmBind(domain);
            if (response.getSuccess()) {
                BankCardAuthoriztionRespBean bean = response.getData();
                if ("O".equals(bean.getState())) {
                    notifyService.bankCardBindSuccess(bean);
                }
            }
            return response;

        } catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    /** 
     * 车位预绑卡
     */
    @Override
    public Response<BankCardAuthoriztionRespBean> cardAuthorizationCW(BindCardDomain domain) {
        try {
            Response<BankCardAuthoriztionRespBean> response = cardService.preBindCard(domain);
            return response;
        } catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }

    /** 
     * 车位确定绑卡
     */
    @Override
    public Response<BankCardAuthoriztionRespBean> confimCardCW(BindCardDomain domain) {
        try {
            Response<BankCardAuthoriztionRespBean> response = cardService.confirmBind(domain);
            if (response.getSuccess()) {
                BankCardAuthoriztionRespBean bean = response.getData();
                if ("O".equals(bean.getState())) {
                    notifyService.bankCardBindSuccess(bean);
                }
            }
            return response;

        } catch (Exception ex) {
            return Response.fail(ex.getMessage());
        }
    }
}
