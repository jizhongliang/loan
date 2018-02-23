package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.WContactsUpdateOneResponse;

import java.math.BigDecimal;
import java.util.Date;


public class WContactsUpdateOneRequest extends RequestBase<WContactsUpdateOneResponse> {

    public static final String METHOD = "/mana/user/wContacts/updateOne";

    public WContactsUpdateOneRequest() {
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
     * 提示手机号码
     */
    private String tipsPhone;

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

    /**
     * 利率
     */
    private BigDecimal borrowRate;

    /**
     * 期限
     */
    private Integer quotaExpire;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 信用按揭额度
     */
    private BigDecimal creditLines;

    /**
     * 风险等级
     */
    private Integer riskLevel;

    /**
     * 按揭贷款余额
     */
    private BigDecimal unuseBorrowBalance;

    /**
     * 按揭已还款利息
     */
    private BigDecimal oldRepayRate;

    /**
     * 按月还款额
     */
    private BigDecimal monthRepayBalance;


    /**
     * 
     * 原始额度
     */
     private BigDecimal originalLimit;
     
 	/**
 	 * 
 	 * 剩余额度
 	 */
 	private BigDecimal surplusLimit;
 	
 	/**
 	 * 额度期限（月）
 	 */
 	private BigDecimal dyTerm;
 	
 	/**
 	 * 合同号
 	 */
 	private String borrowId;
 	
	/**
 	 * 申报人城市区号
 	 */
 	private String cityCode;
 	
 	/**
 	 * 申报公司
 	 */
 	private String declarationCompany;
 	
 	/**
 	 * 申报人
 	 */
 	private String declarationPeople;
 	
 	/**
 	 * 抵押物权属证明
 	 */
 	private String mortgageTestify;
 	
 	/**
 	 * 是否有效
 	 */
 	private String isAvailability;
 	
 	/**
 	 * 车位位置
 	 */
 	private String parkingPosition;
    
    
    public BigDecimal getOriginalLimit() {
		return originalLimit;
	}

	public void setOriginalLimit(BigDecimal originalLimit) {
		this.originalLimit = originalLimit;
	}

	public BigDecimal getSurplusLimit() {
		return surplusLimit;
	}

	public void setSurplusLimit(BigDecimal surplusLimit) {
		this.surplusLimit = surplusLimit;
	}

	public BigDecimal getDyTerm() {
		return dyTerm;
	}

	public void setDyTerm(BigDecimal dyTerm) {
		this.dyTerm = dyTerm;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDeclarationCompany() {
		return declarationCompany;
	}

	public void setDeclarationCompany(String declarationCompany) {
		this.declarationCompany = declarationCompany;
	}

	public String getDeclarationPeople() {
		return declarationPeople;
	}

	public void setDeclarationPeople(String declarationPeople) {
		this.declarationPeople = declarationPeople;
	}

	public String getMortgageTestify() {
		return mortgageTestify;
	}

	public void setMortgageTestify(String mortgageTestify) {
		this.mortgageTestify = mortgageTestify;
	}

	public String getIsAvailability() {
		return isAvailability;
	}

	public void setIsAvailability(String isAvailability) {
		this.isAvailability = isAvailability;
	}

	public String getParkingPosition() {
		return parkingPosition;
	}

	public void setParkingPosition(String parkingPosition) {
		this.parkingPosition = parkingPosition;
	}

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

    public String getTipsPhone() {
        return tipsPhone;
    }

    public void setTipsPhone(String tipsPhone) {
        this.tipsPhone = tipsPhone;
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

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public BigDecimal getCreditLines() {
        return creditLines;
    }

    public void setCreditLines(BigDecimal creditLines) {
        this.creditLines = creditLines;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getUnuseBorrowBalance() {
        return unuseBorrowBalance;
    }

    public void setUnuseBorrowBalance(BigDecimal unuseBorrowBalance) {
        this.unuseBorrowBalance = unuseBorrowBalance;
    }

    public BigDecimal getOldRepayRate() {
        return oldRepayRate;
    }

    public void setOldRepayRate(BigDecimal oldRepayRate) {
        this.oldRepayRate = oldRepayRate;
    }

    public BigDecimal getMonthRepayBalance() {
        return monthRepayBalance;
    }

    public void setMonthRepayBalance(BigDecimal monthRepayBalance) {
        this.monthRepayBalance = monthRepayBalance;
    }
}
