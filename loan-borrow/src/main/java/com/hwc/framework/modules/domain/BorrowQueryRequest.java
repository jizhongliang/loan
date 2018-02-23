package com.hwc.framework.modules.domain;

import com.hwc.base.api.ItemsRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by   on 2017/11/20.
 */
@Data
public class BorrowQueryRequest extends ItemsRequest {
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty(value = "状态", allowableValues = "22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账")
    private String state;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("申请借款开始时间")
    private Date start;
    @ApiModelProperty("申请借款结束时间")
    private Date end;
    @ApiModelProperty("可以不填")
    private String type;
    @ApiModelProperty("用户Id")
    private Long userId;

}
