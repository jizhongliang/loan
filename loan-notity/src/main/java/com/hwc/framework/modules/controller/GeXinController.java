package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DSms;
import com.hwc.framework.modules.domain.DSmsTpl; 
import com.hwc.framework.modules.service.SmsService;
import com.hwc.framework.modules.service.SmsTplService;
import com.hwc.framework.modules.third.BorrowGenXinNotifyService;
import com.hwc.framework.modules.third.GeXinMessageService;
import com.hwc.framework.modules.third.MortgageGenXinNotifyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2017/10/18.
 */
@RestController
@RequestMapping("/api/gexin")
public class GeXinController {
    private static final Logger logger = LoggerFactory.getLogger(GeXinController.class);

    @Autowired
    private BorrowGenXinNotifyService borrowGenXinNotifyService;
    
    
    /**
     * 逾期通知
     * @param jsonObject  @RequestParam("username") String username,  
     */
    @RequestMapping("/overdue")
    public String borrowOverdueNotify(@RequestParam("loan") String loan,@RequestParam("repayData") String repay_data,
    		@RequestParam("mobile") String mobile,@RequestParam("money") String money) {
    	String jsonObject = "{\"loan\":\""+loan+"\",\"repay_data\":\""+repay_data+"\",\"mobile\":\""+mobile+"\",\"money\":\""+money+"\"}";
    	JSONObject json = JSONObject.parseObject(jsonObject); 
        borrowGenXinNotifyService.borrowOverdueNotify(json);
		return "进来了";
    }
    /**
     * 快到期通知
     * @param jsonObject
     */
    @RequestMapping("/repayExpire")
    public String repayExpire(@RequestParam("loan") String loan,@RequestParam("repayData") String repay_data,@RequestParam("mobile") String mobile) {
    	String jsonObject = "{\"loan\":\""+loan+"\",\"repay_data\":\""+repay_data+"\",\"mobile\":\""+mobile+"\"}";
    	JSONObject json = JSONObject.parseObject(jsonObject); 
    	borrowGenXinNotifyService.repayExpireNotify(json);
    	return "进来了";
    }
}
