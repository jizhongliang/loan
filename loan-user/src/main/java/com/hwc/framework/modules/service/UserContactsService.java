package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface UserContactsService {

    public Response getContactsList(Long userId, int currentPage, int pageSize);

    public Response getMessageList(Long userId, int currentPage, int pageSize);
}
