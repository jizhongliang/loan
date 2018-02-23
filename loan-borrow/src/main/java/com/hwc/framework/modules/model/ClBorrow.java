package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_borrow")
public class ClBorrow {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 借款类型， X  信用类 ,D  抵押类
     */
    @Column(name = "borrow_type")
    private String borrowType;

    /**
     * 抵押物申请订单号
     */
    private Long mid;

    private String name;

    private String mobile;
    /**
     * 借款金额
     */
    private BigDecimal amount;

    /**
     * 实际到账金额
     */
    @Column(name = "real_amount")
    private BigDecimal realAmount;

    /**
     * 综合费用(借款利息+服务费+信息认证费)
     */
    private BigDecimal fee;

    /**
     * 分期数
     */
    private Integer periods;

    /**
     * 订单生成时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 借款期限(天)
     */
    @Column(name = "time_limit")
    private String timeLimit;

    /**
     * 订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     */
    private String state;

    /**
     * 审核通过后有效期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    private String isnotify;

    /**
     * 收款银行卡关联id
     */
    @Column(name = "card_id")
    private Long cardId;

    /**
     * 服务费
     */
    @Column(name = "service_fee")
    private BigDecimal serviceFee;

    /**
     * 流量费
     */
    @Column(name = "flow_fee")
    private BigDecimal flowFee;

    /**
     * 信息认证费
     */
    @Column(name = "info_auth_fee")
    private BigDecimal infoAuthFee;

    /**
     * 借款利息
     */
    private BigDecimal interest;

    /**
     * 借款日利率
     */
    private BigDecimal rate;

    /**
     * 客户端 默认10-移动app
     */
    private String client;

    /**
     * 发起借款地址
     */
    private String address;

    /**
     * 借款经纬度坐标
     */
    private String coordinate;

    /**
     * 备注、审核说明
     */
    private String remark;

    /**
     * ip地址
     */
    private String ip;
    /**
     * 放款时间
     */
    @Column(name = "loan_time")
    private Date loanTime;

    /**
     * 消费场景
     */
    private String scenes;

    @Column(name = "contract_id")
    private String contractId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取借款类型， X  信用类 ,D  抵押类
     *
     * @return borrow_type - 借款类型， X  信用类 ,D  抵押类
     */
    public String getBorrowType() {
        return borrowType;
    }

    /**
     * 设置借款类型， X  信用类 ,D  抵押类
     *
     * @param borrowType 借款类型， X  信用类 ,D  抵押类
     */
    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    /**
     * 获取抵押物申请订单号
     *
     * @return mid - 抵押物申请订单号
     */
    public Long getMid() {
        return mid;
    }

    /**
     * 设置抵押物申请订单号
     *
     * @param mid 抵押物申请订单号
     */
    public void setMid(Long mid) {
        this.mid = mid;
    }

    /**
     * 获取借款金额
     *
     * @return amount - 借款金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置借款金额
     *
     * @param amount 借款金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取实际到账金额
     *
     * @return real_amount - 实际到账金额
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * 设置实际到账金额
     *
     * @param realAmount 实际到账金额
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * 获取综合费用(借款利息+服务费+信息认证费)
     *
     * @return fee - 综合费用(借款利息+服务费+信息认证费)
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置综合费用(借款利息+服务费+信息认证费)
     *
     * @param fee 综合费用(借款利息+服务费+信息认证费)
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获取分期数
     *
     * @return periods - 分期数
     */
    public Integer getPeriods() {
        return periods;
    }

    /**
     * 设置分期数
     *
     * @param periods 分期数
     */
    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    /**
     * 获取订单生成时间
     *
     * @return create_time - 订单生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单生成时间
     *
     * @param createTime 订单生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取借款期限(天)
     *
     * @return time_limit - 借款期限(天)
     */
    public String getTimeLimit() {
        return timeLimit;
    }

    /**
     * 设置借款期限(天)
     *
     * @param timeLimit 借款期限(天)
     */
    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * 获取订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     *
     * @return state - 订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     */
    public String getState() {
        return state;
    }

    /**
     * 设置订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     *
     * @param state 订单状态   15 待初审  16 初审不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取审核通过后有效期时间
     *
     * @return expire_time - 审核通过后有效期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置审核通过后有效期时间
     *
     * @param expireTime 审核通过后有效期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return isnotify
     */
    public String getIsnotify() {
        return isnotify;
    }

    /**
     * @param isnotify
     */
    public void setIsnotify(String isnotify) {
        this.isnotify = isnotify;
    }

    /**
     * 获取收款银行卡关联id
     *
     * @return card_id - 收款银行卡关联id
     */
    public Long getCardId() {
        return cardId;
    }

    /**
     * 设置收款银行卡关联id
     *
     * @param cardId 收款银行卡关联id
     */
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取服务费
     *
     * @return service_fee - 服务费
     */
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    /**
     * 设置服务费
     *
     * @param serviceFee 服务费
     */
    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * 获取流量费
     *
     * @return flow_fee - 流量费
     */
    public BigDecimal getFlowFee() {
        return flowFee;
    }

    /**
     * 设置流量费
     *
     * @param flowFee 流量费
     */
    public void setFlowFee(BigDecimal flowFee) {
        this.flowFee = flowFee;
    }

    /**
     * 获取信息认证费
     *
     * @return info_auth_fee - 信息认证费
     */
    public BigDecimal getInfoAuthFee() {
        return infoAuthFee;
    }

    /**
     * 设置信息认证费
     *
     * @param infoAuthFee 信息认证费
     */
    public void setInfoAuthFee(BigDecimal infoAuthFee) {
        this.infoAuthFee = infoAuthFee;
    }

    /**
     * 获取借款利息
     *
     * @return interest - 借款利息
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置借款利息
     *
     * @param interest 借款利息
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取借款日利率
     *
     * @return rate - 借款日利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置借款日利率
     *
     * @param rate 借款日利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取客户端 默认10-移动app
     *
     * @return client - 客户端 默认10-移动app
     */
    public String getClient() {
        return client;
    }

    /**
     * 设置客户端 默认10-移动app
     *
     * @param client 客户端 默认10-移动app
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * 获取发起借款地址
     *
     * @return address - 发起借款地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置发起借款地址
     *
     * @param address 发起借款地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取借款经纬度坐标
     *
     * @return coordinate - 借款经纬度坐标
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * 设置借款经纬度坐标
     *
     * @param coordinate 借款经纬度坐标
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * 获取备注、审核说明
     *
     * @return remark - 备注、审核说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注、审核说明
     *
     * @param remark 备注、审核说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
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

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getScenes() {
        return scenes;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }
}