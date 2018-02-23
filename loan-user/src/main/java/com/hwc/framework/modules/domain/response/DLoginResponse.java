package com.hwc.framework.modules.domain.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by  on 2017/9/29.
 */
@Data
public class DLoginResponse {

    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("类型")
    private String cat;
    @ApiModelProperty("id")
    private Long   UserId;
    @ApiModelProperty("loginName")
    private String loginName;
    private String borrowRate;
    @ApiModelProperty("登录状态")
    private String isLogin;
}
