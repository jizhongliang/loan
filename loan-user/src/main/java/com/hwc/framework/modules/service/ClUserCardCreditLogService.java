/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.ClUserCardCreditLog;


/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/22
 */
public interface ClUserCardCreditLogService extends Service<ClUserCardCreditLog> {
    /**
     * 判断用户是否可以人脸识别认证
     * @param userId
     * @return
     */
    Response isCanCredit(Long userId);
}
