package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.SmsTpl;

public interface SmsTplMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsTpl record);

    int insertSelective(SmsTpl record);

    SmsTpl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsTpl record);

    int updateByPrimaryKey(SmsTpl record);
}