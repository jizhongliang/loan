package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.domain.ClH5UserInfo;
import com.hwc.mybatis.core.Mapper;



public interface ClH5UserInfoMapper extends Mapper<ClH5UserInfo>{

	int save(ClH5UserInfo clH5UserInfo);
	
}
