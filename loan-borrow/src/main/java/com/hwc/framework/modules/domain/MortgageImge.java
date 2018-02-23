package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;

/**
 * Created by   on 2017/11/15.
 */
@Data
public class MortgageImge {
    @ApiModelProperty("图片Url")
    private List<String> url;
    @ApiModelProperty(value = "图片分类",allowableValues = "A 资产证明类，U 用户证明类，O 其他证明")
    private String cat;
    @ApiModelProperty("抵押申请的订单号")
    private Long mid;

    private Long userId;
}
