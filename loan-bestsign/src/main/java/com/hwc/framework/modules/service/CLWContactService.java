package com.hwc.framework.modules.service;

import com.hwc.framework.modules.model.CLWContacts;

/**
 * Created by jzl on 2018/1/9.
 */
public interface CLWContactService {
    /**
     * 获取白名单实体
     * @param phone
     * @return
     */
    CLWContacts getContactsByPhone(String phone);

    CLWContacts getCreditContactsByPhone(String phone);
}
