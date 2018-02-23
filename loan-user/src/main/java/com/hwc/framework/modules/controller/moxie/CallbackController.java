package com.hwc.framework.modules.controller.moxie;

import com.hwc.framework.modules.third.business.carrier.sevice.CallbackService;
import com.hwc.framework.modules.third.callback.dto.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@RestController
@RequestMapping(value = "/api/v1")
public class CallbackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackController.class);


    @Autowired
    private CallbackService callbackService;

    /**
     * 回调接口, moxie通过此endpoint通知账单更新和任务状态更新
     * 存入本地的mongo 
     */
    @RequestMapping(value = "/recall", method = RequestMethod.GET)
    public ResultMsg notifyUpdateBill(@RequestParam(value = "taskId") String taskId, @RequestParam("event") String event, ServletRequest request, ServletResponse response) {
    	
    	LOGGER.info("start recall taskId:"+taskId);
        ResultMsg msg = callbackService.recall(taskId,event);

        LOGGER.info("end recall taskId:"+taskId+",msg="+msg.getMessage());

        return msg;
    	
    }

}
