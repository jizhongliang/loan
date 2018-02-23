package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserUpdateBankCardStateResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserUpdateStatRequest extends RequestBase<UserUpdateBankCardStateResponse> {
    public static final String METHOD = "/api/user/auth/updateUserState";

    public UserUpdateStatRequest() {
        super(METHOD);
    }

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户标识
     */
    private Long userId;

    /**
     * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String idState;

    /**
     * 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String contactState;

    /**
     * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String bankCardState;

    /**
     * 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String workInfoState;

    /**
     * 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String otherInfoState;

    /**
     * 人行征信，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String creditState;

    /**
     * 运营商，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String phoneState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
	}

	public String getContactState() {
		return contactState;
	}

	public void setContactState(String contactState) {
		this.contactState = contactState;
	}

	public String getBankCardState() {
		return bankCardState;
	}

	public void setBankCardState(String bankCardState) {
		this.bankCardState = bankCardState;
	}

	public String getWorkInfoState() {
		return workInfoState;
	}

	public void setWorkInfoState(String workInfoState) {
		this.workInfoState = workInfoState;
	}

	public String getOtherInfoState() {
		return otherInfoState;
	}

	public void setOtherInfoState(String otherInfoState) {
		this.otherInfoState = otherInfoState;
	}

	public String getCreditState() {
		return creditState;
	}

	public void setCreditState(String creditState) {
		this.creditState = creditState;
	}

	public String getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(String phoneState) {
		this.phoneState = phoneState;
	}
    
     
}
