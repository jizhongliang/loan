package com.hwc.framework.modules.controller;

import cn.freesoft.utils.HttpUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.framework.modules.domain.LianlianResponse;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.PaymentBean;
import com.hwc.framework.modules.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by   on 2017/11/2.
 */
@RestController
@RequestMapping("/api/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/baofu/paymentNotify")
    public String paymentNotify(HttpServletRequest request, HttpServletResponse response) {
        return payService.loanCallback(request);
    }

    public String paymentQueryNotify(@RequestBody IdRequest<String> request) {
        return "";
    }
}
