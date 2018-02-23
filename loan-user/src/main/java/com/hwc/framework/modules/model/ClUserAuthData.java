package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_user_auth_data")
public class ClUserAuthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 运营商报表地址
     */
    @Column(name = "phone_report")
    private String phoneReport;

    /**
     * 运营商数据
     */
    @Column(name = "phone_data")
    private String phoneData;

    /**
     * 征信报告
     */
    @Column(name = "zhengxin_report")
    private String zhengxinReport;

    /**
     * 征信数据地址
     */
    @Column(name = "zhengxin_data")
    private String zhengxinData;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取运营商报表地址
     *
     * @return phone_report - 运营商报表地址
     */
    public String getPhoneReport() {
        return phoneReport;
    }

    /**
     * 设置运营商报表地址
     *
     * @param phoneReport 运营商报表地址
     */
    public void setPhoneReport(String phoneReport) {
        this.phoneReport = phoneReport;
    }

    /**
     * 获取运营商数据
     *
     * @return phone_data - 运营商数据
     */
    public String getPhoneData() {
        return phoneData;
    }

    /**
     * 设置运营商数据
     *
     * @param phoneData 运营商数据
     */
    public void setPhoneData(String phoneData) {
        this.phoneData = phoneData;
    }

    /**
     * 获取征信报告
     *
     * @return zhengxin_report - 征信报告
     */
    public String getZhengxinReport() {
        return zhengxinReport;
    }

    /**
     * 设置征信报告
     *
     * @param zhengxinReport 征信报告
     */
    public void setZhengxinReport(String zhengxinReport) {
        this.zhengxinReport = zhengxinReport;
    }

    /**
     * 获取征信数据地址
     *
     * @return zhengxin_data - 征信数据地址
     */
    public String getZhengxinData() {
        return zhengxinData;
    }

    /**
     * 设置征信数据地址
     *
     * @param zhengxinData 征信数据地址
     */
    public void setZhengxinData(String zhengxinData) {
        this.zhengxinData = zhengxinData;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}