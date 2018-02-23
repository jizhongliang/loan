package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserAuthModel;
import com.hwc.framework.modules.model.ClUserAuth;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;

/**
 * 2017/10/26.
 */
public interface ClUserAuthService extends Service<ClUserAuth> {
    public Response updateUserAuth(DUserAuth dUserAuth);

    public Response getUserAuth(Long userId);

    public DUserAuth getUserAuthById(Long userId);

    public Response getUserAuthState(Long userId);

    public Response getAuthTradeState(Long userId);

    public void updateUserAuthCreditState(DUserAuth dUserAuth);

    public void updateUserIdCardCreditState(DUserAuth dUserAuth);

    public void updateUserWorkInfoState(DUserAuth dUserAuth);

    public void updateUserContactState(DUserAuth dUserAuth);

    public void updateUserPhoneState(DUserAuth dUserAuth);

    public Response updateUserBankCardState(DUserAuth dUserAuth);

    List<DUserAuthModel> listModelPage(Map<String, Object> params);

    public boolean updateUserAuthByUserUUID(String uuid, DUserAuth dUserAuth);

	public Response updateAllAuth(DUserAuth dUserAuth);
}
