package com.hwc.framework.modules.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/10/23.
 * 抵押物
 */
@Data
public class MortgageBean {

    @ApiModelProperty("抵押申请Id")
    private Long id;

    private Long userId;
    @ApiModelProperty("订单号")
    private String orderNo;

    private String userName;

    private String mobile;
    /**
     * 抵押物的描述，车位，房产 等
     */
    private String descript;

    /**
     * 所在城市
     */
    @ApiModelProperty("抵押物所在城市")
    private String dyCity;

    /**
     * 所在地址
     * <p>
     */
    @ApiModelProperty("抵押物所在地址")
    private String dyAddress;
    /* <p>
     * 社区
     * <p>
     */
    @ApiModelProperty("社区")
    private String dyCommunity;
    /* <p>
    * /**
    * 抵押物类型 10 车库  20 房产
    */
    private String dyType;

    /**
     * 面积
     */
    @ApiModelProperty("面积")
    private Double dyArea;

    /**
     * 购买时间
     */
    @ApiModelProperty("购买年份")
    private String dyBuyYear;

    /**
     * 购买价格
     */
    @ApiModelProperty("购买价格")
    private Double dyBuyPrice;
    @ApiModelProperty("当前价格")
    private  Double newPrice;

    /**
     * 要借款额度
     */
    @ApiModelProperty("要借款额度")
    private Double borrowAmount;

    /**
     * 状态  10 新申请 20 审核中 30 审核通过  40 审核被拒绝  50 冻结
     */
    @ApiModelProperty(value = "状态",allowableValues = "状态  10 新申请 20 通过初审 30 审核通过 40 终身通过 50 已提现 60 审核被拒绝  70 冻结'")
    private String status;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    private Date applyDate;

    /**
     * 最终审核额度
     */
    @ApiModelProperty("最终审核额度")
    private Double realQuota;

    /**
     * 最终核定的 利率 0.05%
     */
    @ApiModelProperty("最终核定的 利率 0.05%")
    private BigDecimal realRate;

    /**
     * 用户认证类图片
     */
    @ApiModelProperty("用户认证类图片 ")
    private List<String> userImg;
    /**
     * 资产类图片信息
     */
    @ApiModelProperty("资产类图片信息 ")
    private List<String> assetsImg;
    /**
     * 其他认证信息
     */
    @ApiModelProperty("其他认证信息 ")
    private List<String> otherImg;

    private Date created;
    @ApiModelProperty("备注 ")
    private String remark;

}
