package com.hwc.framework;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserEquipment;
import com.hwc.framework.modules.service.ClUserEquipmentInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by   on 2017/11/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClUserEquipmentInfoServiceTest {
    @Autowired
    private ClUserEquipmentInfoService equipmentInfoService;

    @Test
    public void register() {
        DUserEquipment rq = new DUserEquipment();
        rq.setSystemVersions("11.0.2");
        rq.setVersionName("1.0.0");
        rq.setUserId(2L);
        rq.setScreen("1900*2143");
        rq.setVersionCode("bao ming");
        rq.setMac("123213213213");
        rq.setPhoneBrand("iphone");
        rq.setMobilecarrier("中国移动");
        rq.setOperatingSystem("IOS");
        rq.setPhoneMark("123123213123");
        rq.setPhoneName("我的iphone");
        rq.setPhoneType("iphone 6");
        System.out.println(JSON.toJSONString(rq));
        Response response = equipmentInfoService.equipmentRegister(rq);
        System.out.println(JSON.toJSONString(response));
    }
}
