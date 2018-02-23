package com.hwc.framework;

import com.hwc.base.sdk.gen.SDKGen;

public class SDKGenTest {

    public static void main(String[] args) {
        String searchPackage = "com.hwc.**.modules.controller.**.**";
        String sdkPackage = "com.hwc.loan.sdk.admin";
        String dir = "d:/aaa/";
        SDKGen.gen(searchPackage, sdkPackage, dir);
    }

}
