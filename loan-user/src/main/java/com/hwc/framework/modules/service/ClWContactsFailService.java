package com.hwc.framework.modules.service;

import java.util.List;
import java.util.Map;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.domain.response.WContactsFailImportListResponse;
import com.hwc.framework.modules.model.ClWContactsFail;
import com.hwc.mybatis.core.Service;

/**
 * 2018/01/26.
 */
public interface ClWContactsFailService extends Service<ClWContactsFail> {
	
	int insert( DWContacts dwContacts);

	int insertAbnormal(DWContacts dwContacts);

	List<DWContacts> listWContactsFailPage(Map<String, Object> params);
}
