package com.hwc.framework;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.service.ClBorrowRepayService;

/**
 * Created by   on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepayPlanControllerTest {

    @Autowired
    private ClBorrowRepayService clBorrowRepayService;

    @Test
    public void getTempRepayPlanList() {
        /*BorrowBean borrow = new BorrowBean();
        borrow.setAmount(10000D);
        borrow.setUserId(119L);
        Response response = clBorrowRepayService.getTempRepayPlanList(borrow);
        ResponsePage page = (ResponsePage) response.getData();
        for (Object o : page.getItems()) {
            System.out.println(JSON.toJSONString(o));
        
        }*/
    }

    @Test
    public void getRepayPlanList() {
        List<RepayPlanBean> ds = clBorrowRepayService.getRepayPlans(118L);
        System.out.println(JSON.toJSONString(ds));
    }

    @Test
    public void getTempRepayPlanListone() {
        /*BorrowBean borrow = new BorrowBean();
        borrow.setAmount(1000D);
        borrow.setUserId(22L);
        borrow.setRate(0.05D);
        borrow.setPeriods(6);
        Response response = clBorrowRepayService.genCreditRepayPlan(borrow);
        
        System.out.println(JSON.toJSONString(response));*/

    }

    @Test
    public void calcRepay() {
        BorrowBean bean = new BorrowBean();
        bean.setAmount(1000D);
        bean.setRate(0.05D);
        bean.setPeriods(6);
        System.out.println(JSON.toJSONString(clBorrowRepayService.calcFirstRepay(bean)));
    }

    @Test
    public void repment() {
        Response response = clBorrowRepayService.getPrepaymentAmount(476L);
        System.out.println(JSON.toJSONString(response));
    }
}
