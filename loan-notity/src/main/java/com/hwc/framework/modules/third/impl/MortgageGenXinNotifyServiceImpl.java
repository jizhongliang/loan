package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.third.GeXinMessageService;
import com.hwc.framework.modules.third.MortgageGenXinNotifyService;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by   on 2017/11/30.
 */
@Component
public class MortgageGenXinNotifyServiceImpl implements MortgageGenXinNotifyService {


    Logger logger = org.slf4j.LoggerFactory.getLogger(MortgageGenXinNotifyServiceImpl.class);
    @Autowired
    private GeXinMessageService messageService;

    /**
     * 抵押申请
     *
     * @param jsonObject mortgage_apply
     */
    public void mortgageApplyNotify(JSONObject jsonObject) {
        String message = "您在" + FsUtils.formatDateTime(new Date()) + "已成功提交的车位分期申请，我们将尽快完成审核，请耐心等待!";
        sendMessage(message, "车位分期申请", jsonObject);
        // producer.sendJson(config.getMortgageApplyTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 通过初审通知
     * "mortgage_trail"
     *
     * @param jsonObject
     */
    public void mortgageTrailNotify(JSONObject jsonObject) {
        String message = "您申请的车位分期申请已经通过初审，我们会安排专员上门审核，请耐心等待!";
        sendMessage(message, "车位分期通过初审", jsonObject);
        //  producer.sendJson(config.getMortgageTrailTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 申请被拒绝通知
     * "mortgage_refuse"
     *
     * @param jsonObject
     */
    public void mortgageRefuseNotify(JSONObject jsonObject) {
        String message = "您在车位分期申请未通过审核，请完善资料后重新提交申请！";
        sendMessage(message, "车位分期未通过审核", jsonObject);
        //  producer.sendJson(config.getMortgageRefuseTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 复审通过消息通知
     * mortgage_review
     *
     * @param jsonObject
     */
    public void mortgageReviewNotify(JSONObject jsonObject) {
        String message = "您申请的车位分期申请已经通过复审，我们会安排专员与您联系，请耐心等待!";
        sendMessage(message, "车位分期通过复审", jsonObject);
        // producer.sendJson(config.getMortgageReviewTag(), "mortgage_" + mortgage.getId() + "_" + mortgage.getState(), null, mortgage);
    }

    /**
     * 最终审核通过消息通知
     * "mortgage_auth"
     *
     * @param jsonObject
     */
    public void mortgageAuthNotify(JSONObject jsonObject) {
        String message = "您申请的车位分期已通过终审，可随时申请分期用款！";
        sendMessage(message, "通过终审", jsonObject);
    }

    /**
     * "mortgage_withdrawals"
     * 抵押 提现 成功通知
     */
    public void mortgageWithdrawalsNotify(JSONObject jsonObject) {
        // producer.sendJson(config.getMortgageWithdrawalsTag(), "mortgage_" + borrow.getMid() + "_" + borrow.getState(), null, borrow);
    }

    private void sendMessage(String message, String title, JSONObject jsonObject) {
        message = replaceMessage(message, jsonObject);
        List<String> receiver = new ArrayList<>();
        receiver.add(jsonObject.getString("mobile"));
        logger.info("receiver:{}", jsonObject.getString("mobile"));
        messageService.sendListMessager(message, title, receiver);
    }

    private String replaceMessage(String message, JSONObject jsonObject) {
        for (String s : jsonObject.keySet()) {
            message = message.replaceAll("\\{" + s + "\\}", jsonObject.getString(s));
        }
        return message;
    }
}
