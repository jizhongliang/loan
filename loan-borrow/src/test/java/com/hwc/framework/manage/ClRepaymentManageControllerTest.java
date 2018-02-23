package com.hwc.framework.manage;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.hwc.framework.common.BorrowTypeConstant;
import com.hwc.framework.modules.domain.RepayPlanBean;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.service.ChargeService;
import com.hwc.framework.modules.service.ClBorrowRepayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by   on 2017/11/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClRepaymentManageControllerTest {
    @Autowired
    private ClBorrowRepayService repayService;
    @Autowired
    private ChargeService chargeService;

    @Test
    public void repayList() {
        RepayQueryRequest request = new RepayQueryRequest();
        request.setBorrow_type(BorrowTypeConstant.CREDIT);
        //request.setState("40");
//        request.setOrderNo("01200588135");
//        request.setMobile("13093754385");
        request.setEnd(FsUtils.addDate(new Date(),100));
        System.out.println(JSON.toJSONString(repayService.repayList(request)
        ));
    }

    @Test
    public void repayDetail() {
        RepayPlanBean bean = repayService.getRepayPlan(389L);
        System.out.println(JSON.toJSONString(bean));
    }
}
