package com.hwc.framework.common;

import cn.freesoft.utils.FsUtils;

/**
 * Created by   on 2017/11/29.
 */
public class RateUtils {
    /**
     * 计算日利率  万分之5
     *
     * @param rate
     * @return
     */
    public static Double getRate(Double rate) {

        return FsUtils.divDouble(rate, 100D);
    }
}
