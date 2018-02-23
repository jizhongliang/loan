package com.hwc.framework;

import com.hwc.base.sdk.gen.SDKGen;

public class SDKGenTest {

    public static void main(String[] args) {
        String searchPackage = "com.hwc.**.modules.controller.**";
        String sdkPackage = "com.hwc.loan.sdk.borrow";
        String dir = "C:\\HwcDev\\workspace\\loan\\loan-sdk\\src\\main\\java\\com\\hwc\\loan\\sdk\\borrow";
        SDKGen.gen(searchPackage, sdkPackage, dir);
    }

}
