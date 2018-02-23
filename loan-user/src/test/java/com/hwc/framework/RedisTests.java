package com.hwc.framework;

import com.hwc.common.aliyun.IHwcCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by  on 2017/10/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private IHwcCache hwcCache;

    @Test
    public void testCharge() {
        String value = "testtest1111";
        hwcCache.set("test", value);
        String s = hwcCache.get("test");
        Assert.assertEquals(value, s);
    }

}
