package com.hwc.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by   on 2017/11/30.
 */
@Data
@ConfigurationProperties(prefix = "ons")
@Configuration
public class OnsConfig {
    private String accessKey;

    private String secretKey;

    private String topic;


}
