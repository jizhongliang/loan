package com.hwc.loan.sdk.sms.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.sms.response.SendSmsManageResponse;
import com.hwc.loan.sdk.sms.response.SmsVerifyCodeResponse;


public class SendSmsManageRequest extends RequestBase<SendSmsManageResponse> {

    public static final String METHOD = "/api/sms/sendCodeManage";

    public SendSmsManageRequest() {
        super(METHOD);
    }

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 短信类型
     */
    private String type;

    /**
     * 验证码
     */
    private String code;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
