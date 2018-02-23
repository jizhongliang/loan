package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.service.ClBankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/bank/card")
public class ClBankCardController {
    private static final Logger logger = LoggerFactory.getLogger(ClBankCardController.class);

    @Autowired
    private ClBankCardService clBankCardService;

    @PostMapping("/list")
    public Response list(@RequestBody IdRequest<Long> request) {
        Response response = clBankCardService.getBankCardList(request.getId());
        return response;
    }


}
