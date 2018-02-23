package com.hwc.framework.modules.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class BorrowerUserinfo {
    /**
     * 借款金额
     */
    private String boAmount;
    /**
     * 借款期限
     */
    private String termLoan;

    /**
     * 借款人
     */
    private String boUserName;
    /**
     * 借款人手机号
     */
    private String mobile;

    /**
     * 平台来源
     */
    private String bosource;

    /**
     * 借款人银行账号
     */
    private String boAccNo;

    /**
     * 借款人银行名称
     */
    private String boBankName;

    /**
     * 借款人银行编号
     */
    private String boBankNo;

    /**
     * 借款人开户行省名
     */
    private String boProvinceName;

    /**
     * 借款人开户行市名
     */
    private String boCityName;

    /**
     * 借款人开户支行名称
     */
    private String boBrancheBank;

    /**
     * 借款人身份证号
     */
    private String idCard;

    /**
     * 借款人身份证URL地址
     */
    private String idCardUrl;

    /**
     * 银行卡预留手机号
     */
    private String transMobile;

    /**
     * 订单号
     */
    private String thirdTransaction;

    /**
     * 紧急联系人一
     */
    private String emergencyContact1;

    /**
     * 联系人一联系方式
     */
    private String contactPhone1;

    /**
     * 紧急联系人二
     */
    private String emergencyContact2;
    /**
     * 联系人二联系方式
     */
    private String contactPhone2;
    /**
     * 征信报告地址
     */
    private String creditReportUrl;

    /**
     * 电子合同编号
     */
    private String boContractNo;

    /**
     * 上上签token
     */
    private String crossToken;

    /**
     * 电子协议地址
     */
    private String agreementUrl;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 通知回调地址
     */
    private String notifyUrl;
    /**
     * 主体属性
     */
    private String subjectAttribute;
    /**
     * 行业
     */
    private String industry;
    /**
     * 借款用途
     */
    private String usageLoan;

    private Integer repaymentMethod;
    /**
     * 婚姻状况
     */
    private Integer maritalStatus = 3;

    /**
     * 最小年收入
     */
    private Long incomeMin = 500000L;

    /**
     * 最小年收入
     */
    private Long incomeMax = 1000000L;

    /**
     * 还款来源
     */
    private String sourceRepayment="工资收入";

    /**
     * 涉诉情况
     */
    private String caseComplaint="无";
}
