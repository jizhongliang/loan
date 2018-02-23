package com.hwc.framework.modules.model;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class ConfirmCardBindData {


    private String terminal_id;
    private String member_id;

    private String trade_no;;
    private String sms_code;
    private String trade_date;
    private String additional_info;
    private String req_reserved;
}
