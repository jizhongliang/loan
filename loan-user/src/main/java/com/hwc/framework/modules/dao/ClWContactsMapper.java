package com.hwc.framework.modules.dao;

import java.util.Map;

import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.model.ClWContacts;
import com.hwc.mybatis.core.Mapper;

public interface ClWContactsMapper extends Mapper<ClWContacts> {
	ClWContacts getByP(Map<String, String> map);
}