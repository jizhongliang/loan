package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageFinalAuthResponse;

public class ManageMortgageFinalAuthRequest extends RequestBase<ManageMortgageFinalAuthResponse> {

    public static final String METHOD = "/manage/mortgage/finalAuth";

    private Long id;
    private String state;
    private String remark;
    private String borrowId;
    private String cityCode;
    private String declarationPeople;
    private String declarationCompany;
    private String parkingPosition;
    private Double dyValue;
    private Double realQuota;
    private BigDecimal realRate;
    public ManageMortgageFinalAuthRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    

	public Double getRealQuota() {
		return realQuota;
	}

	public void setRealQuota(Double realQuota) {
		this.realQuota = realQuota;
	}

	public BigDecimal getRealRate() {
		return realRate;
	}

	public void setRealRate(BigDecimal realRate) {
		this.realRate = realRate;
	}

	public String getParkingPosition() {
		return parkingPosition;
	}

	public void setParkingPosition(String parkingPosition) {
		this.parkingPosition = parkingPosition;
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

	public String getDeclarationPeople() {
		return declarationPeople;
	}

	public void setDeclarationPeople(String declarationPeople) {
		this.declarationPeople = declarationPeople;
	}

	public String getDeclarationCompany() {
		return declarationCompany;
	}

	public void setDeclarationCompany(String declarationCompany) {
		this.declarationCompany = declarationCompany;
	}

	public Double getDyValue() {
		return dyValue;
	}

	public void setDyValue(Double dyValue) {
		this.dyValue = dyValue;
	}

	public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

} 