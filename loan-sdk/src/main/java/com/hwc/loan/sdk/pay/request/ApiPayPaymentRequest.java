package com.hwc.loan.sdk.pay.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.pay.response.ApiPayPaymentResponse;
import lombok.Data;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class ApiPayPaymentRequest extends RequestBase<ApiPayPaymentResponse> {
    public static final String METHOD = "/api/pay/payment";

    public ApiPayPaymentRequest() {
        super(METHOD);
    }

    /**
     * 银行卡号
     */
    private String bankCardNo;
    /**
     * 请求订单号
     */
    private String orderNo;
    /**
     * 支付金额
     */
    private Double amount;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 支付金额
     */
    private Double realAmount;
    /**
     *订单Id
     */
    private Long borrowId;
    /**
     *确认号  确认放款才有这个码
     */
    private String confim_code;
    /**
     *时间
     */
    private String dt_order;
    /**
     *银行卡协议号
     */
    private String agree_no;


}
