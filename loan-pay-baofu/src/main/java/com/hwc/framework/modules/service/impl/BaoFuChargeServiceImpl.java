package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;

import com.hwc.framework.common.BaoFuConstant;
import com.hwc.framework.modules.domain.ChargeDataDomain;
import com.hwc.framework.modules.domain.ChargeQueryDomain;
import com.hwc.framework.modules.domain.ChargeRespBean;
import com.hwc.framework.modules.domain.SysConfig;
import com.hwc.framework.modules.model.ChargeData;
import com.hwc.framework.modules.model.ChargeQueryData;
import com.hwc.framework.modules.service.BaoFuChargeService;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by   on 2017/11/8.
 */
@Service
public class BaoFuChargeServiceImpl extends BaoFuServiceBase implements BaoFuChargeService {

    Logger logger= LoggerFactory.getLogger(BaoFuChargeServiceImpl.class);

    public Response charge(ChargeDataDomain domain) throws Exception {
        ChargeData data = new ChargeData();
        data.setMobile(domain.getMobile());
        data.setAcc_no(domain.getBank_no());
        data.setId_card(domain.getId_no());
        data.setId_holder(domain.getName());
        data.setPay_code(getBankCardCode(domain.getBank_name(), domain.getBank_code()));
        data.setTrade_date(domain.getTrade_date());
        data.setTrans_id(domain.getOrder_no());
        data.setTrans_serial_no("S" + System.currentTimeMillis() + FsUtils.randomNumeric(3));
        data.setTxn_amt(((int) FsUtils.mulDouble(domain.getAmount(), 100D)) + "");
        data.setMember_id(utils.getMember_id());
        data.setTerminal_id(utils.getTerminal_id());
        data.setAdditional_info(domain.getRepay_id());
        Map<String, String> HeadPostParam = getParams(data);//明文参数

        HeadPostParam.put("txn_sub_type", data.getTxn_sub_type());
        Map<String, String> header = new HashMap<>();
        payReqLogService.doSaveRequest(domain.getService_name(), domain.getOrder_no(), JSON.toJSONString(data), HeadPostParam, domain.getBorrow_id(), domain.getUser_id(), domain.getIp());
        HttpResponse response = doPost(utils.getRepay_url(), HeadPostParam);

        Response res = getRepsonse(response);
        return res;
    }

    @Override
    public Response chargeQuery(ChargeQueryDomain domain) throws Exception {
        ChargeQueryData data = new ChargeQueryData();
        data.setTerminal_id(utils.getTerminal_id());
        data.setMember_id(utils.getMember_id());
        data.setTrans_serial_no(domain.getOrder_no());
        data.setOrig_trans_id(domain.getTrans_serial_no());
        data.setOrig_trade_date(domain.getTrade_date());

        Map<String, String> HeadPostParam = getParams(data);//明文参数


        HeadPostParam.put("txn_sub_type", data.getTxn_sub_type());
        Map<String, String> header = new HashMap<>();
        payReqLogService.doSaveRequest(domain.getService_name(), domain.getOrder_no(), JSON.toJSONString(data), HeadPostParam, domain.getBorrow_id(), domain.getUser_id(), domain.getIp());
        HttpResponse response = doPost(utils.getRepay_query_url(), HeadPostParam);

        Response res = getRepsonse(response);
        return res;
    }

    @Override
    protected Response getResp(JSONObject jsonObject) {
        if (jsonObject.getString("txn_sub_type").equals("13")) {
            //代扣
            ChargeRespBean bean = new ChargeRespBean();
            bean.setPay_channel(BaoFuConstant.PAY_CODE);
            if (FsUtils.ArrayContains(new String[]{BaoFuConstant.BF00100, BaoFuConstant.BF00112, BaoFuConstant.BF00113, BaoFuConstant.BF00144, BaoFuConstant.BF00115, BaoFuConstant.BF00202}, jsonObject.getString("resp_code"))) {
                bean.setState("Q");
            } else if (BaoFuConstant.PAY_SUCCESS_CODE.equals(jsonObject.getString("resp_code"))) {
                bean.setState("O");
            } else {
                bean.setState("F");
                bean.setRemark(jsonObject.getString("resp_msg"));
            }
            bean.setOrder_no(jsonObject.getString("trans_no"));
            bean.setRepay_id(jsonObject.getString("additional_info"));
            bean.setOrig_trans_id(jsonObject.getString("trans_id"));
            bean.setAmount(FsUtils.divDouble(jsonObject.getDouble("succ_amt"), 100D));
            bean.setRemark(jsonObject.getString("resp_msg"));
            return Response.success(bean);
        } else {
            //代扣查询
            ChargeRespBean bean = new ChargeRespBean();
            bean.setPay_channel(BaoFuConstant.PAY_CODE);
            if ("S".equals(jsonObject.getString("order_stat"))) {
                bean.setState("O");
            } else {
                bean.setState(jsonObject.getString("order_stat"));
            }
            bean.setRemark(jsonObject.getString("resp_msg"));
            bean.setOrder_no(jsonObject.getString("trans_no"));
            bean.setOrig_trans_id(jsonObject.getString("orig_trans_id"));
            return Response.success(bean);
        }

    }
}
