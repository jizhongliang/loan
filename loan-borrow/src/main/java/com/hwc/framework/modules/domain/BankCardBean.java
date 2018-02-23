package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class BankCardBean {
    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户标识
     */
    @ApiModelProperty("用户标识")
    private Long userId;

    /**
     * 开户行
     */
    @ApiModelProperty("开户行")
    private String bank;

    /**
     * 银行卡号
     */
    @ApiModelProperty("银行卡号")
    private String cardNo;

    /**
     * 预留手机号
     */
    @ApiModelProperty("预留手机号")
    private String phone;

    /**
     * 签约协议编号
     */
    @ApiModelProperty("签约协议编号")
    private String agreeNo;

    /**
     * 绑卡时间
     */
    @ApiModelProperty("绑卡时间")
    private Date bindTime;
    @ApiModelProperty(value = "持卡人名字",required = true)
    private String id_holder;
    @ApiModelProperty(value = "身份证号码",required = true)
    private String id_no;
    /**
     * 签约结果
     */
    @ApiModelProperty("签约结果")
    private String signResult;
    @ApiModelProperty("银行代码")
    private String bankCode;
    @ApiModelProperty("通道")
    private String channel;
}
