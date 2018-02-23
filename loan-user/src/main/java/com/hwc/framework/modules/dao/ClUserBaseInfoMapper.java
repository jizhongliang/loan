package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.framework.modules.model.ManagerUserModel;
import com.hwc.mybatis.core.Mapper;

public interface ClUserBaseInfoMapper extends Mapper<ClUserBaseInfo> {
    public ManagerUserModel getBaseModelByUserId(Long userId);
}