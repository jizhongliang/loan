/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.framework.modules.third;

import com.alibaba.fastjson.JSONObject;

/**
 * 身份证信息获取client
 * @author jinlilong
 * @version $Id: IdCardService.java, v 0.1 2018年2月1日 上午10:26:52 jinlilong Exp $
 */
public interface IdCardClient {

    /**
     * 身份正面照信息解析获取
     * @param iamge
     * @return
     */
    public JSONObject getidCardInfo(String iamge);

    /**
     * 校验身份证反面照是否正确
     * @param iamge
     * @return
     */
    public boolean validateCardBackInfo(String iamge);

}
