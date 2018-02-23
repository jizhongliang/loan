package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;


import com.hwc.framework.modules.service.BaoFuChargeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.resources.cldr.ar.CalendarData_ar_QA;

import java.util.Date;

/**
 * Created by   on 2017/11/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargeServiceTest {
    @Autowired
    private BaoFuChargeService chargeService;

    @Test
    public void doChargeTest() throws Exception {
        ChargeDataDomain dataDomain = new ChargeDataDomain();
        dataDomain.setTrans_serial_no(NidGenerator.getPayOrderNo());
        dataDomain.setAmount(100.00);
        dataDomain.setBank_name("工商银行");
        dataDomain.setBank_no("6222020111122220000");
        dataDomain.setAgree_no("201711081457201000009902557");
        dataDomain.setId_no("320301198502169142");
        dataDomain.setMobile("15355008306");
        dataDomain.setName("张宝");
        dataDomain.setOrder_no(NidGenerator.getPayOrderNo());
        dataDomain.setTrade_date(FsUtils.formatDateTime(new Date(), "yyyyMMddHHmmss"));
        chargeService.charge(dataDomain);
    }

    @Test
    public void chargeQuery() throws Exception {
        ChargeQueryDomain dataDomain = new ChargeQueryDomain();
        dataDomain.setTrans_serial_no("1459386519");
        dataDomain.setOrder_no(NidGenerator.getPayOrderNo());

//        dataDomain.setAmount(100.00);
//        dataDomain.setBank_name("工商银行");
//        dataDomain.setBank_no("6222020111122220000");
//        dataDomain.setAgree_no("201711081457201000009902557");
//        dataDomain.setId_no("320301198502169142");
//        dataDomain.setMobile("15355008306");
//        dataDomain.setName("张宝");
//        dataDomain.setOrder_no(NidGenerator.getPayOrderNo());
        dataDomain.setTrade_date(FsUtils.formatDateTime(new Date(), "yyyyMMddHHmmss"));
        chargeService.chargeQuery(dataDomain);
    }
}
