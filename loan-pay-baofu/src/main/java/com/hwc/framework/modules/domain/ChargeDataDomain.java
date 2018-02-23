package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class ChargeDataDomain extends DomianBase {
    private String bank_no;
    private String id_no;
    private String name;
    private String mobile;
    private String agree_no;
    private Double amount;
    private String trans_serial_no;
    private String bank_name;
    private String bank_code;
    private String trade_date;
    private String repay_id;
    private String channel;

}
