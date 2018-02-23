package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.model.CLDownloadCount;

public interface CLDownloadCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CLDownloadCount record);

    int insertSelective(CLDownloadCount record);

    CLDownloadCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CLDownloadCount record);

    int updateByPrimaryKey(CLDownloadCount record);
}