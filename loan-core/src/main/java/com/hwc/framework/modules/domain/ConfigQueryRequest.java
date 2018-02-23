package com.hwc.framework.modules.domain;

import com.hwc.base.api.ItemsRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/28.
 */
@Data
public class ConfigQueryRequest extends ItemsRequest {
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;

    /**
     * 参数对应的值
     */
    @ApiModelProperty("参数对应的值")
    private String name;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;
}
