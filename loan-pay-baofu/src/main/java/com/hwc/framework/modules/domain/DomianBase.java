package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/9.
 */
@Data
public class DomianBase {
    private String service_name;
    private String ip;
    private Long borrow_id;
    private Long user_id;
    private String order_no;
}
