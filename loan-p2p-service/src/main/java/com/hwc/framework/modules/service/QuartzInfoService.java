package com.hwc.framework.modules.service;

import com.github.pagehelper.Page;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.QuartzInfoModel;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;

/**
 * 定时任务详情Service
 */
public interface QuartzInfoService extends Service<ClQuartzInfo> {


	/**
	 * 保存定时任务数据
	 * @param qi
	 */
	boolean save(ClQuartzInfo qi);

	/**
	 * 修改定时任务
	 * @param search
	 * @return
	 */
	boolean update(Map<String, Object> search);

	/**
	 * 查询所有任务
	 * @param result
	 * @return
	 */
	List<ClQuartzInfo> list(Map<String, Object> result);

	/**
	 * 定时任务分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<QuartzInfoModel> page(Map<String, Object> searchMap, int current,
							   int pageSize);
	
	/**
	 * 据任务标识查询任务
	 * @return
	 */
	ClQuartzInfo findByCode(String code);
	
	/**
	 * 据条件查询定时任务详情
	 * @param paramMap
	 * @return
	 */
	ClQuartzInfo findSelective(Map<String, Object> paramMap);

	ClQuartzInfo getById(Long id);
	
}
