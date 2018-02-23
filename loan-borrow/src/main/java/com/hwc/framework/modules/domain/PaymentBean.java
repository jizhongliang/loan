package com.hwc.framework.modules.domain;

import lombok.Data;

/**
 * Created by   on 2017/11/1.
 */
@Data
public class PaymentBean {
    private String bankCardNo;
    private String orderNo;
    private Double amount;
    private String realName;
    private Double realAmount;
    private Long borrowId;
    private String confim_code;
    private String dt_order;
    private String agree_no;
}
