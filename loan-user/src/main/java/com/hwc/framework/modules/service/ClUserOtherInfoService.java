package com.hwc.framework.modules.service;

import com.hwc.framework.modules.domain.DUserOtherInfo;
import com.hwc.framework.modules.model.ClUserOtherInfo;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClUserOtherInfoService extends Service<ClUserOtherInfo> {

    public boolean updateUserOtherInfo(DUserOtherInfo dUserOtherInfo);

    public DUserOtherInfo getUserOtherInfoByOpenid(String openid);

    public DUserOtherInfo getUserOtherInfoByUserId(Long userId);

}
