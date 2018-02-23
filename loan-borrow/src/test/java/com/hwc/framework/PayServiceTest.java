package com.hwc.framework;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceTest {

    @Autowired
    private PayService payService;

    @Test
    public void payQueryTest() {
        Response response= payService.queryPayMent("1055860773");
        System.out.println("");

    }
}
