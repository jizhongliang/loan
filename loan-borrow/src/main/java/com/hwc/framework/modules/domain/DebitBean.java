package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  on 2017/12/6.
 */
@Data
public class DebitBean {
    @ApiModelProperty("分期号Id")
    private Long repayId;
    @ApiModelProperty("交易密码")
    private String tradePassword;
    @ApiModelProperty("信用还是车位")
    private int type;


}
