package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BindCardDomain;

/**
 * Created by   on 2017/11/10.
 */
public interface CardAuthorizationGatewayService {
    public Response cardAuthorization(BindCardDomain domain);

    public Response confimCard(BindCardDomain domain);
    /** 
     * 车位预绑卡
     * @param bean
     * @return
     */
	public Response<BankCardAuthoriztionRespBean> cardAuthorizationCW(BindCardDomain bean);
	/** 
	 * 车位确定绑卡
	 * @param bean
	 * @return
	 */
	public Response<BankCardAuthoriztionRespBean> confimCardCW(BindCardDomain bean);
}
