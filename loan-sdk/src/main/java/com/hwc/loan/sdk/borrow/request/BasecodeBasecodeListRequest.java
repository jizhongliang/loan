package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BasecodeBasecodeListResponse;

public class BasecodeBasecodeListRequest extends RequestBase<BasecodeBasecodeListResponse> {

    public static final String METHOD = "/api/basecode/basecodeList";


    public BasecodeBasecodeListRequest() {
        super(METHOD);
    }

} 