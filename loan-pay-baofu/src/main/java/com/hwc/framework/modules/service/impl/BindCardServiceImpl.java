package com.hwc.framework.modules.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BaoFuConstant;
import com.hwc.framework.common.BaofuUtils;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.model.ConfirmCardBindData;
import com.hwc.framework.modules.model.PreCardBindData;
import com.hwc.framework.modules.service.BindCardService;

import cn.freesoft.utils.FsUtils;

/**
 * Created by   on 2017/11/8.
 */
@Service
public class BindCardServiceImpl extends BaoFuServiceBase implements BindCardService {
    private static final Logger logger = LoggerFactory.getLogger(BindCardServiceImpl.class);

    public Response preBindCard(BindCardDomain cardDomain) throws UnsupportedEncodingException {
        try {

            if (cardDomain.getCat().equals("20")) {
                BaofuUtils.isCredit.set(false);
            }

            PreCardBindData data = new PreCardBindData();
            data.setMember_id(utils.getMember_id());
            data.setAcc_no(cardDomain.getCard_no());
            //data.setBiz_type(BaoFuConstant.BIZ_TYPE);
            data.setId_card(cardDomain.getId_no());
            data.setMobile(cardDomain.getMobile());
            data.setId_holder(cardDomain.getId_holder());
            data.setCard_type(
                getBankCardCode(cardDomain.getBank_name(), cardDomain.getBank_code()));
            data.setTerminal_id(utils.getTerminal_id());
            data.setTrade_date(FsUtils.formatDateTime(new Date(), "yyyyMMddHHmmss"));
            data.setTrans_id(cardDomain.getOrder_no());
            // data.setTrans_serial_no("S" + System.currentTimeMillis() + FsUtils.randomNumeric(3));

            // data.setReq_reserved(cardDomain.getUser_id() + "");

            Map<String, String> HeadPostParam = getBindCardParams(data);
            // HeadPostParam.put("txn_sub_type", data.getTxn_sub_type());

            Map<String, String> header = new HashMap<>();
            payReqLogService.doSaveRequest("pre_bind", cardDomain.getOrder_no(),
                JSON.toJSONString(data), HeadPostParam, cardDomain.getBorrow_id(),
                cardDomain.getUser_id(), cardDomain.getIp());
            HttpResponse response = doPost(utils.getPre_bind_card_url(), HeadPostParam);

            Response<BankCardAuthoriztionRespBean> res = getBingCardRepsonse(response,
                data.getTrans_id(), "P");

            return res;

        } catch (Exception ex) {
            logger.error("bindcard error", ex);
            return Response.fail(ex.getMessage());
        }

    }

    public Response<BankCardAuthoriztionRespBean> confirmBind(BindCardDomain domain) throws Exception {

        if (domain.getCat().equals("20")) {
            BaofuUtils.isCredit.set(false);
        }

        ConfirmCardBindData data = new ConfirmCardBindData();
        data.setMember_id(utils.getMember_id());
        //data.setBiz_type(BaoFuConstant.BIZ_TYPE);
        data.setTerminal_id(utils.getTerminal_id());
        data.setTrade_date(FsUtils.formatDateTime(new Date(), BaoFuConstant.DATE_FORMART));
        data.setTrade_no(domain.getOrder_no());
        // data.setTrans_id(domain.getOrder_no());
        // data.setTrans_serial_no("S" + System.currentTimeMillis());
        data.setSms_code(domain.getSms_code());
        data.setReq_reserved(domain.getUser_id() + "");

        Map<String, String> HeadPostParam = getBindCardParams(data);//明文参数

        // HeadPostParam.put("txn_sub_type", data.getTxn_sub_type());

        Map<String, String> header = new HashMap<>();
        payReqLogService.doSaveRequest("confirm_bind", domain.getOrder_no(),
            JSON.toJSONString(data), HeadPostParam, domain.getBorrow_id(), domain.getUser_id(),
            domain.getIp());
        HttpResponse response = doPost(utils.getConfirm_bind_card_url(), HeadPostParam);
        return getBingCardRepsonse(response, domain.getOrder_no(), "C");

    }

    @Override
    protected Response getResp(JSONObject jsonObject) {
        /**
         * 预绑卡
         */
        if (jsonObject.getString("type").equals("P")) {
            if (jsonObject.getInteger("code").equals(2)) {
                BankCardAuthoriztionRespBean bean = new BankCardAuthoriztionRespBean();
                bean.setOrder_no(jsonObject.getString("trade_no"));
                bean.setState("I");
                bean.setPay_channel(BaoFuConstant.PAY_CODE);
                return Response.success(bean);
            } else {
                return Response.fail(jsonObject.getString("desc"));
            }
        } else {
            if (jsonObject.getInteger("code").equals(0)) {
                BankCardAuthoriztionRespBean bean = new BankCardAuthoriztionRespBean();
                bean.setState("O");
                bean.setAgree_no(jsonObject.getString("trade_no"));
                bean.setPay_channel(BaoFuConstant.PAY_CODE);
                return Response.success(bean);
            } else {
                return Response.fail(jsonObject.getString("desc"));
            }

        }

    }
}
