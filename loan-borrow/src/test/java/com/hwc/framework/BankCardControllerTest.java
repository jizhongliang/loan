package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.service.ClBankCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankCardControllerTest {
    @Autowired
    private ClBankCardService clBankCardService;

    @Test
    public void cardAuth() {
        BindCardDomain domain = new BindCardDomain();
        domain.setBank_code("03080000");
        domain.setBank_name("招商银行");
        domain.setId_holder("林秀科");
        domain.setMobile("13142775416");
        domain.setId_no("330327198211242376");
        domain.setCard_no("6214855713121545");
        domain.setOrder_no("0184054524");
        domain.setSms_code("123456");
        Response response = clBankCardService.authSignReturn(domain);
        System.out.println(JSON.toJSONString(response));
    }
}
