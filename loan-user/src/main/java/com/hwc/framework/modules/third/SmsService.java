package com.hwc.framework.modules.third;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;

/**
 * Created by  on 2017/12/4.
 */
public interface SmsService {
    Response verifyCode(DSms dSms);

    Response sendSms(DSms dSms);

    Response sendSmsWx(DSms dSms);

    Response sendSmsManage(DSms dSms);

}
