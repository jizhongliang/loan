package com.hwc.framework.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;


/**
 * Created by   on 2017/11/23.
 */
@ConfigurationProperties(prefix = "ons.borrow")
@Component
@Data
public class BorrowProducerConfig {

    private String producerId;
    private String topic;
}
