package com.hwc.framework.modules.third;

import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.PayRespBean;

import java.util.List;

/**
 * Created by   on 2017/11/24.
 */
public interface PayNotifyService {
    /**
     * 放款请求成功
     *
     * @param respBean
     */
    void paySuccess(PayRespBean respBean);

    /**
     * 放款回调消息通知
     *
     * @param beans
     */
    void payCallback(List<PayRespBean> beans);

    /**
     * 放款请求失败
     *
     * @param respBean
     */
    void payError(PayRespBean respBean);

    /**
     * 代扣请求成功
     *
     * @param bean
     */
    void chargeSuccess(ChargeRespBean bean);

    /**
     * 代扣请求失败
     *
     * @param bean
     */
    void chargeError(ChargeRespBean bean);

    /**
     * 绑定卡成功
     *
     * @param respBean
     */
    void bankCardBindSuccess(BankCardAuthoriztionRespBean respBean);
}
