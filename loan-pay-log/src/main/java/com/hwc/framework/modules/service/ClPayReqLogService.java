package com.hwc.framework.modules.service;

import com.alibaba.fastjson.JSONObject;

import com.hwc.framework.modules.model.ClPayReqLog;
import com.hwc.mybatis.core.Service;

import java.util.Map;

/**
 * 2017/11/01.
 */
public interface ClPayReqLogService extends Service<ClPayReqLog> {

    void doSaveRequest(String service, String orderNo,  String data, Map<String, String> query, Long borrow_id, Long userId, String ip);

    /**
     * 回调之后 记录回调信息
     *
     * @param orderNo
     * @param resp
     */
    boolean updateCallBackResponse(String orderNo, String resp, String state);

    void updateResponse(String orderNo, String resp, String state);
}
