package com.hwc.framework.modules.third.impl;

import com.alibaba.fastjson.JSON;
import com.hwc.base.sdk.core.Client;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.framework.modules.third.BorrowService;
import com.hwc.loan.sdk.borrow.domain.BorrowRepayRepayPlanListDomain;
import com.hwc.loan.sdk.borrow.request.BorrowCreditRepayRepayPlanListRequest;
import com.hwc.loan.sdk.borrow.request.BorrowRepayRepayPlanListRequest;
import com.hwc.loan.sdk.borrow.response.BorrowCreditRepayRepayPlanListResponse;
import com.hwc.loan.sdk.borrow.response.BorrowRepayRepayPlanListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxk on 2017/12/26.
 */
@Component
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    Client borrowClient;
    @Autowired
    private IHwcCache cache;

    @Override
    public List<BorrowRepayRepayPlanListDomain> getRepayPlan(ContractDomian domian) {
        //
//         List<BorrowRepayRepayPlanListDomain> js= JSON.parseArray(cache.get("dddd"),BorrowRepayRepayPlanListDomain.class);
//         return  js;
        BorrowRepayRepayPlanListRequest request = new BorrowRepayRepayPlanListRequest();
        request.setUserId(domian.getUserId());
        request.setAmount(domian.getAmount());
        request.setRate(new BigDecimal(domian.getRate()).doubleValue());
        request.setPeriods(domian.getPeriods());
        BorrowRepayRepayPlanListResponse response = borrowClient.invoke(request);
        if (response.getSuccess()) {
            List<BorrowRepayRepayPlanListDomain> domains = response.getData().getItems();
            return domains;
        } else
            return new ArrayList<>();
    }

    @Override
    public BorrowRepayRepayPlanListDomain getCreditRepayPlan(ContractDomian domian) {
        BorrowCreditRepayRepayPlanListRequest request = new BorrowCreditRepayRepayPlanListRequest();
        request.setUserId(domian.getUserId());
        request.setAmount(domian.getAmount());
        request.setRate(new BigDecimal(domian.getRate()).doubleValue());
        request.setPeriods(domian.getPeriods());
        BorrowCreditRepayRepayPlanListResponse response = borrowClient.invoke(request);
        if (response.getSuccess()) {
            BorrowRepayRepayPlanListDomain domains = response.getData();
            return domains;
        } else{
            return new BorrowRepayRepayPlanListDomain();
        }
    }
}
