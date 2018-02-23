/**
 * Project Name:api-webhook
 * File Name:EventBusConfig.java
 * Package Name:com.example.service.webhook.config
 * Date:2016年7月19日上午11:53:48
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.
 */

package com.hwc.framework.config;

import com.google.common.eventbus.EventBus;
import com.hwc.framework.modules.third.listener.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:EventBusConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:   TODO ADD REASON. <br/>
 * Date:     2016年7月19日 上午11:53:48 <br/>
 *
 * @author yuandong
 * @see
 * @since JDK 1.6
 */
@Configuration
public class EventBusConfig {
    @Autowired
    private ApplicationContext context;

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new EventListener(context));
        return eventBus;
    }

}
  
