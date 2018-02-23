package com.hwc.framework.modules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/31.
 */
@RestController
@RequestMapping("/api/pay/log")
public class ClPayLogController {
    private static final Logger logger = LoggerFactory.getLogger(ClPayLogController.class);

//    @Autowired
//    private ClPayLogService clPayLogService;

//    @PostMapping("/example")
//    public Response example(@RequestParam Integer id) {
//        ClPayLog clPayLog = clPayLogService.findById(id);
//        return Response.success(clPayLog);
//    }

}
