package com.hwc.framework.manage;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.service.ClBorrowService;
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
public class ClMortgageBorrowManageControllerTest {
    @Autowired
    private ClBorrowService borrowService;

    @Test
    public void borrrowList() {
        BorrowQueryRequest request = new BorrowQueryRequest();
        request.setState("40");
        request.setName("张宝");
        System.out.println(JSON.toJSONString(borrowService.getBorrowBeanList(request)));
    }
}
