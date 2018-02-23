package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.domain.SysConfig;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCharge() {
//        DUserStateDomian dUserStateDomian = userService.userIsAuth(119L);
//        System.out.println(dUserStateDomian);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", System.currentTimeMillis());
        String mes = replaceMessage("fadsfadsf{date}{time}", jsonObject);
        System.out.println(mes);
    }

    private String replaceMessage(String message, JSONObject jsonObject) {
        for (String s : jsonObject.keySet()) {

            message = message.replaceAll("\\{" + s + "\\}", jsonObject.getString(s));
        }
        return message;
    }

}
