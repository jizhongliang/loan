package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.domain.CLOpinion;

public interface CLOpinionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CLOpinion record);

    int insertSelective(CLOpinion record);

    CLOpinion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CLOpinion record);

    int updateByPrimaryKey(CLOpinion record);
}