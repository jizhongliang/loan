package com.hwc.framework.modules.dao;

import java.util.Map;

import com.hwc.framework.modules.model.ClUser;
import com.hwc.mybatis.core.Mapper;

public interface ClUserMapper extends Mapper<ClUser> {
	  void updateIsLogin(Map<String,Object> map);
}