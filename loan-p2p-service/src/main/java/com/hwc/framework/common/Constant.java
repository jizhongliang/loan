package com.hwc.framework.common;

public class Constant {

    /**
     *
     */
    public static final String   SMS_MASTER_URL              = "http://www.dh3t.com";

    /**
     *
     */
    public static final String   SMS_SIGN                    = "【财位】";

    /**
     *
     */
    public static final String   SMS_SUB_CODE                = "";

    /**
     * 短信码有效期
     */
    public static final long     SMS_TIME_LIMIT              = 60 * 5;

    /**
     * 图片验证码有效期
     */
    public static final long     VERIFY_TIME_LIMIT           = 60 * 5;

    public static final String   LOAN_LIMIT               = "loan_limit";

    public static final String   RESPONSE_DATA_PAGE          = "page";

    public static final String   RESPONSE_CODE               = "code";

    public static final int      SUCCEED_CODE_VALUE          = 200;                  // 成功 插入 、删除 更新 修改

    public static final String   RESPONSE_CODE_MSG           = "msg";

    public static final int      FAIL_CODE_VALUE             = 400;                  // 失败 插入 、删除 更新 修改

    public static final String   OPERATION_SUCCESS           = "操作成功"; 

    public static final String   OPERATION_FAIL              = "操作失败";

    public static final int      FAIL_CODE_PWD               = 401;                  // 交易密码错误

    public static final int      PERM_CODE_VALUE             = 403;                  // 无权限访问

    public static final int      OTHER_CODE_VALUE            = 500;                  // 其他异常

    public static final int      NOSESSION_CODE_VALUE        = 800;                  // session失效

    public static final int      CLIENT_EXCEPTION_CODE_VALUE = 998;                  // 连接异常（除请求超时）

    public static final int      TIMEOUT_CODE_VALUE          = 999;                  // 请求超时

    public static final int      SUCCESS_CODE_BUT_NOTUSER    = 202;                  // 查询成功但是警用

    public static final String[] BORROW_STATE_ALREADY_LOAN   = { "30", "31" };       // 订单状态已放款或放款失败

    public static final String   BORROW_STATE_ALREADY_REPAY  = "40";                 //已还款

    public static final String   BORROW_REPAY_STATE_UNPAY    = "20";                 //	未还款

    public static final int      BREAK_FINE_TIME_LIMIT       = 180;                  //计算违约金借款天数界限

    public static final String   BREAK_FINE_MORE_THEN_TIME_LIMIT = "0.00";            //六个月以上，按照利息的20%

    public static final String   BREAK_FINE_LESS_THEN_TIME_LIMIT = "0.00";            //六个月以下，按照利息的15%

}
