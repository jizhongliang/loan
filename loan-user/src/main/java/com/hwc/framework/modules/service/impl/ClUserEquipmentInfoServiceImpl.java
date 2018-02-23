/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsDateUtils;
import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.domain.DEquipment;
import com.hwc.framework.modules.domain.DUserEquipment;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.framework.modules.dao.ClUserEquipmentInfoMapper;
import com.hwc.framework.modules.model.ClUserEquipmentInfo;
import com.hwc.framework.modules.service.ClUserEquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 类说明
 *
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/23
 */
@Service
public class ClUserEquipmentInfoServiceImpl extends AbstractService<ClUserEquipmentInfoMapper, ClUserEquipmentInfo> implements ClUserEquipmentInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ClUserEquipmentInfoServiceImpl.class);

    @Autowired
    private IHwcCache cache;


    @Override
    public Response equipmentRegister(DUserEquipment equipment) {
        ClUserEquipmentInfo info = getEquipment(equipment.getPhoneMark(), equipment.getUserId());
        DEquipment equipment1 = new DEquipment();
        equipment1.setTimestamp(System.currentTimeMillis());
        if (FsUtils.strsEmpty(info)) {
            info = new ClUserEquipmentInfo();
            info.setMac(equipment.getMac());
            info.setMobilecarrier(equipment.getMobilecarrier());
            info.setOperatingSystem(equipment.getOperatingSystem());
            info.setPhoneBrand(equipment.getPhoneBrand());
            info.setPhoneMark(equipment.getPhoneMark());
            info.setPhoneName(equipment.getPhoneName());
            info.setPhoneType(equipment.getPhoneType());
            info.setScreen(equipment.getScreen());
            info.setUserId(equipment.getUserId());
            info.setSystemVersions(equipment.getSystemVersions());
            info.setVersionCode(equipment.getVersionCode());
            info.setVersionName(equipment.getVersionName());
            info.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            insert(info);
            equipment1.setFinger(info.getUuid());
            return Response.success(equipment1);
        } else {
            info.setMac(equipment.getMac());
            info.setMobilecarrier(equipment.getMobilecarrier());
            info.setOperatingSystem(equipment.getOperatingSystem());
            info.setPhoneBrand(equipment.getPhoneBrand());
            info.setPhoneName(equipment.getPhoneName());
            info.setPhoneType(equipment.getPhoneType());
            info.setScreen(equipment.getScreen());
            info.setSystemVersions(equipment.getSystemVersions());
            info.setVersionCode(equipment.getVersionCode());
            info.setVersionName(equipment.getVersionName());
            equipment1.setFinger(info.getUuid());
        }
        return Response.success(equipment1);
    }

    public DEquipment bindEq(String uuid, Long userId) {
        DEquipment equipment1 = new DEquipment();
        equipment1.setTimestamp(System.currentTimeMillis());
        ClUserEquipmentInfo info = getEqInfoByUser(uuid, userId);
        if (FsUtils.strsEmpty(info)) {
            List<ClUserEquipmentInfo> eqs = getEqInfo(uuid);
            if (FsUtils.strsEmpty(eqs) && eqs.isEmpty()) {
                info = eqs.get(0);
                info.setUserId(userId);
                info.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                equipment1.setFinger(info.getUuid());
                return equipment1;
            } else {
                throw new ServiceException("该设备未注册");
            }
        } else {
            equipment1.setFinger(info.getUuid());
        }

        return equipment1;
    }

    private ClUserEquipmentInfo getEqInfoByUser(String uuid, Long userId) {
        ClUserEquipmentInfo info = new ClUserEquipmentInfo();
        info.setUuid(uuid);
        info.setUserId(userId);
        List<ClUserEquipmentInfo> infos = mapper.select(info);
        if (FsUtils.strsNotEmpty(infos) && infos.isEmpty()) {
            return infos.get(0);
        }
        return null;
    }

    @Override
    public List<ClUserEquipmentInfo> getEqInfo(String uuid) {
        ClUserEquipmentInfo info = new ClUserEquipmentInfo();
        info.setUuid(uuid);
        List<ClUserEquipmentInfo> infos = mapper.select(info);
        return infos;
    }

    @Override
    public List<ClUserEquipmentInfo> getEqInfoByPhoneMark(String phone_mark) {
        ClUserEquipmentInfo info = new ClUserEquipmentInfo();
        info.setPhoneMark(phone_mark);
        List<ClUserEquipmentInfo> infos = mapper.select(info);
        return infos;
    }

    private ClUserEquipmentInfo getEquipment(String imei, Long userId) {
        String key = "eEquipment:imei:" + imei + ":userId:" + userId;
        if (cache.exists(key)) {
            return cache.get(key, ClUserEquipmentInfo.class);
        }

        Condition condition = new Condition(ClUserEquipmentInfo.class);
        Example.Criteria c = condition.createCriteria();
        c.andEqualTo("phoneMark", imei);
        c.andEqualTo("userId", userId);
        List<ClUserEquipmentInfo> infos = this.mapper.selectByCondition(condition);
        if (FsUtils.strsNotEmpty(infos) && !infos.isEmpty()) {
            Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 7));
            cache.set(key, expire, infos.get(0));
            return infos.get(0);
        } else
            return null;
    }

}
