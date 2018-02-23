package com.hwc.framework;

import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;
import com.hwc.framework.modules.service.BaoFuPayService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceTest {
    @Autowired
    private BaoFuPayService payService;

    @Test
    public void loanTest() throws Exception {
        PayDataDomain dataDomain = new PayDataDomain();
        dataDomain.setAmount(10000D);
        dataDomain.setOrder_no(NidGenerator.getPayOrderNo());
        dataDomain.setMobile("15355008306");
        dataDomain.setId_no("320301198502169142");
        dataDomain.setAgree_no("201711081457201000009902557");
        dataDomain.setBank_card_no("6222020111122220000");
        dataDomain.setBank_name("工商银行");
        dataDomain.setReal_name("张宝");
        dataDomain.setBorrow_id(1000L);
        dataDomain.setUser_id(1L);
        payService.loan(dataDomain);
    }

    @Test
    public void queryLoanTest() throws Exception {
        PayQueryDomain dataDomain = new PayQueryDomain();

        dataDomain.setOrder_no("0704471624");

        payService.queryLoan(dataDomain);
    }
}
