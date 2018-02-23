package com.hwc.framework.modules.consumer;

import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.common.aliyun.ons.consumer.HwcOnsConsumer;
import com.hwc.framework.config.OnsConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by   on 2017/11/28.
 */
public abstract class BorrowConsumerBase extends HwcOnsConsumer {

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
        this.setTopic(getTopic());
        this.start();
    }

    protected abstract boolean doConsume(HwcOnsContext context);

    public boolean consume(HwcOnsContext context) {
        return doConsume(context);
    }


}
