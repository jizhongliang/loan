package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.service.ClMortgageBorrowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MotgageBorrowControllerTest {
    @Autowired
    private ClMortgageBorrowService mortgageBorrowService;

    @Test
    public void index() {
        Response response = mortgageBorrowService.homePage(119L);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void borrow() {
        BorrowBean bean = new BorrowBean();
        bean.setAmount(11000D);
        bean.setPeriods(10);
        bean.setUserId(118L);
        mortgageBorrowService.borrow(bean);
    }

    @Test
    public void qu() {
        mortgageBorrowService.getQuotaRang(118L);
    }
}
