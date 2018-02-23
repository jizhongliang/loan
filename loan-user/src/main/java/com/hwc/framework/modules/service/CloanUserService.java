package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DCloanUserModel;
import com.hwc.framework.modules.model.CloanUserModel;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;


public interface CloanUserService extends Service<CloanUserModel> {
	/**
	 * 查询用户详细信息列表
	 * @param params
	 * @return
	 */
	List<DCloanUserModel> listModelPage(Map<String, Object> params);

	/**
	 * 根据ID查询用户详细信息
	 * @param userId
	 * @return
	 */
	Response getCloanUserById(Long userId);

	/**
	 * 根据ID查询用户详细信息
	 * @param userId
	 * @return
	 */
	DCloanUserModel getCloanUserOne(Long userId);

}
