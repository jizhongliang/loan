/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClUserAuthData;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/27
 */
@RestController
@RequestMapping("/api/cl/user/auth/data")
public class ClUserAuthDataController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserAuthDataController.class);

    @Autowired
    private ClUserAuthDataService clUserAuthDataService;


}
