package com.hwc.framework.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwc.framework.modules.dao.ClQuartzLogMapper;
import com.hwc.framework.modules.model.ClQuartzLog;
import com.hwc.framework.modules.model.QuartzLogModel;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.modules.service.QuartzLogService;
import com.hwc.mybatis.core.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuartzLogServiceImpl extends AbstractService<ClQuartzLogMapper, ClQuartzLog> implements QuartzLogService {
    @Override
    public int save(ClQuartzLog ql) {
        return this.mapper.insert(ql);
    }

    @Override
    public Page<QuartzLogModel> page(Map<String, Object> searchMap, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<QuartzLogModel> list = this.mapper.page(searchMap);
        return (Page<QuartzLogModel>)list;
    }
}
