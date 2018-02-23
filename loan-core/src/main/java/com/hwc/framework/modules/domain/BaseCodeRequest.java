package com.hwc.framework.modules.domain;

import com.hwc.base.api.ItemsRequest;
import lombok.Data;

/**
 * Created by   on 2017/11/28.
 */
@Data
public class BaseCodeRequest extends ItemsRequest {
    private String cat;
    private String code;
    private String descript;
    private String halt;
}
