package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UpdateUserBaseInfoCWResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UpdateUserBaseinfoCWRequest extends RequestBase<UpdateUserBaseInfoCWResponse> {
    public static final String METHOD = "/api/user/baseInfo/updateUserBaseinfoCw";

    public UpdateUserBaseinfoCWRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private Long id;

    private String realName;
    
    private String idNo;
    
    private String phone;
    
    private Long userId;
    
    

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    

}
