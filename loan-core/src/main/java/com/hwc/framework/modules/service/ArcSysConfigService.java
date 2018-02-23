package com.hwc.framework.modules.service;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.ConfigQueryRequest;
import com.hwc.framework.modules.domain.HelpBean;
import com.hwc.framework.modules.domain.OssConfig;
import com.hwc.framework.modules.domain.SysConfig;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.mybatis.core.Service;

/**
 * 2017/11/01.
 */
public interface ArcSysConfigService extends Service<ArcSysConfig> {
    /**
     * 获取配置参数
     *
     * @param code
     * @return
     */
    String getConfigDefault(String code, String def);

    Response updateConfig(SysConfig config);

    Response insertConfig(SysConfig config);

    Response deleteConfig(Long id);

    Response<OssConfig> getOssConfig(IdRequest<String> request);

    Response getSysConfig(ConfigQueryRequest request);

    Response getAppH5(HelpBean app);

    ArcSysConfig getSysConfigByCode(String code);
}
