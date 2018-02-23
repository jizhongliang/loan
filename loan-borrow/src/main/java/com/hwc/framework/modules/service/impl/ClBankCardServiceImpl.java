package com.hwc.framework.modules.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.base.sdk.core.Client;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.common.aliyun.ons.producer.HwcOnsProducer;
import com.hwc.framework.common.BankUtil;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.dao.ClBankCardMapper;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BankCardBean;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.domain.CardAuthDataBean;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.CardAuthorizationGatewayService;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.request.AuthenticationCwRequest;
import com.hwc.loan.sdk.user.request.UserBaseInfoByIdNoRequest;
import com.hwc.loan.sdk.user.response.AuthenticationCwResponse;
import com.hwc.loan.sdk.user.response.UserBaseInfoByIdNoResponse;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/11/02.
 */
@Service
public class ClBankCardServiceImpl extends AbstractService<ClBankCardMapper, ClBankCard>
                                   implements ClBankCardService {
    private static final Logger             logger = LoggerFactory
        .getLogger(ClBankCardServiceImpl.class);

    @Autowired
    private CardAuthorizationGatewayService cardAuthorizationGatewayService;
    @Autowired
    private IHwcCache                       cache;
    @Autowired
    private UserService                     userService;
    @Autowired
    private ArcBasecodeService              basecodeService;

    private final String                    key    = "bank:userid:";
    @Autowired
    private Client                          client;

    @Autowired
    @Qualifier("smsProducer")
    private HwcOnsProducer                  producer;

    public Response authSignReturn(BindCardDomain bean) {

        DCloanUserDomain user = userService.getUserInfo(bean.getUser_id());
        if (FsUtils.strsEmpty(user.getIdNo())) {
            return Response.fail("请先完成实名认证!");
        }
        bean.setCat(user.getCat());

        if (FsUtils.strsEmpty(bean.getSms_code())) {
            if (FsUtils.strsEmpty(bean.getBank_code(), bean.getBank_name(), bean.getCard_no(),
                bean.getId_holder(), bean.getId_no(), bean.getMobile())) {
                return Response.fail("参数不完整，请重新输入");
            }
            bean.setOrder_no(NidGenerator.getPayOrderNo());
            bean.setId_no(user.getIdNo());
            bean.setId_holder(user.getRealName());

            if (!user.getPhone().equalsIgnoreCase(bean.getMobile().trim())) {
                return Response.fail("银行预留手机号与当前登录账号不符");
            }
            return cardAuthorizationGatewayService.cardAuthorization(bean);
        } else {
            if (FsUtils.strsEmpty(bean.getOrder_no(), bean.getSms_code())) {
                return Response.fail("验证码错误，请重新获取");
            }
            if (FsUtils.strsEmpty(bean.getSms_code())) {
                return Response.fail("短信验证码为空");
            }
            Response<BankCardAuthoriztionRespBean> response = cardAuthorizationGatewayService
                .confimCard(bean);
            if (response.getSuccess()) {
                doSaveBankCard(bean, response.getData());
                userService.userBankCardAuth(bean.getUser_id());
            } else {
                return Response.fail("验证码错误");
            }
            return response;
        }

    }

    private void doSaveBankCard(BindCardDomain bean, BankCardAuthoriztionRespBean respBean) {
        ClBankCard card = getBankCard(bean.getUser_id());
        if (FsUtils.strsEmpty(card)) {
            card = new ClBankCard();
            card.setUserId(bean.getUser_id());
            card.setBank(bean.getBank_name());
            card.setBankCode(bean.getBank_code());
            card.setCardNo(bean.getCard_no());
            card.setBindTime(new Date());
            card.setPhone(bean.getMobile());
            card.setAgreeNo(respBean.getAgree_no());
            card.setPayCode(respBean.getPay_channel());
            insert(card);
        } else {
            // card = new ClBankCard();
            card.setUserId(bean.getUser_id());
            card.setPayCode(respBean.getPay_channel());
            card.setBank(bean.getBank_name());
            card.setBankCode(bean.getBank_code());
            card.setCardNo(bean.getCard_no());
            card.setBindTime(new Date());
            card.setPhone(bean.getMobile());
            card.setAgreeNo(respBean.getAgree_no());
            update(card);
            Long expire = FsUtils.getDaySpan(new Date());
            cache.set(key + bean.getUser_id(), expire, card);
        }
    }

    public ClBankCard getBankCard(Long userId) {
        String nkey = key + userId;
        if (cache.exists(nkey)) {
            return cache.get(nkey, ClBankCard.class);
        } else {
            ClBankCard card = new ClBankCard();
            card.setUserId(userId);
            List<ClBankCard> cards = this.mapper.select(card);
            if (FsUtils.strsNotEmpty(cards) && !cards.isEmpty()) {
                ClBankCard c = cards.get(0);
                if (FsUtils.strsNotEmpty(c)) {
                    Long expire = FsUtils.getDaySpan(new Date());
                    cache.set(nkey, expire, c);
                }
                return c;
            } else
                return null;
        }
    }

    @Override
    public BankCardBean getBankCardBean(Long userid) {
        BankCardBean bean = new BankCardBean();
        ClBankCard bankCard = getBankCard(userid);
        if (FsUtils.strsNotEmpty(bankCard)) {
            bean.setPhone(bankCard.getPhone());
            bean.setBank(bankCard.getBank());
            bean.setCardNo(bankCard.getCardNo());
            bean.setAgreeNo(bankCard.getAgreeNo());
            bean.setBankCode(bankCard.getBankCode());
            DCloanUserDomain userinfo = userService.getUserInfo(userid);
            if (FsUtils.strsNotEmpty(userinfo)) {
                bean.setId_holder(userinfo.getRealName());
                bean.setId_no(userinfo.getIdNo());
            }
            return bean;
        } else {
            return null;
        }

    }

    @Override
    public Response<BankCardBean> authData(CardAuthDataBean card) {
        DCloanUserDomain userDomain = userService.getUserInfo(card.getUserId());
        if (FsUtils.strsEmpty(userDomain.getIdNo())) {
            return Response.fail("请先完成实名认证!");
        }
        BankCardBean bean = new BankCardBean();
        bean.setId_no(userDomain.getIdNo().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*"));
        bean.setId_holder(userDomain.getRealName());
        bean.setCardNo(card.getCardNo());
        String bankName = BankUtil.getNameOfBank(card.getCardNo());
        if (FsUtils.strsEmpty(bankName)) {
            return Response.fail("请输入正确的银行卡号!");
        }
        bean.setBankCode(getBankCode(bankName));
        bean.setBank(card.getBank());
        bean.setUserId(card.getUserId());
        bean.setChannel("baofu");
        return Response.success(bean);
    }

    private String getBankCode(String name) {

        List<BaseCode> list = basecodeService.getBasecodeList("bank_code");
        for (BaseCode code : list) {
            if (name.contains(code.getDescript())) {
                return code.getCode();
            }
        }
        throw new ServiceException("请更换银行卡！");
    }

    /** 
     * 车位预绑卡
     * 确定绑卡
     */
    @Override
    public Response<BankCardAuthoriztionRespBean> authSignReturnCW(BindCardDomain bean) {

        DCloanUserDomain user = userService.getUserInfo(bean.getUser_id());
        if (user == null) {
            return Response.fail("用户不存在");
        }

        //校验身份证账号是否已存在
        UserBaseInfoByIdNoRequest userBaseInfoByIdNoRequest = new UserBaseInfoByIdNoRequest();
        userBaseInfoByIdNoRequest.setIdNo(bean.getId_no());
        UserBaseInfoByIdNoResponse userBaseInfoByIdNoResponse = client
            .invoke(userBaseInfoByIdNoRequest);
        if (userBaseInfoByIdNoResponse != null && userBaseInfoByIdNoResponse.getData() != null) {
            return Response.fail("该身份证号已被绑定，无法重复绑定");
        }

        bean.setCat(user.getCat());
        if (FsUtils.strsEmpty(bean.getSms_code())) {
            if (FsUtils.strsEmpty(bean.getBank_code(), bean.getBank_name(), bean.getCard_no(),
                bean.getId_holder(), bean.getId_no(), bean.getMobile())) {
                return Response.fail("请重新输入正确的银行卡信息");
            }
            bean.setOrder_no(NidGenerator.getPayOrderNo());
            bean.setId_no(bean.getId_no());
            bean.setId_holder(bean.getId_holder());
            if (!user.getPhone().equalsIgnoreCase(bean.getMobile().trim())) {
                return Response.fail("银行预留手机号与当前登录账号不符");
            }
            return cardAuthorizationGatewayService.cardAuthorizationCW(bean);
        } else {
            if (FsUtils.strsEmpty(bean.getOrder_no())) {
                return Response.fail("验证码错误");
            }
            if (FsUtils.strsEmpty(bean.getSms_code())) {
                return Response.fail("短信验证码为空");
            }
            Response<BankCardAuthoriztionRespBean> response = cardAuthorizationGatewayService
                .confimCardCW(bean);
            if (response.getSuccess()) {
                doSaveBankCard(bean, response.getData());
                //保存用户信息
                userService.updateUserBaseinfo(bean.getUser_id(), bean.getId_holder(),
                    bean.getId_no(), bean.getMobile());
                userService.userBankCardAuthCW(bean.getUser_id());

                AuthenticationCwRequest request = new AuthenticationCwRequest();
                request.setIdNo(bean.getId_no());
                AuthenticationCwResponse authenticationCwResponse = client.invoke(request);
                logger.info("authenticationCwResponse,"
                            + JSONObject.toJSONString(authenticationCwResponse));
            }
            return response;
        }
    }

}
