package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserBaseInfoByPhoneResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserBaseInfoByPhoneRequest extends RequestBase<UserBaseInfoByPhoneResponse> {
    public static final String METHOD = "/api/user/baseInfo/byphone";

    public UserBaseInfoByPhoneRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    

}
