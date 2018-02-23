package com.hwc.framework.common;

/**
 * 系统变量定义说明
 *
 * @author
 */
public class BaoFuConstant {
    /**
     * 接入类型
     */
    public static  final  String PAY_CODE="baofu";
    public static final String BIZ_TYPE = "0000";
    public static final String RESP_SUCESS = "200";
    public static final String DATE_FORMART = "yyyyMMddHHmmss";
    public static final String PAY_SUCCESS_CODE = "0000";
    /**
     * 订单已支付成功，请勿重复支付
     */
    public static final String PAY_REPEAT_SUCCESS_CODE = "BF00114";

    /**
     * 系统内部 需要查询订单的常量
     */
    public static final Integer NEED_QUERY_ORDER_CODE = 9000;

    /**
     * 需要去调用接口去查询结果
     */
    /**
     * 系统异常，请联系宝付
     */
    public static final String BF00100 = "BF00100";
    /**
     * 系统繁忙，请稍后再试
     */
    public static final String BF00112 = "BF00112";
    /**
     * 交易结果未知，请稍后查询
     */
    public static final String BF00113 = "BF00113";
    /**
     * 交易处理中，请稍后查询
     */
    public static final String BF00115 = "BF00115";
    /**
     * 该交易有风险,订单处理中
     */

    public static final String BF00144 = "BF00144";
    /**
     * 交易超时，请稍后查询
     */
    public static final String BF00202 = "BF00202";


    public final static String DATA_TYPE_JSON = "json";

    public final static String DATA_TYPE_XML = "xml";
}
