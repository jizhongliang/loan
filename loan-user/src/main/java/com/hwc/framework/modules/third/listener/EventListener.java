/**
 * Project Name:api-webhook
 * File Name:EventListener.java
 * Package Name:com.example.service.webhook.service
 * Date:2016年7月19日下午2:24:31
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.
 */

package com.hwc.framework.modules.third.listener;

import com.alibaba.fastjson.JSONObject;
import com.google.common.eventbus.Subscribe;
import com.hwc.framework.modules.third.business.carrier.billitem.CarrierBillTask;
import com.hwc.framework.modules.third.business.carrier.sevice.CarrierBillService;
import com.hwc.framework.modules.third.business.zhengxin.billitem.ZhengxinTask;
import com.hwc.framework.modules.third.business.zhengxin.service.ZhengxinService;
import org.springframework.context.ApplicationContext;

/**
 * ClassName:EventListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:   TODO ADD REASON. <br/>
 * Date:     2016年7月19日 下午2:24:31 <br/>
 *
 * @author yuandong
 * @see
 * @since JDK 1.6
 */
public class EventListener {

    private ApplicationContext applicationContext;


    public EventListener(ApplicationContext context) {
        applicationContext = context;
    }

    @Subscribe
    public void zhengxinListener(ZhengxinTask task) {
        try {
            ZhengxinService billService = (ZhengxinService) applicationContext.getBean("zhengxinService");
            billService.fetchBill(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void carrierListener(CarrierBillTask carrierBill) {

        System.out.println("mobile:" + carrierBill.getMobile());

        try {
            CarrierBillService billService = (CarrierBillService) applicationContext.getBean("carrierBillService");
            billService.fetchBill(carrierBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
  
