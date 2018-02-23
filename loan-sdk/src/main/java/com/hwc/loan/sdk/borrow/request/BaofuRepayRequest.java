package com.hwc.loan.sdk.borrow.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.BaofuRepayResponse;


public class BaofuRepayRequest extends RequestBase<BaofuRepayResponse> {

	public static final String METHOD = "/api/pay/baofuRepay";

	private Long repayId;
	
	private int type;

	public Long getRepayId() {
		return repayId;
	}



	public void setRepayId(Long repayId) {
		this.repayId = repayId;
	}



	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}



	public BaofuRepayRequest() {
		super(METHOD);
	}

}