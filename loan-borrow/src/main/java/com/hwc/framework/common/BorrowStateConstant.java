package com.hwc.framework.common;

/**
 * Created by   on 2017/11/6.
 */
public class BorrowStateConstant {

    /**
     * 申请成功待审核
     */
    public static final String STATE_PRE = "10";

    /**
     * 待初审核
     */
    public static final String STATE_TRIAL = "15";

    /**
     * 初审核不通过
     */
    public static final String STATE_TRIAL_REFUSED = "16";
    /**
     * 自动审核通过
     */
    public static final String STATE_AUTO_PASS = "20";
    /**
     * 自动审核不通过
     */
    public static final String STATE_AUTO_REFUSED = "21";
    /**
     * 自动审核未决待人工复审
     */
    public static final String STATE_NEED_REVIEW = "22";
    /**
     * 人工复审通过
     */
    public static final String STATE_PASS = "26";
    /**
     * 人工复审不通过
     */
    public static final String STATE_REFUSED = "27";
    /**
     * 放款成功
     */
    public static final String STATE_REPAY = "30";
    /**
     * 放款失败
     */
    public static final String STATE_REPAY_FAIL = "31";
    /**
     * 还款成功
     */
    public static final String STATE_FINISH = "40";
    /**
     * 还款成功-金额减免
     */
    public static final String STATE_REMISSION_FINISH = "41";
    /**
     * 逾期
     */
    public static final String STATE_DELAY = "50";
    /**
     * 坏账
     */
    public static final String STATE_BAD = "90";
}
