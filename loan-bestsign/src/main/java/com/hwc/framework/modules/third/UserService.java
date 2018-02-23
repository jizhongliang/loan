package com.hwc.framework.modules.third;


import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;

/**
 * Created by   on 2017/11/6.
 */
public interface UserService {
    DCloanUserDomain getUserInfo(Long userId);

    DUserStateDomian userIsAuth(Long userId);

    void userBankCardAuth(Long userId);
}
