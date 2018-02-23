package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BorrowListResponse;

public class BorrowListRequest extends RequestBase<BorrowListResponse> {

    public static final String METHOD = "/api/borrow/list";

    private Long id;

    public BorrowListRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 