package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BankCardGetBankCardInfoResponse;

public class BankCardGetBankCardInfoRequest extends RequestBase<BankCardGetBankCardInfoResponse> {

    public static final String METHOD = "/api/bank/card/getBankCardInfo";

    private Long id;

    public BankCardGetBankCardInfoRequest() {
        super(METHOD);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

} 