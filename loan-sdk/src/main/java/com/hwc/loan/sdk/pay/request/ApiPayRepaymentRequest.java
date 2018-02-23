package com.hwc.loan.sdk.pay.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.pay.response.ApiPayRepaymentReponse;
import lombok.Data;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class ApiPayRepaymentRequest extends RequestBase<ApiPayRepaymentReponse> {
    public static final String METHOD = "/api/pay/repayment";

    public ApiPayRepaymentRequest() {
        super(METHOD);
    }
}
