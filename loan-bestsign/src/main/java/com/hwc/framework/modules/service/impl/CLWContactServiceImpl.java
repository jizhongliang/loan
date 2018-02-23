package com.hwc.framework.modules.service.impl;

import com.hwc.framework.modules.dao.CLWContactsMapper;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.service.CLWContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jzl on 2018/1/9.
 */
@Service
public class CLWContactServiceImpl implements CLWContactService {
    private static Logger logger = LoggerFactory.getLogger(CLWContactServiceImpl.class);
    @Autowired
    private CLWContactsMapper clwContactsMapper;


    /**
     * 获取实体
     * @param phone
     * @return
     */
    @Override
    public CLWContacts getContactsByPhone(String phone) {
        CLWContacts entity = clwContactsMapper.getContactsByTipsPhone(phone);
        if (null != entity) {
            return entity;
        }
        return clwContactsMapper.getContactsByPhone(phone);
    }

    @Override
    public CLWContacts getCreditContactsByPhone(String phone) {
        CLWContacts entity = clwContactsMapper.getCreditContactsByTipsPhone(phone);
        if (null != entity) {
            return entity;
        }
        return clwContactsMapper.getCreditContactsByPhone(phone);
    }
}
