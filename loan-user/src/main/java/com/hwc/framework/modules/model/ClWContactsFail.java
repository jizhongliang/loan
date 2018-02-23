package com.hwc.framework.modules.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_w_contacts_fail")
public class ClWContactsFail {
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
     * 额度期限(月)
     */
    @Column(name = "dy_term")
    private BigDecimal dyTerm;

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
     * 预设利率
     */
    @Column(name = "borrow_rate")
    private BigDecimal borrowRate;

    /**
     * 额度期限
     */
    @Column(name = "quota_expire")
    private Integer quotaExpire;

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
     * 信用按揭额度
     */
    @Column(name = "credit_lines")
    private BigDecimal creditLines;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 提示手机号
     */
    @Column(name = "tips_phone")
    private String tipsPhone;

    /**
     * 风险等级
     */
    @Column(name = "risk_level")
    private Integer riskLevel;

    /**
     * 已还款利息
     */
    @Column(name = "old_repay_rate")
    private BigDecimal oldRepayRate;

    /**
     * 按揭贷款余额
     */
    @Column(name = "unuse_borrow_balance")
    private BigDecimal unuseBorrowBalance;

    /**
     * 按月还款额度
     */
    @Column(name = "month_repay_balance")
    private BigDecimal monthRepayBalance;

    /**
     * 原始额度（元）
     */
    @Column(name = "original_limit")
    private BigDecimal originalLimit;

    /**
     * 剩余额度（元）
     */
    @Column(name = "surplus_limit")
    private BigDecimal surplusLimit;

    /**
     * 是否有效   1有效 2无效-字段缺失 3无效-重复
     */
    @Column(name = "is_availability")
    private String isAvailability;

    /**
     * 合同号
     */
    @Column(name = "borrowId")
    private String borrowid;

    /**
     * 申报公司
     */
    @Column(name = "declaration_company")
    private String declarationCompany;

    /**
     * 申报人
     */
    @Column(name = "declaration_people")
    private String declarationPeople;

    /**
     * 抵押物权属证明
     */
    @Column(name = "mortgage_testify")
    private String mortgageTestify;

    /**
     * 城市区号
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 车位位置
     */
    @Column(name = "parking_position")
    private String parkingPosition;

    /**
 	 * 错误信息
 	 */
    @Column(name = "msg")
 	private String msg;
    
    
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
     * 获取额度期限(月)
     *
     * @return dy_term - 额度期限(月)
     */
    public BigDecimal getDyTerm() {
        return dyTerm;
    }

    /**
     * 设置额度期限(月)
     *
     * @param dyTerm 额度期限(月)
     */
    public void setDyTerm(BigDecimal dyTerm) {
        this.dyTerm = dyTerm;
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
     * 获取预设利率
     *
     * @return borrow_rate - 预设利率
     */
    public BigDecimal getBorrowRate() {
        return borrowRate;
    }

    /**
     * 设置预设利率
     *
     * @param borrowRate 预设利率
     */
    public void setBorrowRate(BigDecimal borrowRate) {
        this.borrowRate = borrowRate;
    }

    /**
     * 获取额度期限
     *
     * @return quota_expire - 额度期限
     */
    public Integer getQuotaExpire() {
        return quotaExpire;
    }

    /**
     * 设置额度期限
     *
     * @param quotaExpire 额度期限
     */
    public void setQuotaExpire(Integer quotaExpire) {
        this.quotaExpire = quotaExpire;
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

    /**
     * 获取信用按揭额度
     *
     * @return credit_lines - 信用按揭额度
     */
    public BigDecimal getCreditLines() {
        return creditLines;
    }

    /**
     * 设置信用按揭额度
     *
     * @param creditLines 信用按揭额度
     */
    public void setCreditLines(BigDecimal creditLines) {
        this.creditLines = creditLines;
    }

    /**
     * 获取职业
     *
     * @return vocation - 职业
     */
    public String getVocation() {
        return vocation;
    }

    /**
     * 设置职业
     *
     * @param vocation 职业
     */
    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    /**
     * 获取提示手机号
     *
     * @return tips_phone - 提示手机号
     */
    public String getTipsPhone() {
        return tipsPhone;
    }

    /**
     * 设置提示手机号
     *
     * @param tipsPhone 提示手机号
     */
    public void setTipsPhone(String tipsPhone) {
        this.tipsPhone = tipsPhone;
    }

    /**
     * 获取风险等级
     *
     * @return risk_level - 风险等级
     */
    public Integer getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置风险等级
     *
     * @param riskLevel 风险等级
     */
    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * 获取已还款利息
     *
     * @return old_repay_rate - 已还款利息
     */
    public BigDecimal getOldRepayRate() {
        return oldRepayRate;
    }

    /**
     * 设置已还款利息
     *
     * @param oldRepayRate 已还款利息
     */
    public void setOldRepayRate(BigDecimal oldRepayRate) {
        this.oldRepayRate = oldRepayRate;
    }

    /**
     * 获取按揭贷款余额
     *
     * @return unuse_borrow_balance - 按揭贷款余额
     */
    public BigDecimal getUnuseBorrowBalance() {
        return unuseBorrowBalance;
    }

    /**
     * 设置按揭贷款余额
     *
     * @param unuseBorrowBalance 按揭贷款余额
     */
    public void setUnuseBorrowBalance(BigDecimal unuseBorrowBalance) {
        this.unuseBorrowBalance = unuseBorrowBalance;
    }

    /**
     * 获取按月还款额度
     *
     * @return month_repay_balance - 按月还款额度
     */
    public BigDecimal getMonthRepayBalance() {
        return monthRepayBalance;
    }

    /**
     * 设置按月还款额度
     *
     * @param monthRepayBalance 按月还款额度
     */
    public void setMonthRepayBalance(BigDecimal monthRepayBalance) {
        this.monthRepayBalance = monthRepayBalance;
    }

    /**
     * 获取原始额度（元）
     *
     * @return original_limit - 原始额度（元）
     */
    public BigDecimal getOriginalLimit() {
        return originalLimit;
    }

    /**
     * 设置原始额度（元）
     *
     * @param originalLimit 原始额度（元）
     */
    public void setOriginalLimit(BigDecimal originalLimit) {
        this.originalLimit = originalLimit;
    }

    /**
     * 获取剩余额度（元）
     *
     * @return surplus_limit - 剩余额度（元）
     */
    public BigDecimal getSurplusLimit() {
        return surplusLimit;
    }

    /**
     * 设置剩余额度（元）
     *
     * @param surplusLimit 剩余额度（元）
     */
    public void setSurplusLimit(BigDecimal surplusLimit) {
        this.surplusLimit = surplusLimit;
    }

    /**
     * 获取是否有效   1有效 2无效-字段缺失 3无效-重复
     *
     * @return is_availability - 是否有效   1有效 2无效-字段缺失 3无效-重复
     */
    public String getIsAvailability() {
        return isAvailability;
    }

    /**
     * 设置是否有效   1有效 2无效-字段缺失 3无效-重复
     *
     * @param isAvailability 是否有效   1有效 2无效-字段缺失 3无效-重复
     */
    public void setIsAvailability(String isAvailability) {
        this.isAvailability = isAvailability;
    }

    /**
     * 获取合同号
     *
     * @return borrowId - 合同号
     */
    public String getBorrowid() {
        return borrowid;
    }

    /**
     * 设置合同号
     *
     * @param borrowid 合同号
     */
    public void setBorrowid(String borrowid) {
        this.borrowid = borrowid;
    }

    /**
     * 获取申报公司
     *
     * @return declaration_company - 申报公司
     */
    public String getDeclarationCompany() {
        return declarationCompany;
    }

    /**
     * 设置申报公司
     *
     * @param declarationCompany 申报公司
     */
    public void setDeclarationCompany(String declarationCompany) {
        this.declarationCompany = declarationCompany;
    }

    /**
     * 获取申报人
     *
     * @return declaration_people - 申报人
     */
    public String getDeclarationPeople() {
        return declarationPeople;
    }

    /**
     * 设置申报人
     *
     * @param declarationPeople 申报人
     */
    public void setDeclarationPeople(String declarationPeople) {
        this.declarationPeople = declarationPeople;
    }

    /**
     * 获取抵押物权属证明
     *
     * @return mortgage_testify - 抵押物权属证明
     */
    public String getMortgageTestify() {
        return mortgageTestify;
    }

    /**
     * 设置抵押物权属证明
     *
     * @param mortgageTestify 抵押物权属证明
     */
    public void setMortgageTestify(String mortgageTestify) {
        this.mortgageTestify = mortgageTestify;
    }

    /**
     * 获取城市区号
     *
     * @return city_code - 城市区号
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置城市区号
     *
     * @param cityCode 城市区号
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取车位位置
     *
     * @return parking_position - 车位位置
     */
    public String getParkingPosition() {
        return parkingPosition;
    }

    /**
     * 设置车位位置
     *
     * @param parkingPosition 车位位置
     */
    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition;
    }
}