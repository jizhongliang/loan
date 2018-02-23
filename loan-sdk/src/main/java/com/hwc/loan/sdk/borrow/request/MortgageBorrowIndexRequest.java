package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.MortgageBorrowIndexResponse;

public class MortgageBorrowIndexRequest extends RequestBase<MortgageBorrowIndexResponse> {

    public static final String METHOD = "/api/mortgage/borrow/index";

    private Long id;

    public MortgageBorrowIndexRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 