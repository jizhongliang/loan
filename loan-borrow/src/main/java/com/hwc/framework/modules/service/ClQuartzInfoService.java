/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.ClQuartzInfo;

import java.util.List;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/05
 */
public interface ClQuartzInfoService extends Service<ClQuartzInfo> {

    List<ClQuartzInfo> getQuartzService();
}
