package com.hwc.framework.modules.third;


import java.math.BigDecimal;

import com.hwc.base.api.Response;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;

/**
 * Created by   on 2017/11/6.
 */
public interface UserService {
    DCloanUserDomain getUserInfo(Long userId);

    DUserStateDomian userIsAuth(Long userId);

    void userBankCardAuth(Long userId);

    Response checkPwd(Long userId, String pwd);

	Double getBorrowRateByPhone(String loginName);

	Double getBorrowRateByUserId(Long userId);

	void userBankCardAuthCW(Long user_id);

	void updateUserBaseinfo(Long user_id, String id_holder, String id_no, String phone);

	//不从缓存里取
    DCloanUserDomain getUserInfoByUserId(Long userId);

	BigDecimal getBorrowRateByUserIdBigDecimal(Long userId);
}
