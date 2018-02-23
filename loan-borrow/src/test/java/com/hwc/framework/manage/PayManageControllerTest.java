package com.hwc.framework.manage;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.PayQueryRequest;
import com.hwc.framework.modules.service.ClPayLogService;
import com.hwc.framework.modules.service.PayService;
import org.apache.commons.math.stat.descriptive.moment.Skewness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayManageControllerTest {
    @Autowired
    private ClPayLogService payLogService;

    @Autowired
    private PayService payService;

    @Test
    public void payLogListTest() {
        PayQueryRequest request = new PayQueryRequest();
        request.setName("张宝");
        request.setBorrow_type(BorrowTypeConstant.CREDIT);
        Response response = payLogService.payLogList(request);
        System.out.println(JSON.toJSONString(response));
    }
}
