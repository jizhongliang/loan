package com.hwc.framework.common;

/**
 * Created by   on 2017/11/6.
 */
public class PayConstant {
    /** 付款状态 - 待支付 */
    public static final String STATE_PAYMENT_WAIT = "10";
    /** 付款状态 - 待审核 */
    public static final String STATE_PENDING_REVIEW = "15";
    /** 付款状态 - 审核通过 */
    public static final String STATE_AUDIT_PASSED = "20";
    /** 付款状态 - 审核不通过 */
    public static final String STATE_AUDIT_NOT_PASS = "30";
    /** 付款状态 - 支付成功 */
    public static final String STATE_PAYMENT_SUCCESS = "40";
    /** 付款状态 - 支付失败 */
    public static final String STATE_PAYMENT_FAILED = "50";

    /** 资金来源 - 自有资金 */
    public static final String SOURCE_FUNDS_OWN = "10";
    /** 资金来源 - 其他资金 */
    public static final String SOURCE_FUNDS_OTHER = "20";

    /** 付款方式 - 代付 */
    public static final String TYPE_PAYMENT = "10";
    /** 付款方式 - 代扣 */
    public static final String TYPE_COLLECT = "20";
    /** 付款方式 - 主动还款 */
    public static final String TYPE_REPAYMENT = "50";
    /** 付款方式 - 线下代付 */
    public static final String TYPE_OFFLINE_PAYMENT = "30";
    /** 付款方式 - 线下代扣 */
    public static final String TYPE_OFFLINE_COLLECT = "40";

    /** 业务场景 - 放款代付 */
    public static final String SCENES_LOANS = "10";
    /** 业务场景 - 奖励代付 */
    public static final String SCENES_PROFIT = "11";
    /** 业务场景 - 退还代付 */
    public static final String SCENES_REFUND = "12";
    /** 业务场景 - 还款代扣 */
    public static final String SCENES_REPAYMENT = "20";
    /** 业务场景 - 补扣代扣 */
    public static final String SCENES_DEDUCTION = "21";
}
