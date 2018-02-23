package com.hwc.framework;

import org.junit.Test;

/**
 * Created by jzl on 2018/1/3.
 */
public class JzlTest {

    @Test
    public void test01() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<10;i++) {
            sb.append("test");
            sb.append(",");
        }
        String str = sb.substring(0, sb.length() - 1).toString();
        System.out.print(str);
    }
}
