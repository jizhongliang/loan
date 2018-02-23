package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;

/**
 * 2017/10/18.
 */
public interface SmsService{

    /**
     * 发送短信
     * @return
     */
    public Response sendSms(DSms dSms);

    /**
     * 验证 短信码
     * @return
     */
    public Response verifyCode(DSms dSms);


}
