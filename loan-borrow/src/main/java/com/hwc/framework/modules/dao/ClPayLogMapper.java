package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClPayLog;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface ClPayLogMapper extends Mapper<ClPayLog> {

    List<ClPayLog> paylogList(Map<String, Object> map);
    ClPayLog getBydesc(Long borrowId);
    List<ClPayLog> getList(Long borrowId);
    /** 
     * 根据条件查询
     * @param param
     * @return
     */
	ClPayLog selectByParam(Map<String, Object> param);

	ClPayLog getLastRepayLog(Long repayId);
}