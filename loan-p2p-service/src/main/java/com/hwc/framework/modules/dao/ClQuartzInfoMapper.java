package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.QuartzInfoModel;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface ClQuartzInfoMapper extends Mapper<ClQuartzInfo> {

    List<QuartzInfoModel> page(Map<String, Object> map);

    int updateSelective(Map<String, Object> paramMap);

    List<ClQuartzInfo> listSelective(Map<String, Object> paramMap);

    ClQuartzInfo findSelective(Map<String, Object> paramMap);
}