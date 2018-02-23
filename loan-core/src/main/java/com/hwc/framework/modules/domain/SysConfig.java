package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/1.
 */
@Data
public class SysConfig {
    @ApiModelProperty("id")
    private Long id;

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

    /**
     * 参数对应的值
     */
    @ApiModelProperty("参数对应的值")
    private String value;

    /**
     * 状态  0不启用  1启用
     */
    @ApiModelProperty("状态  0不启用  1启用")
    private String state;

    /**
     * 备注说明
     */
    @ApiModelProperty("备注说明")
    private String remark;
}
