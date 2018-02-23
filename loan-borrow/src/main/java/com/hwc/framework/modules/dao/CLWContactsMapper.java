package com.hwc.framework.modules.dao;

import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.model.CLWContacts;

public interface CLWContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLWContacts record);

    int insertSelective(CLWContacts record);
    int add(CLWContacts record);
    CLWContacts selectByPrimaryKey(Long id);
    CLWContacts getByPhone(Map<String, String> map);
    CLWContacts getByPhones(Map<String, String> map);
    CLWContacts getByP(Map<String, String> map);
    int updateByPrimaryKeySelective(CLWContacts record);
    CLWContacts getByMobile(Map<String, String> map);
    CLWContacts getByMobiles(Map<String, String> map);
    int updateByPrimaryKey(CLWContacts record);

    List<CLWContacts> getContactsByPhone(Map<String, String> map);

    List<CLWContacts> getCreditContactsByPhone(Map<String, String> map);

    List<CLWContacts> getAllContactsByPhone(Map<String, String> map);
}