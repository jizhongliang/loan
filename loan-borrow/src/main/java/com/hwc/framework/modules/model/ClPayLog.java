package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "cl_pay_log")
public class ClPayLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 请求订单标识
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 借款标识
     */
    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    private String name;

    private String mobile;

    /**
     * 用户银行卡卡号
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 用户银行卡开户行
     */
    private String bank;
    /**
     * 分期号Id
     */
    @Column(name = "repay_id")
    private Long repayId;
    /**
     * 确认码，实时付款确认交易使用
     */
    @Column(name = "confirm_code")
    private String confirmCode;
    /**
     * 序列号
     */
    @Column(name = "serial_number")
    private String serialNumber;
    /**
     * 资金来源 10:自有资金 20:其他资金
     */
    private String source;

    /**
     * 支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣
     */
    private String type;

    /**
     * 业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
     */
    private String scenes;

    /**
     * 支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
     */
    private String state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 支付请求时间
     */
    @Column(name = "pay_req_time")
    private Date payReqTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取请求订单标识
     *
     * @return order_no - 请求订单标识
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置请求订单标识
     *
     * @param orderNo 请求订单标识
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取用户标识
     *
     * @return user_id - 用户标识
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取借款标识
     *
     * @return borrow_id - 借款标识
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * 设置借款标识
     *
     * @param borrowId 借款标识
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * 获取支付金额
     *
     * @return amount - 支付金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置支付金额
     *
     * @param amount 支付金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取用户银行卡卡号
     *
     * @return card_no - 用户银行卡卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置用户银行卡卡号
     *
     * @param cardNo 用户银行卡卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取用户银行卡开户行
     *
     * @return bank - 用户银行卡开户行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置用户银行卡开户行
     *
     * @param bank 用户银行卡开户行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取确认码，实时付款确认交易使用
     *
     * @return confirm_code - 确认码，实时付款确认交易使用
     */
    public String getConfirmCode() {
        return confirmCode;
    }

    /**
     * 设置确认码，实时付款确认交易使用
     *
     * @param confirmCode 确认码，实时付款确认交易使用
     */
    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    /**
     * 获取资金来源 10:自有资金 20:其他资金
     *
     * @return source - 资金来源 10:自有资金 20:其他资金
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置资金来源 10:自有资金 20:其他资金
     *
     * @param source 资金来源 10:自有资金 20:其他资金
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣
     *
     * @return type - 支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣
     */
    public String getType() {
        return type;
    }

    /**
     * 设置支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣
     *
     * @param type 支付方式 10:代付 20:代扣 30:线下代付  40:线下代扣
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
     *
     * @return scenes - 业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
     */
    public String getScenes() {
        return scenes;
    }

    /**
     * 设置业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
     *
     * @param scenes 业务场景  10、放款  11、分润 12、退还 20、还款 21、补扣
     */
    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    /**
     * 获取支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
     *
     * @return state - 支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
     */
    public String getState() {
        return state;
    }

    /**
     * 设置支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
     *
     * @param state 支付状态   10:待支付 、15:待审核 、20:审核通过、 30:审核不通过 、40:支付成功、50:支付失败
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取支付请求时间
     *
     * @return pay_req_time - 支付请求时间
     */
    public Date getPayReqTime() {
        return payReqTime;
    }

    /**
     * 设置支付请求时间
     *
     * @param payReqTime 支付请求时间
     */
    public void setPayReqTime(Date payReqTime) {
        this.payReqTime = payReqTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getRepayId() {
        return repayId;
    }

    public void setRepayId(Long repayId) {
        this.repayId = repayId;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}