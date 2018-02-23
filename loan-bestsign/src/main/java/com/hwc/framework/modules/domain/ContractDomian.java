package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by  on 2017/12/18.
 */
@Data
public class ContractDomian {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    private String name;

    private String mobile;

    private String idno;

    /**
     * 借款类型， X  信用类 ,D  抵押类
     */

    private String borrowType;


    /**
     * 期数
     */
    @ApiModelProperty(value = "分期数", required = true)
    private int periods;
    /**
     * 借款金额
     */
    @ApiModelProperty(value = "可借款金额", required = true)
    private Double amount;


    /**
     * 订单生成时间
     */

    private Date createTime;


    /**
     * 借款日利率
     */
    @ApiModelProperty(value = "借款日利率 %", required = true)
    private String rate;


    /**
     * 客户端 默认10-移动app
     */
    @ApiModelProperty(value = "客户端 默认10-移动app", required = true)
    private String client;



    /**
     *
     */
    @ApiModelProperty("银行卡号")
    private String cardno;
    /**
     * 逾期费用提醒
     */


    /**
     * 消费场景
     */
    @ApiModelProperty("消费场景")
    private String scenes;
    @ApiModelProperty("手动签署时，当用户签署完成后，指定回跳的页面地址")
    private String returnUrl;

    /**
     * 信用合同列表url
     */
    private String contractListUrl;

    /**
     * 合同序列号 1 - 8
     */
    private String type;
}
