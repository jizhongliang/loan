/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.ClQuartzLogMapper;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.service.ClQuartzLogService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/05
 */
@Service
public class ClQuartzLogServiceImpl extends AbstractService<ClQuartzLogMapper, ClQuartzLog> implements ClQuartzLogService {
    private static final Logger logger = LoggerFactory.getLogger(ClQuartzLogServiceImpl.class);


}
