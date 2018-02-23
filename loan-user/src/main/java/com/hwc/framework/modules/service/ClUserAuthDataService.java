/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.ClUserAuthData;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/27
 */
public interface ClUserAuthDataService extends Service<ClUserAuthData> {

    public DUserAuthData getUserAuthData(Long userId);

    public boolean updateUserAuthDataForPhone(DUserAuthData dUserAuthData);

    public boolean updateUserAuthDataForzhengxin(DUserAuthData dUserAuthData);
}
