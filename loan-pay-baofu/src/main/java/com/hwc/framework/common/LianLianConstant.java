package com.hwc.framework.common;

/**
 * Created by   on 2017/11/1.
 */
public class LianLianConstant {


    /**
     * 对账文件名称前缀 -  付款明细
     */
    public static final String CHECK_PREFIX_PAY = "FKMX_";

    /**
     * 对账文件名称前缀 -  交易明细
     */
    public static final String CHECK_PREFIX_CHARGE = "JYMX_";

    public static final int CHECK_DAY = -5;

    /**
     * API版本
     */
    public static final String API_VERSION = "1.0";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "RSA";

    /******* 银行卡账户 标识 *******/
    /**
     * 对公账户
     */
    public static final String COMPANY_CARD = "1";
    /**
     * 对私账户
     */
    public static final String PERSONAL_CARD = "0";

    /** 处理结果 */
    /**
     * 处理结果 - 成功
     */
    public static final String RESULT_SUCCESS = "SUCCESS";
    /**
     * 处理结果 - 失败
     */
    public static final String RESULT_FAILURE = "FAILURE";
    /**
     * 处理结果 - 处理中
     */
    public static final String RESULT_PROCESSING = "PROCESSING ";

    /**
     * 商户业务类型 - 虚拟商品
     */
    public static final String GOODS_VIRTUAL = "101001";

    /**
     * 支付方式 认证支付（借记卡）
     */
    public static final String PAY_TYPE_CERTIFIED = "D";

    /**
     * 交易成功
     */
    public static final String RESPONSE_SUCCESS_CODE = "0000";
    /**
     * 交易成功
     */
    public static final String RESPONSE_SUCCESS_VALUE = "交易成功";

    /**
     * 异步通知 响应使用
     */
    public static final String RESPONSE_CODE = "ret_code";

    public static final String RESPONSE_MSG = "ret_msg";


    /**
     * 状态 - 启用
     */
    public static final String STATE_ENABLE = "10";

    /**
     * 状态 - 禁用
     */
    public static final String STATE_DISABLE = "20";
    /**
     * 订单不存在
     */
    public static final String NOT_EXISTS_ORDER = "8901";

    /**
     * 交易成功
     */
    public static final String RESPONSE_ERROR_CODE = "2001";
}
