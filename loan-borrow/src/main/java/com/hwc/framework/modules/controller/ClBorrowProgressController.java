package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.framework.modules.service.ClBorrowProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/borrow/progress")
public class ClBorrowProgressController {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowProgressController.class);

//    @Autowired
//    private ClBorrowProgressService clBorrowProgressService;
//
//    @PostMapping("/example")
//    public Response example(@RequestParam Integer id) {
//        ClBorrowProgress clBorrowProgress = clBorrowProgressService.findById(id);
//        return Response.success(clBorrowProgress);
//    }

}
