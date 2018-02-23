package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.user.response.UserValidateTradePwdResponse;

/**
 * Created by  on 2017/10/9.
 */
public class UserValidateTradePwdRequest extends RequestBase<UserValidateTradePwdResponse> {
    public static final String METHOD = "/api/user/validateTradePwd";

    public UserValidateTradePwdRequest() {
        super(METHOD);
    }

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 交易密码
     */
    private String tradePwd;

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
