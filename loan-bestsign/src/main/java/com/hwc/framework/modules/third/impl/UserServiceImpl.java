package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.hwc.base.exception.ServiceException;
import com.hwc.base.sdk.core.Client;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import com.hwc.loan.sdk.user.request.UserAuthTradeStateRequest;
import com.hwc.loan.sdk.user.request.UserBaseInfoGetOneRequest;
import com.hwc.loan.sdk.user.request.UserUpdateBankCardStatRequest;
import com.hwc.loan.sdk.user.response.UserAuthTradeStateResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;
import com.hwc.loan.sdk.user.response.UserUpdateBankCardStateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by   on 2017/11/6.
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
                return new DCloanUserDomain();
            }
        }catch (Exception ex) {
            logger.error("获取用户信息错误", ex);
            //throw new ServiceException("该用户未完成认证");
            return new DCloanUserDomain();
        }

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
}
