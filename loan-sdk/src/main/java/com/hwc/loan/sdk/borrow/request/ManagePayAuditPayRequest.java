package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManagePayAuditPayResponse;

public class ManagePayAuditPayRequest extends RequestBase<ManagePayAuditPayResponse> {

    public static final String METHOD = "/manage/pay/auditPay";

    private Long id;

    public ManagePayAuditPayRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 