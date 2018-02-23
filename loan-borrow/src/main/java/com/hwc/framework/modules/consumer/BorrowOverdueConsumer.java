package com.hwc.framework.modules.consumer;

import com.alibaba.fastjson.JSON;
import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.framework.modules.service.impl.ArcCreditServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by   on 2017/11/29.
 */
@Component
public class BorrowOverdueConsumer extends BorrowConsumerBase {

    private static final Logger logger = LoggerFactory.getLogger(BorrowOverdueConsumer.class);
    @Value("${ons.borrow.consumer.borrowOverdueId}")
    private String id;
    @Value("${ons.borrow.consumer.borrowOverdueTag}")
    private String tag;
    @Value("${ons.borrow.topic}")
    private String topic;

    @Autowired
    private ClBorrowService borrowService;

    public String getTopic() {
        return topic;
    }


    @Override
    protected String getConsumerId() {
        return id;
    }

    @Override
    protected String getTags() {
        return tag;
    }

    @Override
    protected boolean doConsume(HwcOnsContext context) {
        ClBorrowRepay repay = (ClBorrowRepay) context.getData();
        logger.info("逾期消息处理:messageId:{},内容：{}", context.getMsgID(), JSON.toJSON(repay));
        //todo 订单变成逾期 冻结额度
        borrowService.overdue(repay.getBorrowId());
        return true;
    }
}
