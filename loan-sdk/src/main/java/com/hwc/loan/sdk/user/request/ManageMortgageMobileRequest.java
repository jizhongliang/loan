package com.hwc.loan.sdk.user.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageDetailResponse;
import com.hwc.loan.sdk.user.response.ManageMortgageMobileResponse;


public class ManageMortgageMobileRequest extends RequestBase<ManageMortgageMobileResponse> {

    public static final String METHOD = "/manage/mortgage/mobile";


    private String phone;

    public ManageMortgageMobileRequest() {
        super(METHOD);
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	
   
} 