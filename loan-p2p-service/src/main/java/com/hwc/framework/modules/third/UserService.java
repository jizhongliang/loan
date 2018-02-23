package com.hwc.framework.modules.third;

import com.hwc.base.api.Response;

/**
 * Created by jzl on 2018/1/9.
 */
public interface UserService {

    Response checkPwd(Long userId, String pwd);
}
