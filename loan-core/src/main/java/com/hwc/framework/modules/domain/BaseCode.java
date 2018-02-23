package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/1.
 */
@Data
public class BaseCode {
    @ApiModelProperty("数据分类")
    private String  cat;
    /**
     * 代码
     */
    @ApiModelProperty("代码")
    private String  code;

    /**
     * 中文描述
     */
    @ApiModelProperty("中文描述")
    private String  descript;

    /**
     * 是否系统代码 :  T/F
     */
    @ApiModelProperty("是否系统代码 :  T/F")
    private String  sys;

    /**
     * 是否停用 :  T/F
     */
    @ApiModelProperty(value = "是否停用", allowableValues = "T F")
    private String  halt;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**  */
    @ApiModelProperty(value = "获取扩展字段")
    private String  exts;

}
