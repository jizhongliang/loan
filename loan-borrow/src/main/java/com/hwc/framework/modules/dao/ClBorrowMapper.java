package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface ClBorrowMapper extends Mapper<ClBorrow> {
    ClBorrow findCreditByUserIdAndState(Map<String,Object> map);
    
    /** 
     * 根据uerid查询最新的一条数据
     * @param userId
     * @return
     */
	ClBorrow findByUserId(Long userId);
	List<ClBorrow> getByUserId(Map<String, Object> map);
	void updates(Map<String,Object> map);
	 List<ClBorrow> getByUserIds(Map<String, Object> map);
    int getSuccessBorrowCountByUid(Map<String, Object> map);
}