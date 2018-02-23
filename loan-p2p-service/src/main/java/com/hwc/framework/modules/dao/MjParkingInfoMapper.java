package com.hwc.framework.modules.dao;

import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.domain.MjParkingInfo;


public interface MjParkingInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MjParkingInfo record);

    int insertSelective(MjParkingInfo record);

    MjParkingInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MjParkingInfo record);

    int updateByPrimaryKey(MjParkingInfo record);
    
    List<MjParkingInfo> findInfos(Map<String, Object> map);
}