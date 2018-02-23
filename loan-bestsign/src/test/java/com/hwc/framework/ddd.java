package com.hwc.framework;

import com.hwc.mybatis.gen.v2.CodeTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by  on 2017/12/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ddd {


    @Autowired
    private CodeTemplate template;

    @Test
    public void testCharge() {
        template.genAll("best_sign_contract", "best_sign_users");
    }


}
