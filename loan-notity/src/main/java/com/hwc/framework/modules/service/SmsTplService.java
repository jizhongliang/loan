package com.hwc.framework.modules.service;

import com.hwc.framework.modules.domain.DSmsTpl;

/**
 * 2017/10/18.
 */
public interface SmsTplService {

    public DSmsTpl findSmsTplByType(String type);
}
