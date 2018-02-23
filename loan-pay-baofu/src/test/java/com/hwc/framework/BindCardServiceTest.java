package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.service.BindCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * Created by   on 2017/11/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BindCardServiceTest {
    @Autowired
    private BindCardService cardService;

    @Test
    public void bindCard() throws UnsupportedEncodingException {
        BindCardDomain b = new BindCardDomain();
        b.setBank_name("工商银行");
        b.setBank_code("01020000");
        b.setCard_no("6222020111122220000");
        b.setId_no("320301198502169142");
        b.setMobile("15355008306");
        b.setOrder_no(NidGenerator.getPayOrderNo());
        b.setUser_id(0L);
        b.setId_holder("张宝");
        Response response = cardService.preBindCard(b);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void confirmBind() throws Exception {
        BindCardDomain b = new BindCardDomain();
        b.setBank_name("工商银行");
        b.setCard_no("6222020111122220000");
        b.setId_no("320301198502169142");
        b.setMobile("15355008306");
        b.setOrder_no("0432062068");
        b.setUser_id(0L);
        b.setSms_code("999999");
        b.setId_holder("张宝");
        Response response = cardService.confirmBind(b);
        System.out.println(JSON.toJSONString(response));
    }
}
