package com.hwc.framework.modules.third.impl;

import com.hwc.common.aliyun.ons.MQConsts;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.third.PayNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

/**
 * Created by   on 2017/11/24.
 */
@Service
public class PayNotifyServiceImpl implements PayNotifyService {
    @Autowired
    @Qualifier("borrowProducer")
    private HwcOnsProducer producer;

    /**
     * 放款请求成功
     *
     * @param respBean
     */
    public void paySuccess(PayRespBean respBean) {
        Properties properties=new Properties();
        Long expire = 60 * 1000 * 5L;
        properties.setProperty(MQConsts.DELAY_TIME, expire + "");
        producer.sendJson("pay_success", respBean.getkey(), properties, respBean);
    }

    /**
     * 放款回调消息通知
     *
     * @param beans
     */
    public void payCallback(List<PayRespBean> beans) {
        for (PayRespBean bean : beans) {
            producer.sendJson("pay_callback", bean.getkey(), null, bean);
        }
    }

    /**
     * 放款请求失败
     *
     * @param respBean
     */
    public void payError(PayRespBean respBean) {
        producer.sendJson("pay_error", respBean.getkey(), null, respBean);
    }

    /**
     * 代扣请求成功
     *
     * @param bean
     */
    public void chargeSuccess(ChargeRespBean bean) {
        producer.sendJson("charge_success", bean.getKey(), null, bean);
    }

    /**
     * 代扣请求失败
     *
     * @param bean
     */
    public void chargeError(ChargeRespBean bean) {
        producer.sendJson("charge_error", bean.getKey(), null, bean);
    }

    /**
     * 绑定卡成功
     *
     * @param respBean
     */
    public void bankCardBindSuccess(BankCardAuthoriztionRespBean respBean) {
        producer.sendJson("bind_card_success", "agree_" + respBean.getAgree_no(), null, respBean);
    }
}
