package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BorrowRepayLogBorrowRepayListResponse;

public class BorrowRepayLogBorrowRepayListRequest extends RequestBase<BorrowRepayLogBorrowRepayListResponse> {

    public static final String METHOD = "/api/borrow/repay/log/borrowRepayList";

    private Long id;

    public BorrowRepayLogBorrowRepayListRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 