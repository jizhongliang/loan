package com.hwc.framework.modules.model;

import java.math.BigDecimal;
import java.util.Date;

public class CLWContacts {
    private Long id;

    private String phone;

    private String idNo;

    private String name;

    private String src;

    private String state;

    private String isCredit;

    private String isDy;

    private String isBorrow;

    private BigDecimal personIncome;

    private String education;

    private String liveAddr;

    private String liveCommunity;

    private BigDecimal dyValue;

    private BigDecimal dyValueDiscount;

    private Integer dyTerm;

    private String dyCity;

    private String companyName;

    private String companyType;

    private String companyAddr;

    private BigDecimal borrowRate;
    private BigDecimal originalLimit;
    private BigDecimal surplusLimit;

    private Integer quotaExpire;

    private BigDecimal borrowQuota;

    private Date updated;

    private Date created;

    private BigDecimal creditLines;

    private String vocation;
    private String declarationCompany;
    private String declarationPeople;
    private String parkingPosition;

    private String tipsPhone;
    private String isAvailability;
    private String borrowId;
    private String cityCode;

    private Integer riskLevel;

    private BigDecimal oldRepayRate;

    private BigDecimal unuseBorrowBalance;

    private BigDecimal monthRepayBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getParkingPosition() {
		return parkingPosition;
	}

	public void setParkingPosition(String parkingPosition) {
		this.parkingPosition = parkingPosition;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

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

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIsAvailability() {
		return isAvailability;
	}

	public void setIsAvailability(String isAvailability) {
		this.isAvailability = isAvailability;
	}

	public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit == null ? null : isCredit.trim();
    }

    public String getIsDy() {
        return isDy;
    }

    public void setIsDy(String isDy) {
        this.isDy = isDy == null ? null : isDy.trim();
    }

    public String getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow == null ? null : isBorrow.trim();
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
        this.education = education == null ? null : education.trim();
    }

    public String getLiveAddr() {
        return liveAddr;
    }

    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr == null ? null : liveAddr.trim();
    }

    public String getLiveCommunity() {
        return liveCommunity;
    }

    public void setLiveCommunity(String liveCommunity) {
        this.liveCommunity = liveCommunity == null ? null : liveCommunity.trim();
    }

    public BigDecimal getDyValue() {
        return dyValue;
    }

    public void setDyValue(BigDecimal dyValue) {
        this.dyValue = dyValue;
    }

    public BigDecimal getDyValueDiscount() {
        return dyValueDiscount;
    }

    public void setDyValueDiscount(BigDecimal dyValueDiscount) {
        this.dyValueDiscount = dyValueDiscount;
    }

    public Integer getDyTerm() {
        return dyTerm;
    }

    public void setDyTerm(Integer dyTerm) {
        this.dyTerm = dyTerm;
    }

    public String getDyCity() {
        return dyCity;
    }

    public void setDyCity(String dyCity) {
        this.dyCity = dyCity == null ? null : dyCity.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
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

    public BigDecimal getCreditLines() {
        return creditLines;
    }

    public void setCreditLines(BigDecimal creditLines) {
        this.creditLines = creditLines;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation == null ? null : vocation.trim();
    }

    public String getTipsPhone() {
        return tipsPhone;
    }

    public void setTipsPhone(String tipsPhone) {
        this.tipsPhone = tipsPhone == null ? null : tipsPhone.trim();
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getOldRepayRate() {
        return oldRepayRate;
    }

    public void setOldRepayRate(BigDecimal oldRepayRate) {
        this.oldRepayRate = oldRepayRate;
    }

    public BigDecimal getUnuseBorrowBalance() {
        return unuseBorrowBalance;
    }

    public void setUnuseBorrowBalance(BigDecimal unuseBorrowBalance) {
        this.unuseBorrowBalance = unuseBorrowBalance;
    }

    public BigDecimal getMonthRepayBalance() {
        return monthRepayBalance;
    }

    public void setMonthRepayBalance(BigDecimal monthRepayBalance) {
        this.monthRepayBalance = monthRepayBalance;
    }
}