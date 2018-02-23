package com.hwc.framework.modules.model;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class ChargeQueryData {
    private String txn_sub_type="31";
    private String biz_type="0000";
    private String terminal_id;
    private String member_id;
    private String orig_trans_id;
    private String trans_serial_no;

    private String orig_trade_date;
    private String additional_info;
    private String req_reserved;
}
