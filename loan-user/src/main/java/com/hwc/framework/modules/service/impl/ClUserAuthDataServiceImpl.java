/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.ClUserAuthDataMapper;
import com.hwc.framework.modules.model.ClUserAuthData;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/27
 */
@Service
public class ClUserAuthDataServiceImpl extends AbstractService<ClUserAuthDataMapper, ClUserAuthData> implements ClUserAuthDataService {
    private static final Logger logger = LoggerFactory.getLogger(ClUserAuthDataServiceImpl.class);


    @Override
    public DUserAuthData getUserAuthData(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return null;
        }
        ClUserAuthData select = new ClUserAuthData();
        select.setUserId(userId);
        ClUserAuthData clUserAuthData = this.mapper.selectOne(select);
        if (clUserAuthData != null){
            return this.convertToDUserAuthData(clUserAuthData);
        }
        return null;
    }

    @Override
    public boolean updateUserAuthDataForPhone(DUserAuthData dUserAuthData) {
        if (FsUtils.strsEmpty(dUserAuthData.getUserId())){
            return false;
        }
        if (FsUtils.strsEmpty(dUserAuthData.getPhoneReport()) && FsUtils.strsEmpty(dUserAuthData.getPhoneData())){
            return false;
        }
        ClUserAuthData select = new ClUserAuthData();
        select.setUserId(dUserAuthData.getUserId());
        ClUserAuthData clUserAuthData = this.mapper.selectOne(select);

        ClUserAuthData update = new ClUserAuthData();
        update.setUserId(dUserAuthData.getUserId());
        if (!FsUtils.strsEmpty(dUserAuthData.getPhoneData())){
            update.setPhoneData(dUserAuthData.getPhoneData());
        }
        if (!FsUtils.strsEmpty(dUserAuthData.getPhoneReport())){
            update.setPhoneReport(dUserAuthData.getPhoneReport());
        }
        update.setCreated(new Date());
        if (clUserAuthData == null){
            this.insert(update);
        }else {
            update.setId(clUserAuthData.getId());
            this.update(update);
        }
        return true;

    }

    @Override
    public boolean updateUserAuthDataForzhengxin(DUserAuthData dUserAuthData) {
        if (FsUtils.strsEmpty(dUserAuthData.getUserId())){
            return false;
        }
        if (FsUtils.strsEmpty(dUserAuthData.getZhengxinReport()) && FsUtils.strsEmpty(dUserAuthData.getZhengxinData())){
            return false;
        }
        ClUserAuthData select = new ClUserAuthData();
        select.setUserId(dUserAuthData.getUserId());
        ClUserAuthData clUserAuthData = this.mapper.selectOne(select);

        ClUserAuthData update = new ClUserAuthData();
        update.setUserId(dUserAuthData.getUserId());
        if (!FsUtils.strsEmpty(dUserAuthData.getZhengxinData())){
            update.setZhengxinData(dUserAuthData.getZhengxinData());
        }
        if (!FsUtils.strsEmpty(dUserAuthData.getZhengxinReport())){
            update.setZhengxinReport(dUserAuthData.getZhengxinReport());
        }
        update.setCreated(new Date());
        if (clUserAuthData == null){
            this.insert(update);
        }else {
            update.setId(clUserAuthData.getId());
            this.update(update);
        }
        return true;
    }

    private DUserAuthData convertToDUserAuthData(ClUserAuthData clUserAuthData){
        DUserAuthData dUserAuthData = new DUserAuthData();
        dUserAuthData.setId(clUserAuthData.getId());
        dUserAuthData.setUserId(clUserAuthData.getUserId());
        dUserAuthData.setPhoneData(clUserAuthData.getPhoneData());
        dUserAuthData.setPhoneReport(clUserAuthData.getPhoneReport());
        dUserAuthData.setZhengxinData(clUserAuthData.getZhengxinData());
        dUserAuthData.setZhengxinReport(clUserAuthData.getZhengxinReport());
        return dUserAuthData;
    }
}
