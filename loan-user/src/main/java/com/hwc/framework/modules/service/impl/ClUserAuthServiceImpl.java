package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.dao.ClUserAuthMapper;
import com.hwc.framework.modules.domain.DUserAuth;
import com.hwc.framework.modules.domain.DUserAuthModel;
import com.hwc.framework.modules.model.ClUserAuth;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/10/26.
 */
@Service
public class ClUserAuthServiceImpl extends AbstractService<ClUserAuthMapper, ClUserAuth> implements ClUserAuthService {
    private static final Logger logger = LoggerFactory.getLogger(ClUserAuthServiceImpl.class);

    @Autowired
    private ClUserService clUserService;

    @Autowired
    private ArcSysConfigService arcSysConfigService;

    @Override
    public Response updateUserAuth(DUserAuth dUserAuth) {
        if (FsUtils.strsEmpty(dUserAuth.getUserId(),dUserAuth)){
            return Response.fail("参数错误");
        }
        if (!FsUtils.strsEmpty(dUserAuth.getId())){
            this.mapper.updateByPrimaryKey(convertToClUserAuth(dUserAuth));
        }else {
            this.mapper.insert(convertToClUserAuth(dUserAuth));
        }
        return Response.success();
    }

    @Override
    public Response getUserAuth(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        ClUserAuth select = new ClUserAuth();
        select.setUserId(userId);
        ClUserAuth clUserAuth = this.mapper.selectOne(select);
        if (clUserAuth != null){
            DUserAuth dUserAuth = convertToDUserAuth(clUserAuth);
            return Response.success(dUserAuth);
        }else {
            return Response.fail();
        }
    }

    @Override
    public DUserAuth getUserAuthById(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return null;
        }
        ClUserAuth select = new ClUserAuth();
        select.setUserId(userId);
        ClUserAuth clUserAuth = this.mapper.selectOne(select);
        if (clUserAuth != null){
            DUserAuth dUserAuth = convertToDUserAuth(clUserAuth);
            return dUserAuth;
        }else {
            return null;
        }
    }

    @Override
    public Response getUserAuthState(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        ClUserAuth select = new ClUserAuth();
        select.setUserId(userId);
        ClUserAuth clUserAuth = this.mapper.selectOne(select);
        if (clUserAuth.getBankCardState().equals("30")
                && clUserAuth.getContactState().equals("30")
                && clUserAuth.getIdState().equals("30")
                && clUserAuth.getPhoneState().equals("30")){
            if ("T".equals(arcSysConfigService.getConfigDefault(Constant.USE_CREDIT,""))){
                if ( clUserAuth.getCreditState().equals("30")){
                    return Response.success("认证通过");
                }else {
                    return Response.fail("认证未通过");
                }
            }
            return Response.success("认证通过");
        }else {
            return Response.fail("认证未通过");
        }
    }

    @Override
    public Response getAuthTradeState(Long userId) {
        Map<String,Object> map = new HashMap();
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        Response responseAuth = this.getUserAuthState(userId);
        if (responseAuth.getSuccess()){
            map.put("authState",true);
        }else {
            map.put("authState",false);
        }

        Response responseTrade = this.clUserService.getTradeState(userId);
        if (responseTrade.getSuccess()){
            map.put("tradeState",true);
        }else {
            map.put("tradeState",false);
        }
        return Response.success(map);
    }


    @Override
    public void updateUserAuthCreditState(DUserAuth request) {
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setCreditState(request.getCreditState());
            this.update(update);
        }
    }

    @Override
    public void updateUserIdCardCreditState(DUserAuth request) {
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setIdState(request.getIdState());
            this.update(update);
        }
    }

    @Override
    public void updateUserWorkInfoState(DUserAuth request) {
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setWorkInfoState(request.getWorkInfoState());
            this.update(update);
        }
    }

    @Override
    public void updateUserContactState(DUserAuth request) {
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setContactState(request.getContactState());
            this.update(update);
        }
    }

    @Override
    public void updateUserPhoneState(DUserAuth request) {
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setPhoneState(request.getPhoneState());
            this.update(update);
        }
    }

    @Override
    public Response updateUserBankCardState(DUserAuth request) {
        if (FsUtils.strsEmpty(request.getUserId(),request.getBankCardState())){
            return Response.fail("参数错误");
        }
        DUserAuth dUserAuth = this.getUserAuthById(request.getUserId());
        if (dUserAuth != null) {
            ClUserAuth update = new ClUserAuth();
            update.setId(dUserAuth.getId());
            update.setUserId(request.getUserId());
            update.setBankCardState(request.getBankCardState());
            this.update(update);
            return Response.success();
        }else {
            return Response.fail("用户不存在");
        }

    }

    @Override
    public List<DUserAuthModel> listModelPage(Map<String, Object> params) {
        List<DUserAuthModel>  dUserAuthModelList = this.mapper.listUserAuthModelPage(params);
        if (dUserAuthModelList != null){
        }
        return  dUserAuthModelList;
    }

    @Override
    public boolean updateUserAuthByUserUUID(String uuid, DUserAuth dUserAuth) {

        return false;
    }

    private ClUserAuth convertToClUserAuth(DUserAuth dUserAuth){
        ClUserAuth clUserAuth = new ClUserAuth();
        clUserAuth.setId(dUserAuth.getUserId());
        clUserAuth.setUserId(dUserAuth.getUserId());
        clUserAuth.setIdState(dUserAuth.getIdState());
        clUserAuth.setContactState(dUserAuth.getContactState());
        clUserAuth.setBankCardState(dUserAuth.getBankCardState());
        clUserAuth.setWorkInfoState(dUserAuth.getWorkInfoState());
        clUserAuth.setOtherInfoState(dUserAuth.getOtherInfoState());
        clUserAuth.setCreditState(dUserAuth.getCreditState());
        clUserAuth.setPhoneState(dUserAuth.getPhoneState());
        return clUserAuth;
    }

    private DUserAuth convertToDUserAuth(ClUserAuth clUserAuth){
        DUserAuth dUserAuth = new DUserAuth();
        dUserAuth.setId(clUserAuth.getId());
        dUserAuth.setUserId(clUserAuth.getUserId());
        dUserAuth.setIdState(clUserAuth.getIdState());
        dUserAuth.setContactState(clUserAuth.getContactState());
        dUserAuth.setBankCardState(clUserAuth.getBankCardState());
        dUserAuth.setWorkInfoState(clUserAuth.getWorkInfoState());
        dUserAuth.setOtherInfoState(clUserAuth.getOtherInfoState());
        dUserAuth.setCreditState(clUserAuth.getCreditState());
        dUserAuth.setPhoneState(clUserAuth.getPhoneState());
        return dUserAuth;
    }

	@Override
	public Response updateAllAuth(DUserAuth dUserAuth) {
		DUserAuth dUser = this.getUserAuthById(dUserAuth.getUserId());
		dUserAuth.setId(dUser.getId());
		dUserAuth.setOtherInfoState(dUser.getOtherInfoState());
		dUserAuth.setWorkInfoState(dUser.getWorkInfoState());
		dUserAuth.setCreditState(dUser.getCreditState());
		ClUserAuth clUserAuth = convertToClUserAuth(dUserAuth);
		int a =  this.mapper.updateByPrimaryKey(clUserAuth);
		if(a>0){
			return Response.success();
		}
		return Response.fail();
	}
	
	

}
