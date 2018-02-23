package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.mybatis.core.Mapper;

public interface ClBorrowProgressMapper extends Mapper<ClBorrowProgress> {
	/** 
	 * 根据userid查询
	 * @param userId
	 * @return
	 */
	ClBorrowProgress findByUserId(Long userId);
}