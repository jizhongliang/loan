package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.base.api.ResponseCode;
import com.hwc.framework.modules.dao.SysChannelAppMapper;
import com.hwc.framework.modules.domain.DSysChannelApp;
import com.hwc.framework.modules.domain.DSysRole;
import com.hwc.framework.modules.model.SysChannelApp;
import com.hwc.framework.modules.model.SysRole;
import com.hwc.framework.modules.service.SysChannelAppService;
import com.hwc.mybatis.core.AbstractService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * 2017/09/22.
 */
@Service
public class SysChannelAppServiceImpl extends AbstractService<SysChannelAppMapper, SysChannelApp> implements SysChannelAppService {


    @Override
    public Response getChannelAppByType(String type) {
        if (FsUtils.strsEmpty(type)){
            return Response.fail(ResponseCode.MISSING_PARAMETER);
        }
       SysChannelApp sysChannelApp = this.mapper.getChannelAppByType(type);
        if(sysChannelApp != null){
            DSysChannelApp dSysChannelApp = new DSysChannelApp();
            covertToDMenu(dSysChannelApp,sysChannelApp);
            return Response.success(dSysChannelApp);
        }else{
            return Response.fail(ResponseCode.OBJECT_IS_EMPTY);
        }

    }

    public void covertToDMenu (DSysChannelApp dSysChannelApp, SysChannelApp sysChannelApp){
        try {
            BeanUtils.copyProperties(dSysChannelApp,sysChannelApp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
