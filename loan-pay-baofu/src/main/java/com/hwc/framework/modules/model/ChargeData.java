package com.hwc.framework.modules.model;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class ChargeData {
    private String pay_cm="2";
    private String acc_no;
    private String id_card_type="01";
    private String id_card;
    private String id_holder;
    private String mobile;
    private String valid_date="";
    private String valid_no="";
    private String trans_id;
    private String txn_amt;
    private String trade_date;
    private String additional_info;
    private String req_reserved;
    private String trans_serial_no;
    private String pay_code;
    private String biz_type = "0000";
    private String txn_sub_type = "13";
    private String terminal_id;
    private String member_id;
}
