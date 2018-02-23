package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BankCardBean;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.domain.CardAuthDataBean;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.mybatis.core.Service;

/**
 * 2017/11/02.
 */
public interface ClBankCardService extends Service<ClBankCard> {
    /**
     * 绑定银行卡
     * @param bean
     */
    public Response authSignReturn(BindCardDomain bean);

    /**
     * 获取银行卡信息
     * @param userId
     * @return
     */
    public ClBankCard getBankCard(Long userId);

    /**
     * 获取银行卡号
     * @param userid
     * @return
     */
    BankCardBean getBankCardBean(Long userid);

    /**
     * 获取绑定银行卡数据
     * @param card
     * @return
     */
    Response<BankCardBean> authData(CardAuthDataBean card);
    /** 
     * 车位绑卡
     * @param card
     * @return
     */
	public Response<BankCardAuthoriztionRespBean> authSignReturnCW(BindCardDomain card);

}
