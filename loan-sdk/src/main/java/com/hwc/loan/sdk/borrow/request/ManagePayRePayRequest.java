package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManagePayRePayResponse;

public class ManagePayRePayRequest extends RequestBase<ManagePayRePayResponse> {

    public static final String METHOD = "/manage/pay/rePay";

    private Long id;

    public ManagePayRePayRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 