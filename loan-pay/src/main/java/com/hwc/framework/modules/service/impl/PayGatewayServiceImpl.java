package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.druid.sql.dialect.oracle.ast.clause.ModelClause;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponsePage;
import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.service.BaoFuPayService;
import com.hwc.framework.modules.service.PayGatewayService;

import com.hwc.framework.modules.third.PayNotifyService;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by   on 2017/11/9.
 * 付款网关
 */
@Service
public class PayGatewayServiceImpl implements PayGatewayService {
    private static final Logger logger = LoggerFactory.getLogger(ClPayReqLogServiceImpl.class);
    @Autowired
    private BaoFuPayService payService;

    @Autowired
    private PayNotifyService notifyService;

    public Response doPay(PayDataDomain pay) {
        try {
            pay.setService_name("loan");
            Response<PayRespBean> response = payService.loan(pay);
            if (response.getSuccess()) {
                notifyService.paySuccess(response.getData());
            } else {
               // notifyService.payError(response.getMessage());
            }
            return response;
        } catch (Exception ex) {
            logger.error("付款错误", ex);
            return Response.fail(ex.getMessage());
        }
    }

    public Response doPayQuery(PayQueryDomain query) {
        try {
            query.setService_name("pay_query");
            return payService.queryLoan(query);
        } catch (Exception ex) {
            logger.error("doPayQuery error", ex);
            return Response.fail(ex.getMessage());
        }
    }

    @Override
    public Response payCallback(HttpServletRequest request) {

        Response response = payService.payCallback(request);
        if (response.getSuccess()) {
            try {
                ResponsePage page = (ResponsePage) response.getData();
                List<PayRespBean> list = new ArrayList<>();
                for (Object o : page.getItems()) {
                    list.add((PayRespBean) o);
                }
                notifyService.payCallback(list);
            } catch (Exception ex) {
                logger.error("pay callback notify error:", ex);
            }
        }

        return response;
    }
}
