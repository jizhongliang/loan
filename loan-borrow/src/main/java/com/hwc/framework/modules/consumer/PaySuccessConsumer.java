package com.hwc.framework.modules.consumer;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jws.Oneway;

/**
 * Created by lxk on 2017/12/20.
 */
@Component
public class PaySuccessConsumer extends BorrowConsumerBase {
    private static final Logger logger = LoggerFactory.getLogger(BorrowOverdueConsumer.class);
    @Value("${ons.borrow.consumer.payId}")
    private String id;
    @Value("${ons.borrow.consumer.payTag}")
    private String tag;
    @Value("${ons.borrow.topic}")
    private String topic;
    @Autowired
    private PayService payService;

    @Override
    protected String getConsumerId() {
        return id;
    }

    @Override
    protected String getTags() {
        return tag;
    }
    public String getTopic() {
        return topic;
    }
    @Override
    protected boolean doConsume(HwcOnsContext context) {
        PayRespBean bean = (PayRespBean) context.getData();
        Response response = payService.queryPayMent(bean.getOrder_no());
        logger.info(JSON.toJSONString(response));
        return false;
    }
}
