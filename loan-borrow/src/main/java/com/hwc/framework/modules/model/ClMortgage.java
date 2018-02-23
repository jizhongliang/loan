package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_mortgage")
public class ClMortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 抵押物的描述，车位，房产 等
     */
    private String descript;

    /**
     * 所在城市
     */
    @Column(name = "dy_city")
    private String dyCity;

    /**
     * 所在地址
     */
    @Column(name = "dy_address")
    private String dyAddress;

    @Column(name = "dy_community")
    private String dyCommunity;

    /**
     * 抵押物类型 10 车库  20 房产
     */
    @Column(name = "dy_type")
    private String dyType;

    /**
     * 面积
     */
    @Column(name = "dy_area")
    private BigDecimal dyArea;


    /**
     * 购买时间
     */
    @Column(name = "dy_buy_year")
    private String DyBuyYear;

    /**
     * 购买价格
     */
    @Column(name = "dy_buy_price")
    private BigDecimal dyBuyPrice;

    /**
     * 要借款额度
     */
    @Column(name = "borrow_amount")
    private BigDecimal borrowAmount;
    /**
     * 当前市值
     */
    @Column(name = "market_value")
    private BigDecimal marketValue;
    /**
     * 有效期
     */
    @Column(name = "expire_date")
    private Date expireDate;

    private String name;
    private String mobile;

    @Column(name = "order_no")
    private String orderNo;

    /**
     * 状态  10 新申请 20 通过初审 30 审核通过 40 终身通过 50 已提现 60 审核被拒绝  70 冻结'
     */
    private String state;

    /**
     * 是否有效 F 有效 T 无效
     */
    private String halt;

    /**
     * 申请时间
     */
    @Column(name = "apply_date")
    private Date applyDate;

    private Long applyby;

    /**
     * 最终审核额度
     */
    @Column(name = "real_quota")
    private BigDecimal realQuota;

    /**
     * 最终核定的 利率 0.05%
     */
    @Column(name = "real_rate")
    private BigDecimal realRate;

    @Column(name = "pass_by")
    private Long passBy;

    private Date updated;

    /**
     * 创建时间
     */
    private Date created;

    private String remark;


    public String getDyBuyYear() {
        return DyBuyYear;
    }

    public void setDyBuyYear(String dyBuyYear) {
        DyBuyYear = dyBuyYear;
    }

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
     * 获取抵押物的描述，车位，房产 等
     *
     * @return descript - 抵押物的描述，车位，房产 等
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 设置抵押物的描述，车位，房产 等
     *
     * @param descript 抵押物的描述，车位，房产 等
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * 获取所在城市
     *
     * @return dy_city - 所在城市
     */
    public String getDyCity() {
        return dyCity;
    }

    /**
     * 设置所在城市
     *
     * @param dyCity 所在城市
     */
    public void setDyCity(String dyCity) {
        this.dyCity = dyCity;
    }

    /**
     * 获取所在地址
     *
     * @return dy_address - 所在地址
     */
    public String getDyAddress() {
        return dyAddress;
    }

    /**
     * 设置所在地址
     *
     * @param dyAddress 所在地址
     */
    public void setDyAddress(String dyAddress) {
        this.dyAddress = dyAddress;
    }

    /**
     * @return dy_community
     */
    public String getDyCommunity() {
        return dyCommunity;
    }

    /**
     * @param dyCommunity
     */
    public void setDyCommunity(String dyCommunity) {
        this.dyCommunity = dyCommunity;
    }

    /**
     * 获取抵押物类型 10 车库  20 房产
     *
     * @return dy_type - 抵押物类型 10 车库  20 房产
     */
    public String getDyType() {
        return dyType;
    }

    /**
     * 设置抵押物类型 10 车库  20 房产
     *
     * @param dyType 抵押物类型 10 车库  20 房产
     */
    public void setDyType(String dyType) {
        this.dyType = dyType;
    }

    /**
     * 获取面积
     *
     * @return dy_area - 面积
     */
    public BigDecimal getDyArea() {
        return dyArea;
    }

    /**
     * 设置面积
     *
     * @param dyArea 面积
     */
    public void setDyArea(BigDecimal dyArea) {
        this.dyArea = dyArea;
    }


    /**
     * 获取购买价格
     *
     * @return dy_buy_price - 购买价格
     */
    public BigDecimal getDyBuyPrice() {
        return dyBuyPrice;
    }

    /**
     * 设置购买价格
     *
     * @param dyBuyPrice 购买价格
     */
    public void setDyBuyPrice(BigDecimal dyBuyPrice) {
        this.dyBuyPrice = dyBuyPrice;
    }

    /**
     * 获取要借款额度
     *
     * @return borrow_amount - 要借款额度
     */
    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    /**
     * 设置要借款额度
     *
     * @param borrowAmount 要借款额度
     */
    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    /**
     * 获取有效期
     *
     * @return expire_date - 有效期
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置有效期
     *
     * @param expireDate 有效期
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取状态  10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
     *
     * @return status - 状态  10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
     */


    /**
     * 设置状态  10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
     *
     * @param status 状态  10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
     */


    /**
     * 获取是否有效 F 有效 T 无效
     *
     * @return halt - 是否有效 F 有效 T 无效
     */
    public String getHalt() {
        return halt;
    }

    /**
     * 设置是否有效 F 有效 T 无效
     *
     * @param halt 是否有效 F 有效 T 无效
     */
    public void setHalt(String halt) {
        this.halt = halt;
    }

    /**
     * 获取申请时间
     *
     * @return apply_date - 申请时间
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 设置申请时间
     *
     * @param applyDate 申请时间
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return applyby
     */
    public Long getApplyby() {
        return applyby;
    }

    /**
     * @param applyby
     */
    public void setApplyby(Long applyby) {
        this.applyby = applyby;
    }

    /**
     * 获取最终审核额度
     *
     * @return real_quota - 最终审核额度
     */
    public BigDecimal getRealQuota() {
        return realQuota;
    }

    /**
     * 设置最终审核额度
     *
     * @param realQuota 最终审核额度
     */
    public void setRealQuota(BigDecimal realQuota) {
        this.realQuota = realQuota;
    }

    /**
     * 获取最终核定的 利率 0.05%
     *
     * @return real_rate - 最终核定的 利率 0.05%
     */
    public BigDecimal getRealRate() {
        return realRate;
    }

    /**
     * 设置最终核定的 利率 0.05%
     *
     * @param realRate 最终核定的 利率 0.05%
     */
    public void setRealRate(BigDecimal realRate) {
        this.realRate = realRate;
    }

    /**
     * @return pass_by
     */
    public Long getPassBy() {
        return passBy;
    }

    /**
     * @param passBy
     */
    public void setPassBy(Long passBy) {
        this.passBy = passBy;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
}