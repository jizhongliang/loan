package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.CreditBorrowIndexResponse;

public class CreditBorrowIndexRequest extends RequestBase<CreditBorrowIndexResponse> {

    public static final String METHOD = "/api/credit/borrow/index";

    private Long id;

    public CreditBorrowIndexRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 