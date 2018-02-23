package com.hwc.framework.modules.model;

import com.hwc.framework.common.LianLianConstant;

/**
 * Created by   on 2017/11/1.
 *连连支付 分期付 - 解约授权
 */
public class CancelAuthSignModel extends  BasePaymentModel {

    /**
     * 用户唯一标识
     */
    private String user_id;

    /**
     * 支付方式  D 认证支付
     */
    private String pay_type;

    /**
     * 签约协议编号
     */
    private String no_agree;




    public CancelAuthSignModel(String orderNo ) {
        super( );
        this.setService("cancelAuthSign");
        this.setOrderNo(orderNo);
        this.setPay_type(LianLianConstant.PAY_TYPE_CERTIFIED);
        this.setSubUrl("https: //traderapi.lianlianpay.com/bankcardunbind.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[] { "oid_partner", "user_id", "platform", "pay_type",
                "sign_type", "sign", "no_agree" };

    }

    @Override
    public String[] reqParamNames() {
        return new String[] { "oid_partner", "user_id", "platform", "pay_type",
                "sign_type", "sign", "no_agree" };
    }

    @Override
    public String[] respParamNames() {
        return new String[] { "ret_code", "ret_msg", "sign_type", "sign" };
    }

    /**
     * 获取用户唯一标识
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户唯一标识
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取支付方式D认证支付
     * @return pay_type
     */
    public String getPay_type() {
        return pay_type;
    }

    /**
     * 设置支付方式D认证支付
     * @param pay_type
     */
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    /**
     * 获取签约协议编号
     * @return no_agree
     */
    public String getNo_agree() {
        return no_agree;
    }

    /**
     * 设置签约协议编号
     * @param no_agree
     */
    public void setNo_agree(String no_agree) {
        this.no_agree = no_agree;
    }

}
