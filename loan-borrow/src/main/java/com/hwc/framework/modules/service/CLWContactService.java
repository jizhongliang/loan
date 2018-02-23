package com.hwc.framework.modules.service;

import com.hwc.framework.modules.model.CLWContacts;

import java.util.List;
import java.util.Map;

/**
 * Created by jzl on 2018/1/9.
 */
public interface CLWContactService {
    /**
     * 获取白名单实体
     * @param phone
     * @return
     */
    List<CLWContacts> getContactsByPhone(String phone);
    List<CLWContacts> getCreditContactsByPhone(String phone);
    CLWContacts getByPhone(String phone);
    CLWContacts getByPhones(String phone);
    CLWContacts getByMobile(String phone);
    CLWContacts getByMobiles(String phone);
    CLWContacts getByP(Map<String, String> map);
    void update(CLWContacts cLWContacts);
    void add(CLWContacts cLWContacts);

    List<CLWContacts> getAllContactsByPhone(String mobile);
}
