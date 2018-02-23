package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/28.
 */
@Data
public class CardAuthDataBean {

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

    @ApiModelProperty("银行代码")
    private String bankCode;


}
