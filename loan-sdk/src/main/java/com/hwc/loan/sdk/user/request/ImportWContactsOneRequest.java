package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.response.ImportWContactsOneResponse;

import java.math.BigDecimal;
import java.util.Date;





public class ImportWContactsOneRequest extends ItemsRequest<ImportWContactsOneResponse> {

    public static final String METHOD = "/api/user/wContacts/importWContactsOne";

    public ImportWContactsOneRequest() {
        super(METHOD);
    }

    /**
     * 主键
     */
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号码
     */
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
    private String isCredit;

    /**
     * T 抵押类白名单 F 非抵押类类白名单
     */
    private String isDy;

    /**
     * T 是否已经借款 F 未借款
     */
    private String isBorrow;

    /**
     * 个人收入
     */
    private BigDecimal personIncome;

    /**
     * 学历
     */
    private String education;

    /**
     * 家庭地址
     */
    private String liveAddr;

    /**
     * 所在小区
     */
    private String liveCommunity;

    /**
     * 抵押物价值
     */
    private BigDecimal dyValue;

    /**
     * 折扣率
     */
    private Long dyValueDiscount;

    /**
     * 抵押物所在城市
     */
    private String dyCity;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 单位类型
     */
    private String companyType;

    /**
     * 单位地址
     */
    private String companyAddr;

    /**
     * 最大可借款额度
     */
    private BigDecimal borrowQuota;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 导入时间
     */
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }

    public String getIsDy() {
        return isDy;
    }

    public void setIsDy(String isDy) {
        this.isDy = isDy;
    }

    public String getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }

    public BigDecimal getPersonIncome() {
        return personIncome;
    }

    public void setPersonIncome(BigDecimal personIncome) {
        this.personIncome = personIncome;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLiveAddr() {
        return liveAddr;
    }

    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    public String getLiveCommunity() {
        return liveCommunity;
    }

    public void setLiveCommunity(String liveCommunity) {
        this.liveCommunity = liveCommunity;
    }

    public BigDecimal getDyValue() {
        return dyValue;
    }

    public void setDyValue(BigDecimal dyValue) {
        this.dyValue = dyValue;
    }

    public Long getDyValueDiscount() {
        return dyValueDiscount;
    }

    public void setDyValueDiscount(Long dyValueDiscount) {
        this.dyValueDiscount = dyValueDiscount;
    }

    public String getDyCity() {
        return dyCity;
    }

    public void setDyCity(String dyCity) {
        this.dyCity = dyCity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public BigDecimal getBorrowQuota() {
        return borrowQuota;
    }

    public void setBorrowQuota(BigDecimal borrowQuota) {
        this.borrowQuota = borrowQuota;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
