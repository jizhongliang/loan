package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.common.NidGenerator;
import com.hwc.framework.modules.domain.AuthApplyBean;
import com.hwc.framework.modules.model.AuthApplyModel;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.AuthApplyService;
import com.hwc.framework.modules.service.ClBankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by   on 2017/11/2.
 */
@Service
public class AuthApplyServiceImpl implements AuthApplyService {
    private static final Logger logger = LoggerFactory.getLogger(AuthApplyServiceImpl.class);

    @Autowired
    private ClBankCardService bankCardService;
    @Autowired
    private ArcSysConfigService sysConfigService;



//    public void doAuthApply(AuthApplyBean bean) {
//        String orderNo = NidGenerator.getPayOrderNo();
//
//        AuthApplyModel authApply = new AuthApplyModel(orderNo);
//        authApply.setUser_id(bean.getUid());
//        Map<String, Object> repaymentPlanMap = new HashMap<String, Object>();
//
//
//        repaymentPlanMap.put("repaymentPlan", bean.getRepayment_plan());
//        authApply.setRepayment_plan(JSONObject.toJSONString(repaymentPlanMap));
//        authApply.setRepayment_no(bean.getRepayment_no());
//        JSONObject smsParams = new JSONObject();
//        smsParams.put("contract_type", sysConfigService.getConfig("title"));
//        smsParams.put("cContact_way", sysConfigService.getConfig("phone"));
//
//        authApply.setSms_param(JSONObject.toJSONString(smsParams));
//
//        ClBankCard card = bankCardService.getBankCard(FsUtils.l(bean.getUser_id()));
//        authApply.setNo_agree(card.getAgreeNo());
//        authApply = (AuthApplyModel) lianlianService.authApply(authApply);
//        if (authApply.checkReturn()) {
//            logger.info("Borrow", bean.getRepayment_no(), "授权成功");
//        } else {
//            logger.info("Borrow" + bean.getRepayment_no() + "授权失败,原因：" + authApply.getRet_msg());
//        }
//    }
}
