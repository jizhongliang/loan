package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * Created by   on 2017/11/23.
 */
@Data
public class DUserEquipment {

    private Long userId;
    /**
     * 操作系统
     */
    @ApiModelProperty(value = "手机操作系统",allowableValues = "IOS Android")
    private String operatingSystem;

    /**
     * 系统版本
     */
    @ApiModelProperty("系统版本")
    private String systemVersions;

    /**
     * 手机名
     */
    @ApiModelProperty("手机名")
    private String phoneName;

    /**
     * 手机型号
     */
    @ApiModelProperty("手机型号")
    private String phoneType;

    /**
     * 手机品牌
     */
    @ApiModelProperty(value = "手机品牌",allowableValues = "iphone xiao mi vivo oppo ...")
    private String phoneBrand;

    /**
     * 运营商
     */
    @ApiModelProperty("手机运营商")
    private String mobilecarrier;

    /**
     * 手机设备标识
     */
    @ApiModelProperty("手机设备标识 imei")
    private String phoneMark;

    /**
     * mac
     */
    @ApiModelProperty("mac地址")
    private String mac;

    /**
     * 屏幕分辨率
     */
    @ApiModelProperty("屏幕分辨率 1200*768")
    private String screen;

    /**
     * 应用版本号
     */
    @ApiModelProperty("应用版本号")
    private String versionName;

    /**
     * 应用build号
     */
    @ApiModelProperty("app 包名")
    private String versionCode;


}
