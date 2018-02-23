package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BorrowRepayBorrowListResponse;

public class BorrowRepayBorrowListRequest extends RequestBase<BorrowRepayBorrowListResponse> {

    public static final String METHOD = "/api/borrow/repay/borrowList";

    private Long id;

    public BorrowRepayBorrowListRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 