package com.hwc.framework.manage;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.common.ClMortgageOrderState;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.service.ClMortgageService;
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
public class ClMortgageManageControllerTest {
    @Autowired
    private ClMortgageService mortgageService;

    public void testTrial() {
        //mortgageService.trialAudit(ClMortgageOrderState.STATE_TRIAL, bean.getId(), bean.getRemark(), null);
    }

    @Test
    public void mortgageList() {
        BorrowQueryRequest rq = new BorrowQueryRequest();

        rq.setState(ClMortgageOrderState.STATE_PASS);
        System.out.println(JSON.toJSONString(mortgageService.getMortgageList(rq)));
    }

    @Test
    public void detail() {
        System.out.println(JSON.toJSONString(mortgageService.getMortgagBean(2L)));
    }
}
