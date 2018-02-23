package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * Created by   on 2017/11/27.
 */
@Data
public class DEquipment {
    @ApiModelProperty("设备指纹")
    private String finger;
    @ApiModelProperty("其他信息")
    private String numbers;
    @ApiModelProperty("时间戳")
    private Long timestamp;
}
