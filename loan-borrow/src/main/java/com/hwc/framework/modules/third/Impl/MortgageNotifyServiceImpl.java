package com.hwc.framework.modules.third.Impl;

import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.config.BorrowConsumerConfig;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.framework.modules.third.MortgageNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by   on 2017/11/24.
 */
@Service
public class MortgageNotifyServiceImpl implements MortgageNotifyService {
    @Autowired
    @Qualifier("borrowProducer")
    private HwcOnsProducer producer;

    @Autowired
    private BorrowConsumerConfig config;

    /**
     * 抵押申请
     *
     * @param mortgage mortgage_apply
     */
    public void mortgageApplyNotify(ClMortgage mortgage) {
        producer.sendJson(config.getMortgageApplyTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 通过初审通知
     * "mortgage_trail"
     *
     * @param mortgage
     */
    public void mortgageTrailNotify(ClMortgage mortgage) {
        producer.sendJson(config.getMortgageTrailTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 申请被拒绝通知
     * "mortgage_refuse"
     *
     * @param mortgage
     */
    public void mortgageRefuseNotify(ClMortgage mortgage) {
        producer.sendJson(config.getMortgageRefuseTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 复审通过消息通知
     * mortgage_review
     *
     * @param mortgage
     */
    public void mortgageReviewNotify(ClMortgage mortgage) {
        producer.sendJson(config.getMortgageReviewTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 最终审核通过消息通知
     * "mortgage_auth"
     * @param mortgage
     */
    public void mortgageAuthNotify(ClMortgage mortgage) {
        producer.sendJson(config.getMortgageAuthTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /** "mortgage_withdrawals"
     * 抵押 提现 成功通知
     */
    public void mortgageWithdrawalsNotify(ClBorrow borrow) {
        producer.sendJson(config.getMortgageWithdrawalsTag(), "mortgage_" + borrow.getMid() + "_" + borrow.getState(), null, borrow);
    }
}
