package com.hwc.framework.modules.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * Created by   on 2017/11/2.
 */
@Data
public class AuthApplyBean {

    private List<JSONObject> repayment_plan;
    /**
     * 用户唯一编号
     */
    private String user_id;

    private String uid;
    /**
     * 版本号
     */
    private String api_version;

    /**
     * 还款编号
     */
    private String repayment_no;

    /**
     * 短信参数
     */
    private String sms_param;

    /**
     * 支付方式
     */
    private String pay_type;

    /**
     * 签约协议号
     */
    private String no_agree;

    /**
     * 订单关联Id
     */
    private String correlationID;
}
