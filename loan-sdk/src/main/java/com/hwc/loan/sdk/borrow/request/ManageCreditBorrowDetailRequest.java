package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageCreditBorrowDetailResponse;

public class ManageCreditBorrowDetailRequest extends RequestBase<ManageCreditBorrowDetailResponse> {

    public static final String METHOD = "/manage/credit/borrow/detail";

    private Long id;

    public ManageCreditBorrowDetailRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 