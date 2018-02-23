package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClOpinion;
import com.hwc.framework.modules.service.ClOpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/30.
 */
@RestController
@RequestMapping("/api/opinion")
public class ClOpinionController {
    private static final Logger logger = LoggerFactory.getLogger(ClOpinionController.class);

    @Autowired
    private ClOpinionService clOpinionService;



}
