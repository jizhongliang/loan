package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.base.sdk.core.Client;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.third.BankCardService;
import com.hwc.loan.sdk.user.domain.BankCardBean;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.request.BankCardInfoRequest;
import com.hwc.loan.sdk.user.request.UserBaseInfoGetOneRequest;
import com.hwc.loan.sdk.user.request.UserValidateTradePwdRequest;
import com.hwc.loan.sdk.user.response.BankCardInfoResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoGetOneResponse;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by jzl on 2018/1/17.
 */
@Service
public class BankCardServiceImpl implements BankCardService {
    private static Logger logger = LoggerFactory.getLogger(BankCardServiceImpl.class);
    @Autowired
    Client borrowClient;

    @Autowired
    private IHwcCache cache;

    @Override
    public BankCardBean getBankCardInfo(Long userId) {

        /*String url = "http://loan-borrow/api/bank/card/getBankCardInfo";
        BankCardInfoRequest request = new BankCardInfoRequest();
        request.setId(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BankCardInfoRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Response> responseEntity = bankCardClient.exchange(url, HttpMethod.POST,
                entity, Response.class);
        Response response = responseEntity.getBody();
        return response;*/

        try {
            String key = "bankCardInfo:uid:" + userId;
            if (cache.exists(key)) {
                return cache.get(key, BankCardBean.class);
            } else {
                BankCardInfoRequest rq = new BankCardInfoRequest();
                rq.setId(userId);
                BankCardInfoResponse response = borrowClient.invoke(rq);
                if (response.getSuccess()) {
                    BankCardBean domain = response.getData();
                    if (FsUtils.strsNotEmpty(domain.getCardNo())) {
                        Long expire = FsUtils.getDaySpan(new Date());
                        cache.set(key, expire, domain);
                    }
                    return response.getData();
                }
                return null;
            }
        }catch (Exception ex) {
            logger.error("获取用户信息错误", ex);
            //throw new ServiceException("该用户未完成认证");
            return null;
        }

    }
}
