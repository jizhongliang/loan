package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.PayBaofuPaymentNotifyResponse;

public class PayBaofuPaymentNotifyRequest extends RequestBase<PayBaofuPaymentNotifyResponse> {

    public static final String METHOD = "/api/pay/baofu/paymentNotify";


    public PayBaofuPaymentNotifyRequest() {
        super(METHOD);
    }

} 