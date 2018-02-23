package com.hwc.framework.modules.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.common.aliyun.ons.HwcOnsContext;
import com.hwc.framework.modules.service.BestSignUsersService;
import com.hwc.framework.modules.third.BestSignService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2017/12/18.
 * 信用借款
 */

public class CreditBorrowConsumer extends OnsConsumerBase {
    @Override
    protected String getConsumerId() {
        return null;
    }

    @Override
    protected String getTags() {
        return null;
    }

    @Override
    protected boolean doConsume(HwcOnsContext context) {
        return false;
    }
//    private static final Logger logger = LoggerFactory.getLogger(UserAuthConsumer.class);
//    @Value("${ons.credit.borrow.consumer.authId}")
//    private String id;
//    @Value("${ons.credit.borrow.consumer.authTag}")
//    private String tag;
//
//    @Value("${best.sign.signers}")
//    private String singers;
//
//    @Value("${best.sign.owner}")
//    private String account;
//    @Value("${best.sign.fid}")
//    private String fid;
//    @Autowired
//    private BestSignService bestSignService;
//
//    @Override
//    protected String getConsumerId() {
//        return id;
//    }
//
//    @Override
//    protected String getTags() {
//        return tag;
//    }
//
//
//    @Autowired
//    private IHwcCache cache;
//    @Autowired
//    private BestSignUsersService bestSignUsersService;
//
//
//    @Override
//    protected boolean doConsume(HwcOnsContext context) {
//        JSONObject jsonObject = (JSONObject) context.getData();//
//        JSONObject userInfo = null;
//        if (context.getUserProperties().containsKey("users")) {
//            userInfo = jsonObject.parseObject(context.getUserProperties().getProperty("users"));
//        }
//        JSONObject contract = new JSONObject();
//        contract.put("fid", fid);
//        contract.put("title", jsonObject.getString("id"));
//        contract.put("description", "信用分期协议");
//        JSONArray elements = JSONObject.parseArray("http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit.json");
//        contract.put("elements", elements);
//        contract.put("userId", account);
//        //添加合同元素
//        JSONObject jsonContract = bestSignService.addPDFElements(contract);
//        if (jsonContract.getInteger("errno").equals(0)) {
//            //获取新的文件号
//            contract.put("fid", jsonContract.getJSONObject("data").getString("fid"));
//            //创建合同
//            jsonContract = bestSignService.createContract(contract);
//            if (jsonContract.getInteger("errno").equals(0)) {
//                JSONObject jsonObject3 = jsonObject.getJSONObject("data");
//                String contractId = jsonObject3.getString("contractId");
//                JSONObject xx = new JSONObject();
//                xx.put("contractId", contractId);
//                xx.put("userId", jsonObject.getString("userId"));
//                JSONArray array = new JSONArray();
//                array.add(jsonObject.getString("userId"));
//                xx.put("signers", array);
//                //添加签署者
//                JSONObject jsonObject2 = bestSignService.addSigners(xx);
//                xx.put("signType", "U");
//                //设置签署者位置
//                setSignerConfig(xx);
//                // 获取手工签署地址
//                JSONObject jsonObject11 = bestSignService.getSignURL(xx);
//            }
//
//        }
//        return false;
//    }
//
//    private JSONObject setSignerConfig(JSONObject jsonObject) {
//        JSONObject object2 = new JSONObject();
//        object2.put("userId", jsonObject.getString("userId"));
//
//        JSONArray array_config = new JSONArray();
//        //用户签署位置
//        if (jsonObject.getString("signType").equals("U")) {
//            JSONObject p = new JSONObject();
//            p.put("pageNum", "7");
//            p.put("x", "0.327");
//            p.put("y", "0.536");
//            array_config.add(p);
//        }
//        // 财位签署位置
//        else if (jsonObject.getString("signType").equals("C")) {
//            JSONObject p = new JSONObject();
//            p.put("pageNum", "7");
//            p.put("x", "0.397");
//            p.put("y", "0.736");
//            array_config.add(p);
//        } else if (jsonObject.getString("signType").equals("J")) {
//            JSONObject p = new JSONObject();
//            p.put("pageNum", "7");
//            p.put("x", "0.427");
//            p.put("y", "0.636");
//            array_config.add(p);
//        }
//        object2.put("position", array_config);
//        object2.put("contractId", jsonObject.getString("contractId"));
//        JSONObject jsonObject4 = bestSignService.setSignerConfig(object2);
//        return jsonObject4;
//    }

}
