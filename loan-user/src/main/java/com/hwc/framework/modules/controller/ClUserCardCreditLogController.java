/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClUserCardCreditLog;
import com.hwc.framework.modules.service.ClUserCardCreditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/11/22
 */
@RestController
@RequestMapping("/api/user/card/credit/log")
public class ClUserCardCreditLogController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserCardCreditLogController.class);

    @Autowired
    private ClUserCardCreditLogService clUserCardCreditLogService;



}
