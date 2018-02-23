package com.hwc.framework.modules.domain.response;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSysChannelApp;
import com.hwc.framework.modules.domain.DSysMenu;
import com.hwc.framework.modules.domain.DSysUser;

import java.io.Serializable;
import java.util.List;

public class SysChannelAppResponse extends Response implements Serializable {
    private DSysChannelApp dSysChannelApp;

    public DSysChannelApp getdSysChannelApp() {
        return dSysChannelApp;
    }

    public void setdSysChannelApp(DSysChannelApp dSysChannelApp) {
        this.dSysChannelApp = dSysChannelApp;
    }
}
