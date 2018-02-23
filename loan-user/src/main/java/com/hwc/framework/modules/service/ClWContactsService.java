package com.hwc.framework.modules.service;

import java.util.List;
import java.util.Map;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.model.ClWContacts;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClWContactsService extends Service<ClWContacts> {

    public Response getWContactsWithPhone(DWContacts dwContacts);

    public DWContacts getWContactsWithPhone(String phone);

    public DWContacts getWContactsWithTipsPhone(String tipsPhone);

    public DWContacts getWContactsWithIdNo(String idNO);

    public List<DWContacts> listWContactsPage(Map<String, Object> params);

    public Response importWContactsList(WContactsImportListRequest list);

    public Response importWContactsOne(DWContacts dwContacts);

    public Response updateWContactsOne(DWContacts dwContacts);

    public Response getWContactsOne(Long id);
     DWContacts getByP(Map<String, String> map);
}
