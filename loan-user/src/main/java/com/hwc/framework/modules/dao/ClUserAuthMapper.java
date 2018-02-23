package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.domain.DUserAuthModel;
import com.hwc.framework.modules.model.ClUserAuth;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface ClUserAuthMapper extends Mapper<ClUserAuth> {

    List<DUserAuthModel> listUserAuthModelPage(Map<String, Object> params);

}