package com.hwc.framework;

import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.third.GeXinMessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by  on 2017/10/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GexinTests {

    @Autowired
    private GeXinMessageService geXinMessageService;

    @Test
    public void testCharge() {
        geXinMessageService.sendListMessager("测试", "测试", Arrays.asList("18238727757"));
    }

}
