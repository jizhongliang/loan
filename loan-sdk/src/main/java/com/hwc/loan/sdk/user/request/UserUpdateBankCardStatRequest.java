package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserUpdateBankCardStateResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserUpdateBankCardStatRequest extends RequestBase<UserUpdateBankCardStateResponse> {
    public static final String METHOD = "/api/user/auth/updateBankCardState";

    public UserUpdateBankCardStatRequest() {
        super(METHOD);
    }

    /**
     * 主键id
     */
    private Long userId;

    private String bankCardState; // 10未认证/未完善，20认证中/完善中，30已认证/已完善

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBankCardState() {
        return bankCardState;
    }

    public void setBankCardState(String bankCardState) {
        this.bankCardState = bankCardState;
    }
}
