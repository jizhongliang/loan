package com.hwc.framework.config;

import com.hwc.framework.common.BeanUtil;
import com.hwc.framework.modules.controller.ArcCreditController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

/**
 * Created by on 2017/11/22.
 */
@Configuration
public class LoanSchedulingConfigurer implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(LoanSchedulingConfigurer.class);

    @Bean
    public BeanUtil beanUtil() {
        return new BeanUtil();
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(10);

        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(poolScheduler());
    }
}
