package com.hwc.framework.modules.service;

import com.github.pagehelper.Page;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.model.QuartzLogModel;
import com.hwc.mybatis.core.Service;

import java.util.Map;

/**
 * 定时任务记录Service
 */
public interface QuartzLogService extends Service<ClQuartzLog> {

	/**
	 * 保存日志
	 */
	int save(ClQuartzLog ql);

	Page<QuartzLogModel> page(Map<String, Object> searchMap, int current,
							  int pageSize);

}
