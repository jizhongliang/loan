/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserBaseInfo;

/**
 * 用户认证相关service
 * @author jinlilong
 * @version $Id: UserAuthService.java, v 0.1 2018年2月1日 上午9:56:18 jinlilong Exp $
 */
public interface UserAuthService {

    /**
     * 微信端用户实名认证
     * @param dUserBaseInfo
     * @return
     */
    public Response<String> authentication(DUserBaseInfo dUserBaseInfo);

}
