/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserEquipment;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.ClUserEquipmentInfo;

import java.util.List;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/23
 */
public interface ClUserEquipmentInfoService extends Service<ClUserEquipmentInfo> {

    Response equipmentRegister(DUserEquipment equipment);

    List<ClUserEquipmentInfo> getEqInfo(String uuid);

    List<ClUserEquipmentInfo> getEqInfoByPhoneMark(String phone_mark);
}
