package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.SysChannelApp;
import com.hwc.mybatis.core.Service;


/**
 * 2017/09/22.
 */
public interface SysChannelAppService extends Service<SysChannelApp> {
    public Response getChannelAppByType(String type);
}
