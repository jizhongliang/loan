package com.hwc.framework.modules.service.impl;

import com.hwc.framework.modules.dao.ClOpinionMapper;
import com.hwc.framework.modules.model.ClOpinion;
import com.hwc.framework.modules.service.ClOpinionService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 2017/10/30.
 */
@Service
public class ClOpinionServiceImpl extends AbstractService<ClOpinionMapper, ClOpinion> implements ClOpinionService {
    private static final Logger logger = LoggerFactory.getLogger(ClOpinionServiceImpl.class);


}
