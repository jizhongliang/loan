package com.hwc.framework.manage;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
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
public class BorrowRepayLogManageControllerTest {
    @Autowired
    private ClBorrowRepayLogService repayLogService;

    @Test
    public void repaylogList() {
        RepayQueryRequest request = new RepayQueryRequest();
        request.setName("张宝");
        System.out.println(JSON.toJSONString(repayLogService.getPayLogList(request)));
    }

}
