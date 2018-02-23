package com.hwc.framework.modules.consumer;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.framework.modules.model.BestSignUsers;
import com.hwc.framework.modules.service.BestSignUsersService;
import com.hwc.framework.modules.third.BestSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.Cache;

import java.util.Date;

/**
 * Created by  on 2017/12/7.
 */
@Component
public class UserAuthConsumer extends OnsConsumerBase {


    private static final Logger logger = LoggerFactory.getLogger(UserAuthConsumer.class);
    @Value("${ons.user.consumer.authId}")
    private String id;
    @Value("${ons.user.consumer.authTag}")
    private String tag;

    @Autowired
    private BestSignService bestSignService;

    @Override
    protected String getConsumerId() {
        return id;
    }

    @Override
    protected String getTags() {
        return tag;
    }

    @Autowired
    private IHwcCache cache;
    @Autowired
    private BestSignUsersService bestSignUsersService;

    @Override
    protected boolean doConsume(HwcOnsContext context) {
        try {

            String key = "bestsign:user:" + FsUtils.formatDate(new Date());
            if (context.getData() instanceof JSONObject) {
                JSONObject object = (JSONObject) context.getData();
                BestSignUsers users = bestSignUsersService.getUser(object.getLong("userId"));
                if (FsUtils.strsNotEmpty(users)) {
                    logger.info("best sign 已经注册 {}", JSONObject.toJSONString(object));
                    return true;
                }
                JSONObject register = bestSignService.register(object);
                if (!register.getInteger("errno").equals(0) && !register.getInteger("errno").equals(241208)) {
                    logger.error("上传用户失败");
                    cache.hset(key, object.getString("userId"), object);
                    return false;
                }
                JSONObject idnoJSon = bestSignService.setUserIdno(object);
                if (0 != idnoJSon.getInteger("errno")) {
                    logger.error("设置用户信息失败");
                    cache.hset(key, object.getString("userId"), object);
                    return false;
                }
                JSONObject cert = bestSignService.applycert(object);
                if (!cert.getInteger("errno").equals(0)) {
                    logger.error("申请用户证书失败");
                    cache.hset(key, object.getString("userId"), object);
                    return false;
                }

                if (!StringUtils.isEmpty(object.getString("signatureImg"))) {
                    JSONObject signImage = bestSignService.upSignatureImage(object);
                    if (!signImage.getInteger("errno").equals(0)) {
                        logger.error("上传签名图片失败:{}", signImage.toJSONString());
                        cache.hset(key, object.getString("userId"), object);
                        return false;
                    } else {
                        JSONObject data1 = cert.getJSONObject("data");
                        object.put("certType", data1.getString("certType"));
                        object.put("certId", data1.getString("certId"));
                        object.put("signImage", object.getString("signatureImg"));
                        bestSignUsersService.signUser(object);
                    }
                }
                logger.info("向上上签注册用户成功,bestsign:user:{},object:{}", object.getString("userId"), JSONObject.toJSONString(object));
            } else {
                logger.error("上传用户信息数据类型不正确 {}", JSONObject.toJSONString(context.getData()));
            }
        } catch (Exception ex) {
            logger.error("上传用户信息失败", ex);
        }
        return false;
    }
}
