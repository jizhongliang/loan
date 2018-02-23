package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DBankCard;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClBankCardService extends Service<ClBankCard> {

    public Response getBankCardList(Long userId);

    public Response updateUserBankCar(DBankCard dBankCard);

    public DBankCard getBankCardByUserId(Long userId);
}
