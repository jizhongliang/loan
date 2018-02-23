package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hwc.framework.modules.service.impl.SmsServiceImpl;
import com.hwc.framework.modules.third.GeXinMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/11/29.
 */
@Service
public class GeXinMessageServiceImpl implements GeXinMessageService {

    private static final Logger logger = LoggerFactory.getLogger(GeXinMessageServiceImpl.class);
    final String host = "http://sdk.open.api.igexin.com/apiex.htm";
    public String prefix = "";
    @Value("${gexin.appKey}")
    private String appKey;
    @Value("${gexin.masterSecret}")
    private String masterSecret;
    @Value("${gexin.appid}")
    private String appid;

    public void sendListMessager(final String msg, final String title, final List<String> aliaes) {
        Thread th = new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub

                //IGtPush push = new IGtPush(appkey, master, true);
                IGtPush push = new IGtPush(host, appKey, masterSecret);

                TransmissionTemplate template = transmissionTemplateDemo(msg, title);

                ListMessage message = new ListMessage();
                message.setData(template);
                message.setOffline(true);
                message.setOfflineExpireTime(1000 * 3600 * 24);

                List<Target> targets = new ArrayList<Target>();
                for (String alias : aliaes) {
                    Target target = new Target();
                    target.setAppId(appid);
                    target.setClientId("");
                    target.setAlias(prefix + alias);
                    targets.add(target);
                }

                String taskId = push.getContentId(message, "任务别名_LIST");
                try {
                    IPushResult ret = push.pushMessageToList(taskId, targets);
                    logger.info("正常：" + ret.getResponse().toString());
                } catch (RequestException e) {
                    logger.error("message list send error:", e);
                }
            }
        });
        th.start();
    }

    private TransmissionTemplate transmissionTemplateDemo(String msg, String title) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appid);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        JSONObject json = new JSONObject();

        json.put("title", title);
        json.put("msgbody", msg);
        json.put("sendTime", FsUtils.formatDateTime(new Date()));
        template.setTransmissionContent(JSON.toJSONString(json));// 具体消息内容
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        APNPayload payload = new APNPayload();
        payload.setBadge(1);
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory(JSON.toJSONString(json));
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(title));

        template.setAPNInfo(payload);
        return template;
    }
}
