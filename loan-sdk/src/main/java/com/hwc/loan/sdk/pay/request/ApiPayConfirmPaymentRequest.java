package com.hwc.loan.sdk.pay.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.pay.response.ApiPayConfirmPaymentResponse;
import lombok.Data;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class ApiPayConfirmPaymentRequest extends RequestBase<ApiPayConfirmPaymentResponse> {
    public static final String METHOD = "/api/pay/confirmPayment";

    public ApiPayConfirmPaymentRequest() {
        super(METHOD);
    }
}
