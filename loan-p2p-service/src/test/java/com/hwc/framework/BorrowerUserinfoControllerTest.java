package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.BorrowerUserinfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowerUserinfoControllerTest {
    @Autowired
    private BorrowerUserinfoService borrowerUserinfoService;
    @Autowired
    private BorrowRepayService borrowRepayService;
    
    @Test
    public void getBorrowList() {
        System.out.println(JSON.toJSONString(borrowerUserinfoService.findBorrowerUserinfos()));
    }

    @Test
    public void synBorrowerUserinfo(){
        borrowerUserinfoService.synBorrowerUserinfo();
    }

    @Test
    public void queryBorrowerUserinfo() throws Exception {
        borrowerUserinfoService.queryBorrowerUserinfo("00138239336");
    }
    
    @Test
    public void clacOverdue() throws Exception {
    	borrowRepayService.clacOverdue();
    }
    
    @Test
    public void getSoonExpireRepayPlan() throws Exception {
    	borrowRepayService.getSoonExpireRepayPlan();
    }
    
}
