package com.hwc.framework.modules.service;

import java.math.BigDecimal;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DContactsModel;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.domain.DUserModel;
import com.hwc.framework.modules.model.ClUser;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClUserService extends Service<ClUser> {

     public Response registerUser(DUser dUser);

    public Response wxRegisterUser(DUser dUser);

    public Response loginUser(DUser dUser);

    public Response wxCheckAccount(DUser dUser);

    public Response wxLoginUser(DUser dUser);
    public Response loginout(DUser dUser);
    public Response wxBindLogin(DUser dUser);

    public Response vCodeLoginUser(DUser dUser);

    public Response forgetPwd(DUser dUser);

    public Response updatePwd(DUser dUser);

    public Response getUserCat(Long userId);

    public Response updateCat(DUser dUser);

    public Response setTradePwd(DUser dUser);

    public Response changeTradePwd(DUser dUser);
    public Response changeDLPwd(DUser dUser);

    public Response validateTradePwd(DUser dUser);

    public Response getTradeState(Long userId);
    void updateIsLogin(Long userId,String isLogin);

    public Response validateUser(DUserBaseInfo dUserBaseInfo);

    public Response resetTradePwd(DUserModel dUserModel);

    public DUser getUserById(Long userId);

    public DUser getUserByUuid(String uuid);

    public DUser getUserByLoginName(String loginName);

    public Response isSetTradePwd(DUser DUser);

    public Response uploadContacts(DContactsModel contactsModel);

    public Response uploadMessage(DContactsModel contactsModel);

    public Response resetTradePwdPreCheck(DUserModel dUserModel);

    /**
     * 根据用户的openId获取用户的信息
     * @param openId
     * @return
     */
    public Response queryUserData(String openId);


    /**
     * 根据手机号获取该用户利率
     * @param phone
     * @return
     */
    double getRateByPhone(String phone);

    /**
     * 根据userId获取该用户利率
     * @param userId
     * @return
     */
    double getRateByUserId(Long userId);

    public Response getCode(Integer id);

	public BigDecimal getRateByPhoneBig(String loginName);

	public BigDecimal getRateByUserIdBig(Long userId);
    public Response queryIsRegister(String loginName);
    public ClUser findUserByParameter(String loginName, String s);
}
