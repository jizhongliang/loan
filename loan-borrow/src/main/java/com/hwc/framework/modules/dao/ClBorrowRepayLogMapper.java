package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.domain.RepayLogBean;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.mybatis.core.Mapper;

import java.util.List;
import java.util.Map;

public interface ClBorrowRepayLogMapper extends Mapper<ClBorrowRepayLog> {
    List<RepayLogBean> payLogList(Map<String, Object> map);

    ClBorrowRepayLog loadRepayLately(Long borrowId);
}