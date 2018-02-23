package com.hwc.framework.modules.third;

import com.hwc.base.api.Response;
import com.hwc.loan.sdk.user.domain.BankCardBean;

/**
 * Created by jzl on 2018/1/17.
 */
public interface BankCardService {
    /**
     * huo获取银行卡信息
     * @param userId
     * @return
     */
    BankCardBean getBankCardInfo(Long userId);
}
