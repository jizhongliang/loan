package com.hwc.framework.common;

/**
 * Created by   on 2017/10/30.
 */
public class ClMortgageOrderState {
    /**
     * 待审核
     */
    public static final String STATE_PRE = "10";

    /**
     * 初审核通过
     */
    public static final String STATE_TRIAL = "20";


    /**
     * 复审通过
     */
    public static final String STATE_V_PASS = "30";
    /**
     * 终审通过
     */
    public static final String STATE_PASS = "40";
    /**
     * 已提现
     */
    public static final String STATE_DRAWING = "50";
    /**
     * 审核拒绝
     */
    public static final String STATE_REFUSED = "60";

    /**
     * 冻结
     */
    public static final String STATE_FROZEN = "70";

}
