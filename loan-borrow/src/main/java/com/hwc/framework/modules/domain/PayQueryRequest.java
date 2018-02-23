package com.hwc.framework.modules.domain;

import com.hwc.base.api.ItemsRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/21.
 */
@Data
public class PayQueryRequest extends ItemsRequest {
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty(value = "状态",allowableValues = "10:待支付 40:支付成功、50:支付失败")
    private String state;
    @ApiModelProperty(value = "支付方式",allowableValues = " 10:代付 20:代扣 30:线下代付 40:线下代扣")
    private String type;

    @ApiModelProperty("支付场景")
    private String scenes;

    @ApiModelProperty("订单号")
    private String order_no;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("支付开始时间")
    private Date start;
    @ApiModelProperty("支付结束时间")
    private Date end;
    @ApiModelProperty("不填")
    private String borrow_type;
}
