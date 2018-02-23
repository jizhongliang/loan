package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.model.ClUserBaseInfo;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.Service;

/**
 * 2017/10/23.
 */
public interface ClUserBaseInfoService extends Service<ClUserBaseInfo> {

    public Response authenticationUserBaseInfo(DUserBaseInfo dUserBaseInfo);

    /**
     * 车位用户身份认证。 微信端专用。不需要认证人脸识别照片
     * @param dUserBaseInfo
     * @return
     */
    public Response authenticationParking(DUserBaseInfo dUserBaseInfo);

    public Response authenticationCw(DUserBaseInfo dUserBaseInfo);

    public Response updateUserBaseInfo(DUserBaseInfo dUserBaseInfo);

    /**
     * 插入dUserBaseInfo
     * @param dUserBaseInfo
     * @return
     */
    public Response insertUserBaseInfo(DUserBaseInfo dUserBaseInfo);

    public Response getByPhone(DCloanUserDomain dUserBaseInfo);

    /**
     * 根据身份证号查询baseInfo信息
     * @param dUserBaseInfo
     * @return
     */
    public Response getByIdNo(DCloanUserDomain dUserBaseInfo);

    /**
     * 根据userId更新用户基础信息
     * @param dUserBaseInfo
     * @return
     */
    public Response updateUserBaseInfoByUserId(DUserBaseInfo dUserBaseInfo);

    public DUserBaseInfo getUserBaseInfo(Long userId);

    public Response updateUserState(DUserBaseInfo dUserBaseInfo);

    public Response getUserBaseInfoApp(Long userId);

    public Response updateUserWorkInfo(DUserBaseInfo dUserBaseInfo);

    public Response getUserWorkInfo(Long userId);

    public Response getUserDetail(Long userId);

    public Response isUseCredit();

    public Response updateUserBaseInfoByUserIdCW(DUserBaseInfo dUserBaseInfo);

}
