package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.mybatis.core.Mapper;

import java.util.Map;

public interface ClMortgageMapper extends Mapper<ClMortgage> {

    int updateByMobile(Map<String, Object> map);
}