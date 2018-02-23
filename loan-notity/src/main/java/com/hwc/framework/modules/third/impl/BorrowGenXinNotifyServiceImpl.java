package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.third.BorrowGenXinNotifyService;
import com.hwc.framework.modules.third.GeXinMessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by   on 2017/11/29.
 */
@Component
public class BorrowGenXinNotifyServiceImpl implements BorrowGenXinNotifyService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowGenXinNotifyServiceImpl.class);

	
    @Autowired
    private GeXinMessageService messageService;

    public void borrowApply(JSONObject jsonObject) {
        String message = "您在" + FsUtils.formatDateTime(new Date()) + "提交的分期申请正在审核中，请保持手机畅通，" +
                "稍后可能有审核专员联系您!";
        sendMessage(message, "消费分期申请", jsonObject);
    }


    /**
     * 申请被拒绝消息通知
     * credit_borrow_refuse
     *
     * @param jsonObject
     */
    public void refuseBorrowNotify(JSONObject jsonObject) {
        String message = "您在{time}提交的分期申请因风控原因未通过审核，请完善资料后重新提交申请！".
                replace("{time}", FsUtils.formatDateTime(new Date()));
        sendMessage(message, "消费分期未通过审核", jsonObject);
//        Properties properties = new Properties();
//        properties.setProperty("borrow", JSON.toJSONString(borrow));
//        producer.sendJson(config.getRefuseBorrowTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties, getBean(borrow));borrow
    }

    /**
     * 审核通过
     * credit_borrow_pass
     *
     * @param jsonObject
     */
    public void borrowPassNotify(JSONObject jsonObject) {
        String msg = "恭喜您在{time}提交的分期申请已放款成功,请及时查看您的收款银行卡!".replace("{time}",
                FsUtils.formatDateTime(new Date()));
        sendMessage(msg, "消费分期审核通过", jsonObject);
//  Properties properties = new Properties();
//        properties.setProperty("borrow", JSON.toJSONString(borrow));
//        propertiesroducer.sendJson(config.getBorrowPassTag(), "borrow_" + borrow.getId() + "_" + borrow.getState(), properties, getBean(borrow));
    }

    /**
     * 逾期通知
     * repay_borrow_overdue
     *
     * @param jsonObject
     */
    public void borrowOverdueNotify(JSONObject jsonObject) {
        String msg = "您在财位分期{loan}元,归还时间为{repay_data},现已逾期，逾期后将收取{money}元/天的逾期管理费用，请尽快归还!";
        sendMessage(msg, "逾期通知", jsonObject);
    }

    private void sendMessage(String message, String title, JSONObject jsonObject) {
        message = replaceMessage(message, jsonObject);
        List<String> receiver = new ArrayList<>();
        receiver.add(jsonObject.getString("mobile"));
        messageService.sendListMessager(message, title, receiver);
    }

    private String replaceMessage(String message, JSONObject jsonObject) {
        for (String s : jsonObject.keySet()) {
            message = message.replaceAll("\\{" + s + "\\}", jsonObject.getString(s));
        }
        return message;
    }

    /**
     * 快到期提醒
     * repay_borrow_expire
     *
     * @param jsonObject
     */
    public void repayExpireNotify(JSONObject jsonObject) {
        String msg = "您在{time}分期{loan}元,最后归还日期为{date}，请及时归还！".replace("{time}", FsUtils.formatDateTime(new Date()));
        sendMessage(msg, "到期提醒", jsonObject);
        // ClBorrow borrow = borrowService.getBorrow(repay.getBorrowId());
        //  Properties properties = new Properties();
        //  properties.setProperty("borrow", JSON.toJSONString(borrow));
        //  producer.sendJson(config.getRepayExpireTag(), "repay_" + repay.getId() + "_" + repay.getState(), properties, repay);
    }
    
   /** 
    * 放款成功
    * @param jsonObject
    */
    public void paySuccessNotify(JSONObject jsonObject) {
    	logger.info("pay_success_notify >>>"+jsonObject.toString());
    	 String msg = "尊敬的用户：恭喜您申请分期成功!";
         sendMessage(msg, "放款成功", jsonObject);
    }
    
    /** 
     * 还款成功
     * @param jsonObject
     */
    public void repaySuccess(JSONObject jsonObject) {
    	logger.info("repay_success_notify >>>"+jsonObject.toString());
        String msg = String.format("尊敬的用户：恭喜您于%s归还成功!",FsUtils.formatDateTime(new Date()));
        sendMessage(msg, "还款成功", jsonObject);
    }
    
    
}
