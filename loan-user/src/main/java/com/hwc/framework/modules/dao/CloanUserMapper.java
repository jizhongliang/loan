package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.CloanUserModel;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface CloanUserMapper extends Mapper<CloanUserModel> {

    List<CloanUserModel> listModelPage(Map<String, Object> params);

    CloanUserModel getModelById(Long userId);

}