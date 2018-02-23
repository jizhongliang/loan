package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.base.sdk.core.ResponseBase;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserBorrowRateRequest extends RequestBase<ResponseBase> {
    public static final String METHOD = "/api/user/getBorrowRate";

    public UserBorrowRateRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private String loginName;
    public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	private Long userId; 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

 

}
