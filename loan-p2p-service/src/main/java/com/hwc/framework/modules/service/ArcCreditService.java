package com.hwc.framework.modules.service;

import com.hwc.base.exception.ServiceException;
import com.hwc.framework.modules.model.ArcCredit;

/**
 * Created by jzl on 2018/1/8.
 */
public interface ArcCreditService {

    void increaseQuota(Long userId, Double amount);

    ArcCredit getCreditByUserid(Long userId) throws ServiceException;
}
