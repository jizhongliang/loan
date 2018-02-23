package com.hwc.framework.modules.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by   on 2017/11/15.
 */
@Data
public class PreRepayBean {
    private Double amount;
    private Double Interest;
    private Double total;
    private String bank_card_no;
    private String bank_card_name;
    private Long borrow_id;
    private Long user_id;
    private Double rate;
    private BigDecimal repayAmount;
}

