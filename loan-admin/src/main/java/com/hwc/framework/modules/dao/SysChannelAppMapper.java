package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.SysChannelApp;
import com.hwc.framework.modules.model.SysMenu;
import com.hwc.mybatis.core.Mapper;

import java.util.List;

public interface SysChannelAppMapper extends Mapper<SysChannelApp> {
    public SysChannelApp getChannelAppByType(String type);
}