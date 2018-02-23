package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.framework.modules.model.ClUserBlackLog;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClUserBlackLogService extends Service<ClUserBlackLog> {
    public void insert(Long userId,String state);

}
