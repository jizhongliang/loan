/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DEquipment;
import com.hwc.framework.modules.domain.DUserEquipment;
import com.hwc.framework.modules.model.ClUserEquipmentInfo;
import com.hwc.framework.modules.service.ClUserEquipmentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类说明
 *
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/23
 */
@RestController
@RequestMapping("/api/user/equipment")
public class ClUserEquipmentInfoController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserEquipmentInfoController.class);

    @Autowired
    private ClUserEquipmentInfoService clUserEquipmentInfoService;

    @PostMapping("/register")
    public Response<DEquipment> example(@RequestBody DUserEquipment equipment) {
        return clUserEquipmentInfoService.equipmentRegister(equipment);

    }

}
