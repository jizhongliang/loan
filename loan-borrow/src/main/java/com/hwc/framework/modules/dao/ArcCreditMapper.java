package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.mybatis.core.Mapper;

import java.util.Map;

public interface ArcCreditMapper extends Mapper<ArcCredit> {
    /**
     * 更新额度
     * @param map
     */
    void updateAmount(Map<String, Object> map);
    void updates(Map<String, Object> map);
    /**
     * 获取额度信息
     * @param userId
     * @return
     */
    ArcCredit  getCreditById(Long userId);
}