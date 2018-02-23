package com.hwc.framework;

import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.framework.modules.service.BestSignContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by  on 2017/12/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractTest {
    @Autowired
    private BestSignContractService signContractService;

    @Test
    public void getContract() {
        ContractDomian domian = new ContractDomian();
        domian.setUserId(22233L);
        domian.setId(100L);
        //domian.setRate(0.05D);
        domian.setScenes("旅游");
        domian.setAmount(10000D);
        domian.setPeriods(5);
        domian.setCreateTime(new Date());
        System.out.println(JSONObject.toJSONString(domian));
        //Response response = signContractService.createContract(domian);
       // System.out.println(response.getMessage());
    }
}
