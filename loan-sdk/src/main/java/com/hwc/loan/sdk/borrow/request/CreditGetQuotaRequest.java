package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.CreditGetQuotaResponse;

public class CreditGetQuotaRequest extends RequestBase<CreditGetQuotaResponse> {

    public static final String METHOD = "/api/credit/getQuota";


    public CreditGetQuotaRequest() {
        super(METHOD);
    }

} 