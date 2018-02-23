package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/24.
 */
@Data
public class OssConfig {
    @ApiModelProperty("存放文件位置")
    private String folder;
    @ApiModelProperty("accesskeyid")
    private String accesskeyid;
    @ApiModelProperty("accesskeysecret")
    private String accesskeysecret;
    @ApiModelProperty("bucketname")
    private String bucketname;
    @ApiModelProperty("endpoint")
    private String endpoint;
}
