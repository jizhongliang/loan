package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.ArcCredit;

import java.util.Map;

public interface ArcCreditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArcCredit record);

    int insertSelective(ArcCredit record);

    ArcCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArcCredit record);

    int updateByPrimaryKey(ArcCredit record);

    /**
     * 更新额度
     * @param map
     */
    void updateAmount(Map<String, Object> map);

    /**
     * 获取额度信息
     * @param userId
     * @return
     */
    ArcCredit  getCreditById(Long userId);
}