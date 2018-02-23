package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  on 2017/12/15.
 */
@Data
public class HelpBean {
    @ApiModelProperty("包名")
    private String packageName;
    @ApiModelProperty("类型")
    private String type;
}
