package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DebitBean;
import com.hwc.framework.modules.domain.EarlyRepayBean;
import com.hwc.framework.modules.service.ChargeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargeServiceTest {
    @Autowired
    private ChargeService chargeService;

    @Test
    public void repaymentTest() {
        DebitBean bean = new DebitBean();
        bean.setRepayId(254L);
        Response response = chargeService.repayment(bean);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void preRepaymentTest() {
        EarlyRepayBean bean = new EarlyRepayBean();
        bean.setBorrowId(339L);
        Response response = chargeService.preRepayment(bean);
        System.out.println(JSON.toJSONString(response));
    }
}
