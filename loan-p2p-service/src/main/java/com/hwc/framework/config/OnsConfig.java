package com.hwc.framework.config;

import com.hwc.common.aliyun.ons.consumer.HwcOnsConsumer;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by   on 2017/11/23.
 */
@Data
@ConfigurationProperties(prefix = "ons")
@Configuration
public class OnsConfig {

    @Autowired
    private SmsProducerConfig config;

    private String accessKey;

    private String secretKey;


    @Bean("smsProducer")
    public HwcOnsProducer getOnsProducer() {
        Properties properties = new Properties();
        HwcOnsProducer producer = new HwcOnsProducer();
        properties.setProperty("ProducerId", config.getProducerId());
        properties.setProperty("AccessKey", accessKey);
        properties.setProperty("SecretKey", secretKey);
        producer.setProperties(properties);
        producer.setTopic(config.getTopic());
        producer.start();
        return producer;
    }


}
