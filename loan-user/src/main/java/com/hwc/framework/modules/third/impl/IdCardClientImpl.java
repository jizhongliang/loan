/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.third.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.exception.ServiceException;
import com.hwc.framework.config.LinkFaceConfig;
import com.hwc.framework.modules.third.IdCardClient;

import cn.freesoft.utils.FsUtils;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;

/**
 * 
 * @author jinlilong
 * @version $Id: IdCardServiceImpl.java, v 0.1 2018年2月1日 上午10:30:20 jinlilong Exp $
 */
@Service
public class IdCardClientImpl implements IdCardClient {

    /**  */
    private static final Logger logger    = LoggerFactory.getLogger(IdCardClientImpl.class);

    /**  文件服务器地址*/
    private static final String ALI_URL   = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/";

    /**  参数*/
    private static final String ALI_PARAM = "?x-oss-process=image/resize,w_500";

    /**  */
    @Resource
    private LinkFaceConfig      linkFaceConfig;

    /** 
     * @see com.hwc.framework.modules.third.IdCardClient#getidCardInfo(java.lang.String)
     */
    @Override
    public JSONObject getidCardInfo(String image) {

        Map<String, String> query = new HashMap<>();
        query.put("api_id", linkFaceConfig.getApikey());
        query.put("api_secret", linkFaceConfig.getSecretkey());
        String url = ALI_URL + image + ALI_PARAM;
        query.put("url", url);
        RawResponse send = Requests.post(linkFaceConfig.getOcrHost()).params(query).send();
        JSONObject jsonObject = send.readToJson(JSONObject.class);
        logger.info("身份正面照解析结果image=" + image + ",data=>" + jsonObject.toJSONString());
        String status = jsonObject.getString("status");
        if ("ok".equalsIgnoreCase(status)) {
            String side = jsonObject.getString("side");
            if ("front".equalsIgnoreCase(side)) {
                JSONObject info = jsonObject.getJSONObject("info");
                if (FsUtils.strsNotEmpty(info)) {
                    return info;
                }
            } else {
                throw new ServiceException("身份证正面照片不正确，请重新上传");
            }
        }
        throw new ServiceException("身份正面照解析失败");

    }

    /** 
     * @see com.hwc.framework.modules.third.IdCardClient#validateCardBackInfo(java.lang.String)
     */
    @Override
    public boolean validateCardBackInfo(String image) {

        Map<String, String> query = new HashMap<>();
        query.put("api_id", linkFaceConfig.getApikey());
        query.put("api_secret", linkFaceConfig.getSecretkey());
        String url = ALI_URL + image + ALI_PARAM;
        query.put("url", url);
        RawResponse send = Requests.post(linkFaceConfig.getOcrHost()).params(query).send();
        JSONObject jsonObject = send.readToJson(JSONObject.class);
        String status = jsonObject.getString("status");
        if ("ok".equalsIgnoreCase(status)) {
            String side = jsonObject.getString("side");
            if ("back".equalsIgnoreCase(side)) {
                return true;
            } else {
                throw new ServiceException("身份证反面照片不正确，请重新上传");
            }
        } else {
            throw new ServiceException("身份证反面照片解析失败");
        }
    }

}
