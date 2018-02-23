package com.hwc.framework.modules.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

@Data
public class DWContacts {
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
 	  * 
 	  * 额度期限
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
 	
	/**
 	 * 错误信息
 	 */
 	private String msg;
 	
}