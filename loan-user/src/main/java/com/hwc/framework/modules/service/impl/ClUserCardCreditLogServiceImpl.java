/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.ClUserCardCreditLogMapper;
import com.hwc.framework.modules.model.ClUserCardCreditLog;
import com.hwc.framework.modules.service.ClUserCardCreditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/22
 */
@Service
public class ClUserCardCreditLogServiceImpl extends AbstractService<ClUserCardCreditLogMapper, ClUserCardCreditLog> implements ClUserCardCreditLogService {
    private static final Logger logger = LoggerFactory.getLogger(ClUserCardCreditLogServiceImpl.class);

    @Autowired
    private ArcSysConfigService arcSysConfigService;

    @Override
    public Response isCanCredit(Long userId) {
        int daysMostTimes = FsUtils.i(arcSysConfigService.getConfigDefault("idCardCredit_day_most_times",""));
        if(!FsUtils.strsEmpty(daysMostTimes, userId)){
            ClUserCardCreditLog clUserCardCreditLog = new ClUserCardCreditLog();
            clUserCardCreditLog.setUserId(userId);
            int count = this.mapper.selectCount(clUserCardCreditLog);
            if(count >= daysMostTimes){
                logger.error("用户"+userId+"今天请求人脸识别次数超过"+daysMostTimes+",请明日再来认证");
                return  Response.fail("人脸识别次数超过"+daysMostTimes+",请明日再来认证");
            }
            return  Response.success();
        }else {
            return  Response.fail("参数错误");
        }
    }
}
