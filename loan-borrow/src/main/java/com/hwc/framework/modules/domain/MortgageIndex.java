package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.PackagePrivate;

import javax.print.event.PrintJobAttributeEvent;

/**
 * Created by   on 2017/11/15.
 */
@Data
public class MortgageIndex {

    /**
     * 当前首页的界面在哪里 apply  withdrawals
     */
    @ApiModelProperty("当前页面的位置是在哪个页面  apply =申请界面  withdrawals=提现界面 ")
    private String page;
    @ApiModelProperty("app上面提示的信息")
    private String alt_message;
    /**
     * 按钮是否可以用
     */
    @ApiModelProperty("首页上的按钮 是否可以点 1 可用 0 不可用")
    private String state;
    @ApiModelProperty("可用额度")
    private Double quota;
    @ApiModelProperty("总额度")
    private Double total_quota;
    @ApiModelProperty("利率说明")
    private String rate_descript;
    @ApiModelProperty("是否设置交易密码")
    private boolean pwd;
    @ApiModelProperty("是否完成身份认证")
    private boolean auth;
    @ApiModelProperty("车位分期申请id")
    private Long mid;
    @ApiModelProperty("申请是否完整")
    private boolean apply;

}
