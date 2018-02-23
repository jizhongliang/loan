/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.mybatis.core.Service;
import com.hwc.framework.modules.model.BestSignUsers;

/**
 * 类说明
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/18
 */
public interface BestSignUsersService extends Service<BestSignUsers> {

    void signUser(JSONObject jsonObject);

    BestSignUsers getUser(Long userId);
}
