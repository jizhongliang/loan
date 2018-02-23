package com.hwc.framework.modules.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwc.framework.modules.dao.CLWContactsMapper;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.service.CLWContactService;

/**
 * Created by jzl on 2018/1/9.
 */
@Service
public class CLWContactServiceImpl implements CLWContactService {
    private static Logger     logger = LoggerFactory.getLogger(CLWContactServiceImpl.class);
    @Autowired
    private CLWContactsMapper clwContactsMapper;

    /**
     * 获取实体
     * @param phone
     * @return
     */
    @Override
    public List<CLWContacts> getContactsByPhone(String phone) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("phone", phone);
        return clwContactsMapper.getContactsByPhone(map);
    }

	@Override
	public List<CLWContacts> getCreditContactsByPhone(String phone) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		return clwContactsMapper.getCreditContactsByPhone(map);
	}

	@Override
	public CLWContacts getByPhone(String phone) {
		 Map<String, String> map = new HashMap<String, String>();
	     map.put("phone", phone);
		return clwContactsMapper.getByPhone(map);
	}
	@Override
	public CLWContacts getByPhones(String phone) {
		Map<String, String> map = new HashMap<String, String>();
		logger.info("------------------phone=="+phone);
		map.put("phone", phone);
		map.put("isAvailability", "1");
		CLWContacts cLWContacts=clwContactsMapper.getByPhones(map);
		logger.info("***************cLWContacts=="+(null==cLWContacts));
		
		return cLWContacts;
	}

	@Override
	public void update(CLWContacts cLWContacts) {
		clwContactsMapper.updateByPrimaryKeySelective(cLWContacts);
		
	}

	@Override
	public void add(CLWContacts cLWContacts) {
		clwContactsMapper.add(cLWContacts);
		
	}

	/**
	 * 获取所有的白名单，不区分信用和车位
	 * @param mobile
	 * @return
	 */
	@Override
	public List<CLWContacts> getAllContactsByPhone(String mobile) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", mobile);
		return clwContactsMapper.getAllContactsByPhone(map);
	}

	@Override
	public CLWContacts getByMobile(String phone) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("isAvailability", "1");
		CLWContacts cLWContacts=clwContactsMapper.getByMobile(map);
		return cLWContacts;
	}
	@Override
	public CLWContacts getByMobiles(String phone) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("isAvailability", "1");
		CLWContacts cLWContacts=clwContactsMapper.getByMobiles(map);
		return cLWContacts;
	}

	@Override
	public CLWContacts getByP(Map<String, String> map) {
		CLWContacts cLWContacts=clwContactsMapper.getByP(map);
		return cLWContacts;
	}
}
