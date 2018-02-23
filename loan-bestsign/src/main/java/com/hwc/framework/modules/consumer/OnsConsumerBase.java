package com.hwc.framework.modules.consumer;

import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.common.aliyun.ons.consumer.HwcOnsConsumer;
import com.hwc.framework.config.OnsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by  on 2017/12/7.
 */
public abstract class OnsConsumerBase extends HwcOnsConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OnsConsumerBase.class);

    protected abstract String getConsumerId();

    protected abstract String getTags();


    @Autowired
    protected OnsConfig config;

    @PostConstruct
    public void init() {
        this.setExpression(getTags());
        Properties properties = new Properties();
        properties.setProperty("ConsumerId", getConsumerId());
        properties.setProperty("AccessKey", config.getAccessKey());
        properties.setProperty("SecretKey", config.getSecretKey());
        this.setProperties(properties);
        this.setTopic(config.getTopic());
        this.start();
        logger.info("topic:{} tag:{} cid:{}", getTopic(), getTags(), getConsumerId());
    }

    protected abstract boolean doConsume(HwcOnsContext context);

    public boolean consume(HwcOnsContext context) {
        return doConsume(context);
    }


}