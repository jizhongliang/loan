/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.ClQuartzInfoMapper;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.service.ClQuartzInfoService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 类说明
 *
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/05
 */
@Service
public class ClQuartzInfoServiceImpl extends AbstractService<ClQuartzInfoMapper, ClQuartzInfo> implements ClQuartzInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ClQuartzInfoServiceImpl.class);

    @Override
    public List<ClQuartzInfo> getQuartzService() {
        ClQuartzInfo info = new ClQuartzInfo();
        info.setState("10");
        return this.mapper.select(info);
    }
}
