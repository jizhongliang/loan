package com.hwc.framework.modules.dao;


import com.hwc.framework.modules.model.CLWContacts;
import org.apache.ibatis.annotations.Param;

public interface CLWContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLWContacts record);

    int insertSelective(CLWContacts record);

    CLWContacts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLWContacts record);

    int updateByPrimaryKey(CLWContacts record);

    CLWContacts getContactsByPhone(@Param("phone") String phone);

    CLWContacts getContactsByTipsPhone(@Param("phone") String phone);

    CLWContacts getCreditContactsByTipsPhone(@Param("phone") String phone);

    CLWContacts getCreditContactsByPhone(@Param("phone") String phone);
}