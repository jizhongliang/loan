package com.hwc.framework.modules.third.Impl;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.third.UserService;
import com.hwc.framework.utils.GsonUtil;
import com.hwc.loan.sdk.user.request.UserValidateTradePwdRequest;
import com.hwc.loan.sdk.user.response.UserValidateTradePwdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jzl on 2018/1/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate userClient;

    @Override
    public Response checkPwd(Long userId, String pwd) {
        String url = "http://loan-user/api/user/validateTradePwd";
        UserValidateTradePwdRequest request = new UserValidateTradePwdRequest();
        request.setId(userId);
        request.setTradePwd(pwd);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        //String result = userClient.getForObject(url,String.class);
        HttpEntity<UserValidateTradePwdRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Response> responseEntity = userClient.exchange(url, HttpMethod.POST, entity,Response.class);
        Response response = responseEntity.getBody();
        if (response.getSuccess()) {
            return Response.success();
        } else {
            return Response.fail(response.getMessage());
        }

    }
}
