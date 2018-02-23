package com.hwc.framework.modules.domain;

import com.hwc.base.api.ItemsRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.print.event.PrintJobAttributeEvent;
import java.util.Date;

/**
 * Created by   on 2017/11/21.
 */
@Data
public class RepayQueryRequest extends ItemsRequest {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty(value = "状态",allowableValues = "还款状态 10-已还款 20-未还款")
    private String state;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("应还款开始时间")
    private Date start;
    @ApiModelProperty("应还款结束时间")
    private Date end;
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty(value = "还款方式",allowableValues ="10代扣，20银行卡转账，30支付宝转账" )
    private String repayWay;
    @ApiModelProperty("还款账号")
    private String account;
    @ApiModelProperty("可以不填")
    private String borrow_type;
}
