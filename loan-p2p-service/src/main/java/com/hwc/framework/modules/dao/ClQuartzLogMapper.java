package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.model.QuartzLogModel;
import com.hwc.mybatis.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClQuartzLogMapper extends Mapper<ClQuartzLog> {

    /**
     * 据quartId查询最后执行时间
     * @param quartId
     * @return
     */
    String findLastTimeByQzInfoId(@Param("quartzId") Long quartId);

    /**
     * 日志查询
     * @param searchMap
     * @return
     */
    List<QuartzLogModel> page(Map<String, Object> searchMap);

}