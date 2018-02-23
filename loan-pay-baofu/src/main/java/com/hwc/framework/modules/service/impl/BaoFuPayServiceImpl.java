package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.framework.common.BaoFuConstant;
import com.hwc.framework.common.RsaCodingUtil;
import com.hwc.framework.common.SecurityUtil;

import com.hwc.framework.modules.domain.PayDataDomain;
import com.hwc.framework.modules.domain.PayQueryDomain;
import com.hwc.framework.modules.domain.PayRespBean;
import com.hwc.framework.modules.model.*;

import com.hwc.framework.modules.service.BaoFuPayService;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.awt.dnd.DropTargetAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by   on 2017/11/8.
 */
@Service
public class BaoFuPayServiceImpl extends BaoFuServiceBase implements BaoFuPayService {
    private static final Logger logger = LoggerFactory.getLogger(BaoFuPayServiceImpl.class);

    public void loanSplit(PayDataDomain data) throws Exception {
        TransContent<TransReqBF0040004> transContent = new TransContent<TransReqBF0040004>(utils.getData_type());

        List<TransReqBF0040004> trans_reqDatas = new ArrayList<TransReqBF0040004>();
        TransHead trans_head = new TransHead();
        trans_head.setTrans_count("1");
        trans_head.setTrans_totalMoney(FsUtils.roundDouble(data.getAmount(), 2) + "");
        transContent.setTrans_head(trans_head);

        TransReqBF0040004 transReqData = new TransReqBF0040004();
        transReqData.setTrans_no(data.getOrder_no());
        transReqData.setTrans_money(trans_head.getTrans_totalMoney());
        transReqData.setTo_acc_name(data.getReal_name());
        transReqData.setTo_acc_no(data.getBank_card_no());
        transReqData.setTo_bank_name(data.getBank_name());
        transReqData.setTo_pro_name("");
        transReqData.setTo_city_name("");
        transReqData.setTo_acc_dept("");
        transReqData.setTrans_card_id(data.getId_no());
        transReqData.setTrans_mobile(data.getMobile());
        trans_reqDatas.add(transReqData);

        transContent.setTrans_reqDatas(trans_reqDatas);

        Map<String, String> query = getParams(transContent);//明文参数

        HttpResponse response = doPost("https://public.baofoo.com/baofoo-fopay/pay/BF0040004.do", query);
        String PostString = EntityUtils.toString(response.getEntity(), "utf-8");
        TransContent<TransRespBF0040004> str2Obj = new TransContent<TransRespBF0040004>(transContent.getData_type());
        if (PostString.contains("trans_content")) {
            TransContent<TransRespBF0040004> jsonObject = (TransContent<TransRespBF0040004>) str2Obj
                    .str2Obj(PostString, TransRespBF0040004.class);

        } else {
            PostString = RsaCodingUtil.decryptByPublicKey(PostString, RsaCodingUtil.getPublicKey());
            PostString = SecurityUtil.Base64Decode(PostString);
            TransContent<TransRespBF0040004> jsonObject = (TransContent<TransRespBF0040004>) str2Obj
                    .str2Obj(PostString, TransRespBF0040004.class);
            System.out.println(PostString);
        }
    }

    @Override
    public Response loan(PayDataDomain data) throws Exception {
        TransContent<TransReqBF0040001> transContent = new TransContent<TransReqBF0040001>(
                utils.getData_type());

        List<TransReqBF0040001> trans_reqDatas = new ArrayList<TransReqBF0040001>();

        TransReqBF0040001 transReqData = new TransReqBF0040001();
        transReqData.setTrans_no(data.getOrder_no());
        transReqData.setTrans_money(FsUtils.roundDouble(data.getAmount(), 2) + "");
        transReqData.setTo_acc_name(data.getReal_name());
        transReqData.setTo_acc_no(data.getBank_card_no());
        transReqData.setTo_bank_name(data.getBank_name());
        transReqData.setTo_pro_name("");
        transReqData.setTo_city_name("");
        transReqData.setTo_acc_dept("");
        transReqData.setTrans_summary("");
        transReqData.setTrans_card_id(data.getId_no());
        transReqData.setTrans_mobile(data.getMobile());
        trans_reqDatas.add(transReqData);
        transContent.setTrans_reqDatas(trans_reqDatas);
        String bean2XmlString = transContent.obj2Str(transContent);
        Map<String, String> query = getParams(bean2XmlString);//明文参数

        payReqLogService.doSaveRequest("loan", data.getOrder_no(), bean2XmlString, query, data.getBorrow_id(), data.getUser_id(), data.getIp());
        HttpResponse response = doPost(utils.getPay_001_url(), query);
        String PostString = EntityUtils.toString(response.getEntity(), "utf-8");
        logger.info("loan:{}",PostString);
        TransContent<TransRespBF0040001> str2Obj = new TransContent<TransRespBF0040001>(utils.getData_type());
        if (PostString.contains("trans_content")) {
            // 我报文错误处理
            str2Obj = (TransContent<TransRespBF0040001>) str2Obj
                    .str2Obj(PostString, TransRespBF0040001.class);
            payReqLogService.updateResponse(data.getOrder_no(), PostString, "1");
            return Response.fail().setMessage(str2Obj.getTrans_head().getReturn_msg());
            //业务逻辑判断
        } else {
            PostString = RsaCodingUtil.decryptByPublicKey(PostString, RsaCodingUtil.getPublicKey());
            PostString = SecurityUtil.Base64Decode(PostString);
            logger.info("dec loan:{}",PostString);
            str2Obj = (TransContent<TransRespBF0040001>) str2Obj
                    .str2Obj(PostString, TransRespBF0040001.class);
            payReqLogService.updateResponse(data.getOrder_no(), PostString, str2Obj.getTrans_head().getReturn_code());
            if (BaoFuConstant.PAY_SUCCESS_CODE.equals(str2Obj.getTrans_head().getReturn_code())) {
                TransRespBF0040001 respBF0040001 = str2Obj.getTrans_reqDatas().get(0);
                PayRespBean bean = new PayRespBean();
                //需要去查询订单 或者等消息回调
                bean.setState("Q");
                bean.setAmount(FsUtils.d(respBF0040001.getTrans_money()));
                bean.setBatch_id(respBF0040001.getTrans_batchid());
                bean.setOrder_no(respBF0040001.getTrans_no());
                bean.setPay_channel(BaoFuConstant.PAY_CODE);
                bean.setBank_card_no(respBF0040001.getTo_acc_no());

                return Response.success(bean);

            } else {
                return Response.fail(str2Obj.getTrans_head().getReturn_msg());
            }

            //业务逻辑判断
        }

    }

    public Response queryLoan(PayQueryDomain data) throws Exception {
        TransContent<TransReqBF0040002> transContent = new TransContent<TransReqBF0040002>(
                utils.getData_type());

        List<TransReqBF0040002> trans_reqDatas = new ArrayList<TransReqBF0040002>();
        if (data.getOrder_no().contains(",")) {
            String[] os = data.getOrder_no().split(",");
            for (String o : os) {
                if (FsUtils.strsNotEmpty(o)) {
                    TransReqBF0040002 transReqData = new TransReqBF0040002();
                    transReqData.setTrans_batchid("");
                    transReqData.setTrans_no(o);
                    trans_reqDatas.add(transReqData);
                }
            }

        } else {
            TransReqBF0040002 transReqData = new TransReqBF0040002();
            transReqData.setTrans_batchid("");
            transReqData.setTrans_no(data.getOrder_no());
            trans_reqDatas.add(transReqData);
        }
        transContent.setTrans_reqDatas(trans_reqDatas);
        String bean2XmlString = transContent.obj2Str(transContent);
        Map<String, String> query = getParams(bean2XmlString);//明文参数
        logger.info("pay查询：" + data.getOrder_no() + ":data:" + JSON.toJSONString(query));
        // payReqLogService.doSaveRequest(data.getService_name(), data.getOrder_no(), bean2XmlString, query, data.getBorrow_id(), data.getUser_id(), data.getIp());
        HttpResponse response = doPost(utils.getPay_query_url(), query);
        if (200 == response.getStatusLine().getStatusCode()) {
            String PostString = EntityUtils.toString(response.getEntity(), "utf-8");
            TransContent<TransRespBF0040002> str2Obj = new TransContent<TransRespBF0040002>(utils.getData_type());

            if (PostString.contains("trans_content")) {
                // 我报文错误处理
                str2Obj = (TransContent<TransRespBF0040002>) str2Obj
                        .str2Obj(PostString, TransRespBF0040002.class);
                logger.error("pay查询结果失败：" + data.getOrder_no() + ":data:" + PostString);
                return Response.fail().setMessage(str2Obj.getTrans_head().getReturn_msg());

                //业务逻辑判断
            } else {
                PostString = RsaCodingUtil.decryptByPublicKey(PostString, RsaCodingUtil.getPublicKey());
                PostString = SecurityUtil.Base64Decode(PostString);
                str2Obj = (TransContent<TransRespBF0040002>) str2Obj
                        .str2Obj(PostString, TransRespBF0040002.class);
                logger.info("pay查询结果成功：" + data.getOrder_no() + ":data:" + PostString);
                if (BaoFuConstant.PAY_SUCCESS_CODE.equals(str2Obj.getTrans_head().getReturn_code())) {
                    List<PayRespBean> beans = new ArrayList<>();
                    for (TransRespBF0040002 TransRespBF0040002 : str2Obj.getTrans_reqDatas()) {
                        PayRespBean bean = new PayRespBean();
                        if (TransRespBF0040002.getState().equals("1")) {
                            //需要去查询订单 或者等消息回调
                            bean.setState("O");
                        } else if (TransRespBF0040002.getState().equals("0")) {
                            //暂时先用 后面正式 修改过来
                            bean.setState("Q");
                        } else if (TransRespBF0040002.getState().equals("-1")) {
                            //转账失败
                            bean.setState("F");
                        } else {
                            //退款成功
                            bean.setState("R");
                        }
                        bean.setAmount(FsUtils.d(TransRespBF0040002.getTrans_money()));
                        bean.setBatch_id(TransRespBF0040002.getTrans_batchid());
                        bean.setOrder_no(TransRespBF0040002.getTrans_no());
                        bean.setPay_channel(BaoFuConstant.PAY_CODE);
                        bean.setBank_card_no(TransRespBF0040002.getTo_acc_no());
                        bean.setResponse_id(TransRespBF0040002.getTrans_orderid());
                        bean.setRemark(TransRespBF0040002.getTrans_remark());
                        beans.add(bean);
                    }
                    return Response.success(beans);
                } else {
                    return Response.fail().setMessage(str2Obj.getTrans_head().getReturn_msg());
                }
                //业务逻辑判断

            }
        } else {
            logger.error("pay查询结果 调用接口错误: http responsecode:" + data.getOrder_no() + ":data:" + response.getStatusLine().getStatusCode());
            return Response.fail().setMessage("调用接口错误: http responsecode:" + response.getStatusLine().getStatusCode());
        }
    }

    public Response payCallback(HttpServletRequest request) {
        try {
            String data_content = request.getParameter("data_content");//回调参数
            if (data_content.isEmpty()) {//判断参数是否为空
                logger.error("=====返回数据为空");
                return Response.fail("返回数据为空");
            }
            data_content = RsaCodingUtil.decryptByPublicKey(data_content, RsaCodingUtil.getPublicKey());
            data_content = SecurityUtil.Base64Decode(data_content);
            logger.info("paycallback：", data_content);

            TransContent<TransRespBF0040002> str2Obj = new TransContent<TransRespBF0040002>("xml");
            str2Obj = (TransContent<TransRespBF0040002>) str2Obj
                    .str2Obj(data_content, TransRespBF0040002.class);
            List<TransRespBF0040002> list = str2Obj.getTrans_reqDatas();
            List<PayRespBean> beans = new ArrayList<>();

            for (TransRespBF0040002 resp : list) {
                try {
                    PayRespBean bean = new PayRespBean();
                    if (resp.getState().equals("1")) {
                        //需要去查询订单 或者等消息回调
                        bean.setState("O");
                    } else if (resp.getState().equals("0")) {
                        bean.setState("Q");
                    } else if (resp.getState().equals("-1")) {
                        //转账失败
                        bean.setState("F");
                    } else {
                        //退款成功
                        bean.setState("R");
                    }
                    bean.setAmount(FsUtils.d(resp.getTrans_money()));
                    bean.setOrder_no(resp.getTrans_no());
                    bean.setBatch_id(resp.getTrans_batchid());
                    bean.setPay_channel("baofu");
                    bean.setRemark(resp.getTrans_remark());
                    bean.setBank_card_no(resp.getTo_acc_no());
                    bean.setResponse_id(resp.getTrans_orderid());
                    beans.add(bean);
                    payReqLogService.updateCallBackResponse(resp.getTrans_no(), JSON.toJSONString(resp), resp.getState());
                } catch (Exception ex) {
                    logger.error("payCallback  error", ex);
                }
            }
            //
            return Response.success(beans);
        } catch (Exception ex) {
            logger.error("payCallback:", ex);
            return Response.fail("返回数据为空");
        }
    }

    @Override
    protected Response getResp(JSONObject jsonObject) {
        return null;
    }
}
