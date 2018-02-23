package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BindCardDomain;

import java.io.UnsupportedEncodingException;

/**
 * Created by   on 2017/11/8.
 */
public interface BindCardService {
    public Response preBindCard(BindCardDomain cardDomain) throws UnsupportedEncodingException;

    Response confirmBind(BindCardDomain domain) throws Exception;
}
