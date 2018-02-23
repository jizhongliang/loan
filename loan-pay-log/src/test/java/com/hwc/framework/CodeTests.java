package com.hwc.framework;

import com.hwc.mybatis.gen.v2.CodeTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeTests {


    @Autowired
    private CodeTemplate template;

    @Test
    public void testCharge() {
        template.genAll("cl_pay_req_log");
    }


}
