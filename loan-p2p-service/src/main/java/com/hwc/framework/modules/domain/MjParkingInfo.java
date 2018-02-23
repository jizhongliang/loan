package com.hwc.framework.modules.domain;

import java.util.Date;

public class MjParkingInfo {
    private Long id;

    private Long userId;

    private String parkingPosition;

    private Long price;

    private String phone;

    private String name;

    private String demandLabel;

    private String imgUrl1;

    private String imgUrl2;
    
    private String imgUrl3;
    
    private String isDemand;

    private String demandIsHot;

    private Date createTime;
    
    private String remark;
    
    private String createTime1;
    
    private String villageName;
    
    private String isCollect;
    
    private String collectId;
    
    
    public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getParkingPosition() {
        return parkingPosition;
    }

    public void setParkingPosition(String parkingPosition) {
        this.parkingPosition = parkingPosition == null ? null : parkingPosition.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDemandLabel() {
        return demandLabel;
    }

    public void setDemandLabel(String demandLabel) {
        this.demandLabel = demandLabel == null ? null : demandLabel.trim();
    }

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1 == null ? null : imgUrl1.trim();
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2 == null ? null : imgUrl2.trim();
    }
    
    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3 == null ? null : imgUrl3.trim();
    }
    
    public String getIsDemand() {
        return isDemand;
    }

    public void setIsDemand(String isDemand) {
        this.isDemand = isDemand == null ? null : isDemand.trim();
    }

    public String getDemandIsHot() {
        return demandIsHot;
    }

    public void setDemandIsHot(String demandIsHot) {
        this.demandIsHot = demandIsHot == null ? null : demandIsHot.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}