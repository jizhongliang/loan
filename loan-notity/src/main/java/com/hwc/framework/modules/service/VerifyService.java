package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DVerify;

/**
 * 2017/10/18.
 */
public interface VerifyService {

    /**
     * 获取图片验证码
     * @return
     */
    public Response getVerifyCode(DVerify dVerify);

    /**
     * 校对图片验证码
     * @return
     */
    public Response verifyCode(DVerify dVerify);
}
