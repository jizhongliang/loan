package com.hwc.framework.modules.dao;

import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.domain.CLChannelApp;

public interface CLChannelAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLChannelApp record);

    int insertSelective(CLChannelApp record);

    CLChannelApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLChannelApp record);

    int updateByPrimaryKey(CLChannelApp record);
    
    List<CLChannelApp> findInfos(Map<String, Object> map);
}