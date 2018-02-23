package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserEmerContacts;
import com.hwc.framework.modules.model.ClUserEmerContacts;
import com.hwc.mybatis.core.Service;

import java.util.List;

/**
 * 2017/10/23.
 */
public interface ClUserEmerContactsService extends Service<ClUserEmerContacts> {

    public Response updateUserEmerContacts(DUserEmerContacts dUserEmerContacts);

    public List<DUserEmerContacts> findUserEmerContactsByUserId(Long userId);

}
