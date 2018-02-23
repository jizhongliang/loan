package com.hwc.framework.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;

import lombok.Data;

/**
 * Created by   on 2017/11/23.
 */
@Data
@ConfigurationProperties(prefix = "ons")
@Configuration
public class OnsConfig {

    @Autowired
    private BorrowProducerConfig config;
    @Autowired
    private BorrowConsumerConfig consumerConfig;
    private String               accessKey;

    private String               secretKey;

    private String               smsAccessKey;

    private String               smsSecretKey;

    @Bean("borrowProducer")
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

    @Bean("smsProducer")
    public HwcOnsProducer getSmsProducer() {
        Properties properties = new Properties();
        HwcOnsProducer producer = new HwcOnsProducer();
        properties.setProperty("ProducerId", config.getProducerId());
        properties.setProperty("AccessKey", smsAccessKey);
        properties.setProperty("SecretKey", smsSecretKey);
        producer.setProperties(properties);
        producer.setTopic(config.getTopic());
        producer.start();
        return producer;
    }

}
