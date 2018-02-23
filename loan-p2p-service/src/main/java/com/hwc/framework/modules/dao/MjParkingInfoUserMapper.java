package com.hwc.framework.modules.dao;

import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.domain.MjParkingInfo;
import com.hwc.framework.modules.domain.MjParkingInfoUser;

public interface MjParkingInfoUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MjParkingInfoUser record);

    int insertSelective(MjParkingInfoUser record);

    MjParkingInfoUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MjParkingInfoUser record);

    int updateByPrimaryKey(MjParkingInfoUser record);
    
    List<MjParkingInfoUser> findInfos(Map<String, Object> map);
}