package com.hwc.framework.modules.domain;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/27.
 */
@Data
public class BorrowAuditBean {
    @ApiModelProperty("订单ID")
    private Long id;
    @ApiModelProperty(value = "状态", allowableValues ="22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期 90 坏账")
    private String state;
    /**
     * 备注、审核说明
     */
    private String remark;
    private String borrowId;
    private String cityCode;
    private String declarationPeople;
    private String declarationCompany;
    private String parkingPosition;
    private Double dyValue;
    private Double realQuota;
    private BigDecimal realRate;
}
