package com.hwc.framework.modules.third.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.base.sdk.core.Client;
import com.hwc.base.sdk.core.ResponseBase;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import com.hwc.loan.sdk.user.request.UpdateUserBaseinfoCWRequest;
import com.hwc.loan.sdk.user.request.UserAuthTradeStateRequest;
import com.hwc.loan.sdk.user.request.UserBaseInfoGetOneRequest;
import com.hwc.loan.sdk.user.request.UserBorrowRateBigRequest;
import com.hwc.loan.sdk.user.request.UserBorrowRateRequest;
import com.hwc.loan.sdk.user.request.UserUpdateBankCardStatRequest;
import com.hwc.loan.sdk.user.request.UserUpdateStatRequest;
import com.hwc.loan.sdk.user.request.UserValidateTradePwdRequest;
import com.hwc.loan.sdk.user.response.UpdateUserBaseInfoCWResponse;
import com.hwc.loan.sdk.user.response.UserAuthTradeStateResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;
import com.hwc.loan.sdk.user.response.UserUpdateBankCardStateResponse;
import com.hwc.loan.sdk.user.response.UserValidateTradePwdResponse;

import cn.freesoft.utils.FsUtils;

/**
 * Created by on 2017/11/6.
 */
@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IHwcCache cache;

	@Autowired
	Client userClient;

	public DCloanUserDomain getUserInfo(Long userId) {
		try {
			String key = "users:id:" + userId;
			if (cache.exists(key)) {
				return cache.get(key, DCloanUserDomain.class);
			} else {
				UserBaseInfoGetOneRequest rq = new UserBaseInfoGetOneRequest();
				rq.setId(userId);
				UserBaseInfoGetOneResponse response = userClient.invoke(rq);
				if (response.getSuccess()) {
					DCloanUserDomain domain = response.getData();
					if (FsUtils.strsNotEmpty(domain.getIdNo())) {
						Long expire = FsUtils.getDaySpan(new Date());
						cache.set(key, expire, domain);
					}
					return response.getData();
				}
			}
		} catch (Exception ex) {
			logger.error("获取用户信息错误", ex);
		}
		throw new ServiceException("该用户未完成认证");

	}

	public DUserStateDomian userIsAuth(Long userId) {
		try {
			String key = "users:auth:id:" + userId;
			if (cache.exists(key)) {
				return cache.get(key, DUserStateDomian.class);
			}
			DUserStateDomian domian = new DUserStateDomian();
			UserAuthTradeStateRequest rq = new UserAuthTradeStateRequest();
			rq.setId(userId);
			UserAuthTradeStateResponse response = userClient.invoke(rq);
			logger.info(JSON.toJSONString(response));
			if (response.getSuccess()) {
				Map<String, Object> map = (Map) response.getData();
				domian.setAuth(FsUtils.b(map.get("authState")));
				domian.setTrade_pwd(FsUtils.b(map.get("tradeState")));
				if (domian.isAuth() && domian.isTrade_pwd()) {
					Long expire = FsUtils.getDaySpan(new Date());
					cache.set(key, expire, domian);
				}
			}
			return domian;
		} catch (Exception ex) {
			logger.error("userIsAuth", ex);
			return new DUserStateDomian();
		}
	}

	@Override
	public void userBankCardAuth(Long userId) {
		UserUpdateBankCardStatRequest request = new UserUpdateBankCardStatRequest();
		request.setBankCardState("30");
		request.setUserId(userId);
		UserUpdateBankCardStateResponse response = userClient.invoke(request);
		if (FsUtils.strsEmpty(response) || !response.getSuccess()) {
			logger.error("用户银行认证更新失败:" + userId);
		}
	}

	@Override
	public Double getBorrowRateByUserId(Long userId) {
		UserBorrowRateRequest request = new UserBorrowRateRequest();
		request.setUserId(userId);
		ResponseBase response = userClient.invoke(request);
		Map<String,Object> data=(Map<String,Object>) response.getData();
		return Double.valueOf(data.get("borrowRate").toString());
	}
	
	@Override
	public BigDecimal getBorrowRateByUserIdBigDecimal(Long userId) {
		UserBorrowRateBigRequest request = new UserBorrowRateBigRequest();
		request.setUserId(userId);
		ResponseBase response = userClient.invoke(request);
		Map<String,Object> data=(Map<String,Object>) response.getData();
		return new BigDecimal((String)data.get("borrowRate"));
	}
	
	@Override
	public Double getBorrowRateByPhone(String loginName) {
		UserBorrowRateRequest request = new UserBorrowRateRequest();
		request.setLoginName(loginName);
		ResponseBase response = userClient.invoke(request);
		Map<String,Object> data=(Map<String,Object>) response.getData();
		return Double.valueOf(data.get("borrowRate").toString());
	}
	
	@Override
	public Response checkPwd(Long userId, String pwd) {
		UserValidateTradePwdRequest request = new UserValidateTradePwdRequest();
		request.setId(userId);
		request.setTradePwd(pwd);
		UserValidateTradePwdResponse response = userClient.invoke(request);
		if (response.getSuccess()) {
			return Response.success();
		} else {
			return Response.fail(response.getMessage());
		}

	}
	/** 
	 * 车位
	 */
	@Override
	public void userBankCardAuthCW(Long userId) {
		UserUpdateStatRequest request = new UserUpdateStatRequest();
		request.setBankCardState("30");
		request.setIdState("30");
		request.setContactState("30");
		request.setPhoneState("30");
		request.setUserId(userId);
		UserUpdateBankCardStateResponse response = userClient.invoke(request);
		if (FsUtils.strsEmpty(response) || !response.getSuccess()) {
			logger.error("更新失败:" + userId);
		}
	}
	/** 
	 * 车位保存用户信息
	 */
	@Override
	public void updateUserBaseinfo(Long user_id, String userName, String id_no,String phone) {
		UpdateUserBaseinfoCWRequest updateUserBaseinfoCWRequest =new UpdateUserBaseinfoCWRequest();
		updateUserBaseinfoCWRequest.setIdNo(id_no);
		updateUserBaseinfoCWRequest.setRealName(userName);
		updateUserBaseinfoCWRequest.setPhone(phone);
		updateUserBaseinfoCWRequest.setUserId(user_id);
		UpdateUserBaseInfoCWResponse response =userClient.invoke(updateUserBaseinfoCWRequest);
		if(FsUtils.strsEmpty(response) || !response.getSuccess()){
			logger.error("updateUserBaseinfo更新失败:" + user_id);
		}
		
	}

	@Override
	public DCloanUserDomain getUserInfoByUserId(Long userId) {
		try {
			UserBaseInfoGetOneRequest rq = new UserBaseInfoGetOneRequest();
			rq.setId(userId);
			UserBaseInfoGetOneResponse response = userClient.invoke(rq);
			if (response.getSuccess()) {
				return response.getData();
			}
		} catch (Exception ex) {
			logger.error("获取用户信息错误", ex);
		}
		throw new ServiceException("该用户未完成认证");
	}

}
