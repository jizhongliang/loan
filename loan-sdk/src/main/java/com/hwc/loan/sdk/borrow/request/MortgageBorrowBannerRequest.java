package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.MortgageBorrowBannerResponse;

public class MortgageBorrowBannerRequest extends RequestBase<MortgageBorrowBannerResponse> {

    public static final String METHOD = "/api/mortgage/borrow/banner";


    public MortgageBorrowBannerRequest() {
        super(METHOD);
    }

} 