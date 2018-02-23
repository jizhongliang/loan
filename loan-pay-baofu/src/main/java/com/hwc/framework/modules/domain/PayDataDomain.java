package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class PayDataDomain extends DomianBase {
    private Double amount;
    private String real_name;
    private String id_no;
    private String mobile;
    private String agree_no;
    private String bank_name;
    private String bank_card_no;
    private String pay_channel;

}
