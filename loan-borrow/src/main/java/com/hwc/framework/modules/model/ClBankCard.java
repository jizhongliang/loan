package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_bank_card")
public class ClBankCard {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 走那个支付通道
     */
    @Column(name = "pay_code")
    private String payCode;

    /**
     * 开户行
     */
    private String bank;

    @Column(name = "bank_code")
    private String bankCode;

    /**
     * 银行卡号
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 预留手机号
     */
    private String phone;

    /**
     * 签约协议编号
     */
    @Column(name = "agree_no")
    private String agreeNo;

    /**
     * 绑卡时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

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
     * 获取走那个支付通道
     *
     * @return pay_code - 走那个支付通道
     */
    public String getPayCode() {
        return payCode;
    }

    /**
     * 设置走那个支付通道
     *
     * @param payCode 走那个支付通道
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    /**
     * 获取开户行
     *
     * @return bank - 开户行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置开户行
     *
     * @param bank 开户行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * @return bank_code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取银行卡号
     *
     * @return card_no - 银行卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置银行卡号
     *
     * @param cardNo 银行卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取预留手机号
     *
     * @return phone - 预留手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置预留手机号
     *
     * @param phone 预留手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取签约协议编号
     *
     * @return agree_no - 签约协议编号
     */
    public String getAgreeNo() {
        return agreeNo;
    }

    /**
     * 设置签约协议编号
     *
     * @param agreeNo 签约协议编号
     */
    public void setAgreeNo(String agreeNo) {
        this.agreeNo = agreeNo;
    }

    /**
     * 获取绑卡时间
     *
     * @return bind_time - 绑卡时间
     */
    public Date getBindTime() {
        return bindTime;
    }

    /**
     * 设置绑卡时间
     *
     * @param bindTime 绑卡时间
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
}