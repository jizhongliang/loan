package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_w_contacts")
public class ClWContacts {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号码
     */
    @Column(name = "id_no")
    private String idNo;

    /**
     * 身份证号码
     */
    private String name;

    /**
     * 来源
     */
    private String src;

    private String state;

    /**
     * T 信用类白名单 F 非信用类白名单
     */
    @Column(name = "is_credit")
    private String isCredit;

    /**
     * T 抵押类白名单 F 非抵押类类白名单
     */
    @Column(name = "is_dy")
    private String isDy;

    /**
     * T 是否已经借款 F 未借款
     */
    @Column(name = "is_borrow")
    private String isBorrow;

    /**
     * 个人收入
     */
    @Column(name = "person_income")
    private BigDecimal personIncome;

    /**
     * 学历
     */
    private String education;

    /**
     * 家庭地址
     */
    @Column(name = "live_addr")
    private String liveAddr;

    /**
     * 所在小区
     */
    @Column(name = "live_community")
    private String liveCommunity;

    /**
     * 抵押物价值
     */
    @Column(name = "dy_value")
    private BigDecimal dyValue;

    /**
     * 折扣率
     */
    @Column(name = "dy_value_discount")
    private Long dyValueDiscount;

    /**
     * 抵押物所在城市
     */
    @Column(name = "dy_city")
    private String dyCity;

    /**
     * 单位名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 单位类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 单位地址
     */
    @Column(name = "company_addr")
    private String companyAddr;

    /**
     * 最大可借款额度
     */
    @Column(name = "borrow_quota")
    private BigDecimal borrowQuota;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 导入时间
     */
    private Date created;

    /**
     * 利率
     */
    @Column(name = "borrow_rate")
    private BigDecimal borrowRate;

    /**
     * 期限
     */
    @Column(name = "quota_expire")
    private Integer quotaExpire;

    public BigDecimal getBorrowRate() {
        return borrowRate;
    }

    public void setBorrowRate(BigDecimal borrowRate) {
        this.borrowRate = borrowRate;
    }

    public Integer getQuotaExpire() {
        return quotaExpire;
    }

    public void setQuotaExpire(Integer quotaExpire) {
        this.quotaExpire = quotaExpire;
    }

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
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取身份证号码
     *
     * @return id_no - 身份证号码
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置身份证号码
     *
     * @param idNo 身份证号码
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取身份证号码
     *
     * @return name - 身份证号码
     */
    public String getName() {
        return name;
    }

    /**
     * 设置身份证号码
     *
     * @param name 身份证号码
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取来源
     *
     * @return src - 来源
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置来源
     *
     * @param src 来源
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取T 信用类白名单 F 非信用类白名单
     *
     * @return is_credit - T 信用类白名单 F 非信用类白名单
     */
    public String getIsCredit() {
        return isCredit;
    }

    /**
     * 设置T 信用类白名单 F 非信用类白名单
     *
     * @param isCredit T 信用类白名单 F 非信用类白名单
     */
    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }

    /**
     * 获取T 抵押类白名单 F 非抵押类类白名单
     *
     * @return is_dy - T 抵押类白名单 F 非抵押类类白名单
     */
    public String getIsDy() {
        return isDy;
    }

    /**
     * 设置T 抵押类白名单 F 非抵押类类白名单
     *
     * @param isDy T 抵押类白名单 F 非抵押类类白名单
     */
    public void setIsDy(String isDy) {
        this.isDy = isDy;
    }

    /**
     * 获取T 是否已经借款 F 未借款
     *
     * @return is_borrow - T 是否已经借款 F 未借款
     */
    public String getIsBorrow() {
        return isBorrow;
    }

    /**
     * 设置T 是否已经借款 F 未借款
     *
     * @param isBorrow T 是否已经借款 F 未借款
     */
    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }

    /**
     * 获取个人收入
     *
     * @return person_income - 个人收入
     */
    public BigDecimal getPersonIncome() {
        return personIncome;
    }

    /**
     * 设置个人收入
     *
     * @param personIncome 个人收入
     */
    public void setPersonIncome(BigDecimal personIncome) {
        this.personIncome = personIncome;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取家庭地址
     *
     * @return live_addr - 家庭地址
     */
    public String getLiveAddr() {
        return liveAddr;
    }

    /**
     * 设置家庭地址
     *
     * @param liveAddr 家庭地址
     */
    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    /**
     * 获取所在小区
     *
     * @return live_community - 所在小区
     */
    public String getLiveCommunity() {
        return liveCommunity;
    }

    /**
     * 设置所在小区
     *
     * @param liveCommunity 所在小区
     */
    public void setLiveCommunity(String liveCommunity) {
        this.liveCommunity = liveCommunity;
    }

    /**
     * 获取抵押物价值
     *
     * @return dy_value - 抵押物价值
     */
    public BigDecimal getDyValue() {
        return dyValue;
    }

    /**
     * 设置抵押物价值
     *
     * @param dyValue 抵押物价值
     */
    public void setDyValue(BigDecimal dyValue) {
        this.dyValue = dyValue;
    }

    /**
     * 获取折扣率
     *
     * @return dy_value_discount - 折扣率
     */
    public Long getDyValueDiscount() {
        return dyValueDiscount;
    }

    /**
     * 设置折扣率
     *
     * @param dyValueDiscount 折扣率
     */
    public void setDyValueDiscount(Long dyValueDiscount) {
        this.dyValueDiscount = dyValueDiscount;
    }

    /**
     * 获取抵押物所在城市
     *
     * @return dy_city - 抵押物所在城市
     */
    public String getDyCity() {
        return dyCity;
    }

    /**
     * 设置抵押物所在城市
     *
     * @param dyCity 抵押物所在城市
     */
    public void setDyCity(String dyCity) {
        this.dyCity = dyCity;
    }

    /**
     * 获取单位名称
     *
     * @return company_name - 单位名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置单位名称
     *
     * @param companyName 单位名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取单位类型
     *
     * @return company_type - 单位类型
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置单位类型
     *
     * @param companyType 单位类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取单位地址
     *
     * @return company_addr - 单位地址
     */
    public String getCompanyAddr() {
        return companyAddr;
    }

    /**
     * 设置单位地址
     *
     * @param companyAddr 单位地址
     */
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    /**
     * 获取最大可借款额度
     *
     * @return borrow_quota - 最大可借款额度
     */
    public BigDecimal getBorrowQuota() {
        return borrowQuota;
    }

    /**
     * 设置最大可借款额度
     *
     * @param borrowQuota 最大可借款额度
     */
    public void setBorrowQuota(BigDecimal borrowQuota) {
        this.borrowQuota = borrowQuota;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取导入时间
     *
     * @return created - 导入时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置导入时间
     *
     * @param created 导入时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}