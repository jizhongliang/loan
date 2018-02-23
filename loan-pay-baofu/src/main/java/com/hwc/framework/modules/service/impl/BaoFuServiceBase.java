package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import cn.freesoft.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.framework.common.BaoFuConstant;
import com.hwc.framework.common.BaofuUtils;
import com.hwc.framework.common.RsaCodingUtil;
import com.hwc.framework.common.SecurityUtil;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.ClPayReqLogService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by   on 2017/11/9.
 */
public abstract class BaoFuServiceBase {
    private static final Logger logger = LoggerFactory.getLogger(BaoFuServiceBase.class);
    @Autowired
    protected BaofuUtils utils;
    @Autowired
    private ArcBasecodeService basecodeService;
    @Autowired
    public ClPayReqLogService payReqLogService;

    public Map<String, String> getParams(Object data) throws UnsupportedEncodingException {
        Map<String, String> HeadPostParam = new HashMap<String, String>();
        HeadPostParam.put("version", utils.getVersion());
        HeadPostParam.put("member_id", utils.getMember_id());
        HeadPostParam.put("terminal_id", utils.getTerminal_id());
        HeadPostParam.put("txn_type", utils.getTxn_type());
        HeadPostParam.put("data_type", utils.getData_type());
        String base64str = "";
        if (data instanceof String) {
            base64str = SecurityUtil.Base64Encode(data + "");
        } else {
            base64str = SecurityUtil.Base64Encode(JSON.toJSONString(data));
        }
        String data_content = RsaCodingUtil.encryptByPrivateKey(base64str, utils.getPrivateKey());
        HeadPostParam.put("data_content", data_content);
        return HeadPostParam;
    }

    public Map<String, String> getBindCardParams(Object data) throws UnsupportedEncodingException {
        Map<String, String> HeadPostParam = new HashMap<String, String>();
        //HeadPostParam.put("version", utils.getVersion());
        HeadPostParam.put("member_id", utils.getMember_id());
        HeadPostParam.put("terminal_id", utils.getTerminal_id());
        //HeadPostParam.put("txn_type", utils.getTxn_type());
        HeadPostParam.put("data_type", utils.getData_type());
        String base64str = "";
        if (data instanceof String) {
            base64str = SecurityUtil.Base64Encode(data + "");
        } else {
            base64str = SecurityUtil.Base64Encode(JSON.toJSONString(data));
        }
        String data_content = RsaCodingUtil.encryptByPrivateKey(base64str, utils.getPrivateKey());
        HeadPostParam.put("data_content", data_content);
        return HeadPostParam;
    }

    public Response getRepsonse(HttpResponse response) throws IOException {
        String postString = EntityUtils.toString(response.getEntity(), "utf-8");
        logger.info("baofu response:{}", postString);
        postString = RsaCodingUtil.decryptByPublicKey(postString, utils.getPublicKey());
        postString = SecurityUtil.Base64Decode(postString);
        JSONObject jsonObject = JSON.parseObject(postString);
        logger.info("encode baofu response:{}", postString);
        String trans_id = jsonObject.getString("trans_id");

        payReqLogService.updateResponse(trans_id, postString, jsonObject.getString("resp_code"));
        if (BaoFuConstant.PAY_SUCCESS_CODE.equals(jsonObject.getString("resp_code")) || FsUtils.ArrayContains(new String[]{BaoFuConstant.BF00100, BaoFuConstant.BF00112, BaoFuConstant.BF00113, BaoFuConstant.BF00144, BaoFuConstant.BF00115, BaoFuConstant.BF00202}, jsonObject.getString("resp_code"))) {
            return getResp(jsonObject);
        } else {
            //  //
            logger.info("error message:{}", jsonObject.getString("resp_msg"));
            return Response.fail(jsonObject.getString("resp_msg"));
        }
    }

    public Response getBingCardRepsonse(HttpResponse response, String trans_id, String type) throws IOException {
        String postString = EntityUtils.toString(response.getEntity(), "utf-8");
        logger.info("baofu response:{}", postString);
//        postString = RsaCodingUtil.decryptByPublicKey(postString, utils.getPublicKey());
//        postString = SecurityUtil.Base64Decode(postString);
        JSONObject jsonObject = JSON.parseObject(postString);
        boolean success = jsonObject.getBoolean("success");
        // payReqLogService.updateResponse(trans_id, postString, jsonObject.getString("resp_code"));

        if (success) {
            JSONObject data = jsonObject.getJSONObject("data");
            //认证
            data.put("type", type);
            if (type.equals("P")) {
                if (data.getInteger("code").equals(2)) {
                    payReqLogService.updateResponse(trans_id, postString, "0000");
                    return getResp(data);
                } else {
                    payReqLogService.updateResponse(trans_id, postString, data.getString("code"));
                    if(data.getString("desc").equals("亲，认证信息不一致")){
                    	return Response.fail("绑卡信息有误");
                    }
                    return Response.fail(data.getString("desc"));
                }
            } else {
                //确认绑卡
                if (data.getInteger("code").equals(0)) {
                    payReqLogService.updateResponse(trans_id, postString, "0000");
                    return getResp(data);
                } else {
                    payReqLogService.updateResponse(trans_id, postString, data.getString("code"));
                    if(data.getString("desc").equals("亲，认证信息不一致")){
                    	return Response.fail("绑卡信息有误");
                    }
                    return Response.fail(data.getString("desc"));
                }
            }

        } else {
            payReqLogService.updateResponse(trans_id, postString, jsonObject.getString("errorCode"));
            String errorMsg=jsonObject.getString("errorMsg");
            if(errorMsg!=null &&errorMsg!=""){
            	if(errorMsg.equals("身份证号格式有误")){
            		return Response.fail("请输入正确的身份证号码");
            	}else if(errorMsg.equals("暂不支持该银行卡校验")){
            		return Response.fail("银行卡暂不支持，请更换银行卡");
            	}
            }
            return Response.fail(jsonObject.getString("errorMsg"));
        }


//        if (BaoFuConstant.PAY_SUCCESS_CODE.equals(jsonObject.getString("resp_code")) || FsUtils.ArrayContains(new String[]{BaoFuConstant.BF00100, BaoFuConstant.BF00112, BaoFuConstant.BF00113, BaoFuConstant.BF00144, BaoFuConstant.BF00115, BaoFuConstant.BF00202}, jsonObject.getString("resp_code"))) {
//
//        } else {
//
//        }
    }

//        if (BaoFuConstant.PAY_SUCCESS_CODE.equals(jsonObject.getString("resp_code")) || BaoFuConstant.PAY_REPEAT_SUCCESS_CODE.equals(jsonObject.getString("resp_code"))) {
//            return Response.success(jsonObject);
//        } else {
//


    protected String getBankCardCode(String name, String code) {
        String ext = basecodeService.getExtValue("bank_code", code);
        if (FsUtils.strsEmpty(ext)) {
            throw new ServiceException("银行卡暂不支持，请更换银行卡");
        }
        return ext;
    }

    public HttpResponse doPost(String url, Map<String, String> querys) throws Exception {
        Map<String, String> header = new HashMap<>();
        return HttpUtils.doPost("", url, header, querys, "");
    }

    protected abstract Response getResp(JSONObject jsonObject);
}
