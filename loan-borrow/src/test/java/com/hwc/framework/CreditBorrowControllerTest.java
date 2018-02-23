package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BorrowStateConstant;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.ClCreditBorrowService;
import org.apache.catalina.LifecycleState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by   on 2017/11/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditBorrowControllerTest {
    @Autowired
    private ClCreditBorrowService clBorrowService;
    @Autowired
    private ClBorrowService clBorrowService1;

    @Test
    public void getBorrowList() {
        List<ClBorrow> borrows = clBorrowService1.getBorrowLoanSuccess(7L);
        for (ClBorrow borrow : borrows) {
            System.out.println(borrow.getAmount());
        }
    }

    /**
     * 首页获取 信用借款情况
     *
     * @param
     * @return
     */
    @Test
    public void getIndex() {

        BorrowBean bean = clBorrowService.getIndex(7L);
        System.out.println(JSON.toJSONString(Response.success(bean)));

    }

    /**
     * 提借款申请
     *
     * @return
     */
    @Test
    public void borrowApply() {
        BorrowBean borrowBean = new BorrowBean();
        borrowBean.setUserId(7L);
        borrowBean.setPeriods(5);
        borrowBean.setAmount(1000D);

        Response response = clBorrowService.borrow(borrowBean);

        System.out.println(JSON.toJSONString(response));


    }


    @Test
    public void auditBorrow() {
        BorrowAuditBean bean = new BorrowAuditBean();
        bean.setId(416L);
        bean.setState(BorrowStateConstant.STATE_PASS);
        bean.setRemark("审核通过");
        Response response = clBorrowService.auditBorrow(bean, null);
        System.out.println(JSON.toJSONString(response));
    }
}
