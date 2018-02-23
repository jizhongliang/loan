package com.hwc.framework.modules.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  on 2017/12/1.
 */
@Data
public class UserBorrowQuota {
    @ApiModelProperty("可借款额度")
    private String quota;
}
