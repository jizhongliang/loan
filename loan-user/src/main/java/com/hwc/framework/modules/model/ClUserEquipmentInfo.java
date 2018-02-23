package com.hwc.framework.modules.model;

import javax.persistence.*;

@Table(name = "cl_user_equipment_info")
public class ClUserEquipmentInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 操作系统
     */
    @Column(name = "operating_system")
    private String operatingSystem;

    /**
     * 系统版本
     */
    @Column(name = "system_versions")
    private String systemVersions;

    /**
     * 手机名
     */
    @Column(name = "phone_name")
    private String phoneName;

    /**
     * 手机型号
     */
    @Column(name = "phone_type")
    private String phoneType;

    /**
     * 手机品牌
     */
    @Column(name = "phone_brand")
    private String phoneBrand;

    /**
     * 运营商
     */
    @Column(name = "mobileCarrier")
    private String mobilecarrier;

    /**
     * 手机设备标识
     */
    @Column(name = "phone_mark")
    private String phoneMark;

    /**
     * mac
     */
    private String mac;

    /**
     * 屏幕分辨率
     */
    private String screen;

    /**
     * 应用版本号
     */
    @Column(name = "version_name")
    private String versionName;

    /**
     * 应用build号
     */
    @Column(name = "version_code")
    private String versionCode;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取uuid
     *
     * @return uuid - uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取操作系统
     *
     * @return operating_system - 操作系统
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * 设置操作系统
     *
     * @param operatingSystem 操作系统
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * 获取系统版本
     *
     * @return system_versions - 系统版本
     */
    public String getSystemVersions() {
        return systemVersions;
    }

    /**
     * 设置系统版本
     *
     * @param systemVersions 系统版本
     */
    public void setSystemVersions(String systemVersions) {
        this.systemVersions = systemVersions;
    }

    /**
     * 获取手机名
     *
     * @return phone_name - 手机名
     */
    public String getPhoneName() {
        return phoneName;
    }

    /**
     * 设置手机名
     *
     * @param phoneName 手机名
     */
    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    /**
     * 获取手机型号
     *
     * @return phone_type - 手机型号
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 设置手机型号
     *
     * @param phoneType 手机型号
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * 获取手机品牌
     *
     * @return phone_brand - 手机品牌
     */
    public String getPhoneBrand() {
        return phoneBrand;
    }

    /**
     * 设置手机品牌
     *
     * @param phoneBrand 手机品牌
     */
    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    /**
     * 获取运营商
     *
     * @return mobileCarrier - 运营商
     */
    public String getMobilecarrier() {
        return mobilecarrier;
    }

    /**
     * 设置运营商
     *
     * @param mobilecarrier 运营商
     */
    public void setMobilecarrier(String mobilecarrier) {
        this.mobilecarrier = mobilecarrier;
    }

    /**
     * 获取手机设备标识
     *
     * @return phone_mark - 手机设备标识
     */
    public String getPhoneMark() {
        return phoneMark;
    }

    /**
     * 设置手机设备标识
     *
     * @param phoneMark 手机设备标识
     */
    public void setPhoneMark(String phoneMark) {
        this.phoneMark = phoneMark;
    }

    /**
     * 获取mac
     *
     * @return mac - mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置mac
     *
     * @param mac mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取屏幕分辨率
     *
     * @return screen - 屏幕分辨率
     */
    public String getScreen() {
        return screen;
    }

    /**
     * 设置屏幕分辨率
     *
     * @param screen 屏幕分辨率
     */
    public void setScreen(String screen) {
        this.screen = screen;
    }

    /**
     * 获取应用版本号
     *
     * @return version_name - 应用版本号
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 设置应用版本号
     *
     * @param versionName 应用版本号
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * 获取应用build号
     *
     * @return version_code - 应用build号
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 设置应用build号
     *
     * @param versionCode 应用build号
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}