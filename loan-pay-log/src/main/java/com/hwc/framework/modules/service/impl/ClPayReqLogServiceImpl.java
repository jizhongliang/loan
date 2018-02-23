package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.hwc.framework.modules.dao.ClPayReqLogMapper;

import com.hwc.framework.modules.model.ClPayReqLog;
import com.hwc.framework.modules.service.ClPayReqLogService;
import com.hwc.mybatis.core.AbstractService;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 2017/11/01.
 */
@Service
public class ClPayReqLogServiceImpl extends AbstractService<ClPayReqLogMapper, ClPayReqLog> implements ClPayReqLogService {
    private static final Logger logger = LoggerFactory.getLogger(ClPayReqLogServiceImpl.class);

    public void doSaveRequest(String service, String orderNo, String data, Map<String, String> query, Long borrow_id, Long userId, String ip) {
        try {
            ClPayReqLog payReqLog = new ClPayReqLog();
            payReqLog.setBorrowId(borrow_id);
            payReqLog.setUserId(userId);
            payReqLog.setOrderNo(orderNo);
            payReqLog.setService(service);
            payReqLog.setCreateTime(new Date());
            payReqLog.setParams(data);
            payReqLog.setReqDetailParams(JSON.toJSONString(query));
            payReqLog.setIp(ip);
            this.insert(payReqLog);
        } catch (Exception ex) {
            logger.error("插入 pay log error ", ex);
        }
    }

    public void updateResponse(String orderNo, String resp, String state) {
        try {
            ClPayReqLog reqLog = findbyOrderNo(orderNo);
            if (FsUtils.strsNotEmpty(reqLog)) {
                ClPayReqLog reqLog1 = new ClPayReqLog();
                reqLog1.setReturnParams(resp);
                reqLog1.setReturnTime(new Date());
                reqLog1.setId(reqLog.getId());
                JSONObject jsonObject = JSONObject.parseObject(resp);
                if ("0000".equals(state)) {
                    reqLog1.setResult(1);
                } else {
                    reqLog1.setResult(-1);
                }
                this.update(reqLog1);

            }
        } catch (Exception ex) {
            logger.error("updateResponse pay log error ", ex);
        }

    }

    public boolean updateCallBackResponse(String orderNo, String resp, String state) {
        ClPayReqLog reqLog = findbyOrderNo(orderNo);
        if (FsUtils.strsNotEmpty(reqLog)) {
            ClPayReqLog reqLog1 = new ClPayReqLog();
            reqLog1.setNotifyParams(resp);
            reqLog1.setNotifyTime(new Date());
            reqLog1.setId(reqLog.getId());
            JSONObject jsonObject = JSONObject.parseObject(resp);
            if ("1".equals(state) || "2".equals(state)) {
                reqLog1.setResult(1);
            } else {
                reqLog1.setResult(-1);
            }
            this.update(reqLog1);
            return true;
        } else {
            return false;
        }

    }

    private ClPayReqLog findbyOrderNo(String orderNo) {
        ClPayReqLog log = new ClPayReqLog();
        log.setOrderNo(orderNo);
        List<ClPayReqLog> logs = this.mapper.select(log);
        if (FsUtils.strsEmpty(logs) || logs.isEmpty()) {
            return null;
        } else {
            return logs.get(0);
        }

    }
}
