package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  on 2017/12/7.
 */
@Data
public class H5UrlConfig {
    @ApiModelProperty("注册协议")
    private String register;
    @ApiModelProperty("登录协议")
    private String login;
    @ApiModelProperty("信用借款协议")
    private String creditBorrow;
    @ApiModelProperty("抵押借款协议")
    private String mortgageBorrow;
    @ApiModelProperty("信用还款协议")
    private String creditRepay;
    @ApiModelProperty("抵押还款协议")
    private String mortgageRepay;
    @ApiModelProperty("信用提前还款协议")
    private String creditRreRepay;
    @ApiModelProperty("抵押提前还款协议")
    private String mortgageRreRepay;
    @ApiModelProperty("关于我们")
    private String about;
    @ApiModelProperty("常见问题")
    private String problem;
    @ApiModelProperty("借款须知")
    private String notes;

}
