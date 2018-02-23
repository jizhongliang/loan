package com.hwc.framework.modules.model;

/**
 * Created by   on 2017/11/1.
 * 连连支付 分期付 - 还款计划变更接口
 */
public class RepaymentPlanModel extends BasePaymentModel {


    /**
     * 用户唯一编号
     */
    private String user_id;

    /**
     * 还款计划 JSON格式
     */
    private String repayment_plan;

    /**
     * 还款状态  终止还款 terminal
     */
    private String repayment_state;

    /**
     * 还款计划编号
     */
    private String repayment_no;

    /**
     * 短信参数
     */
    private String sms_param;

    /**
     * 订单关联Id
     */
    private String correlationID;


    public RepaymentPlanModel(String orderNo) {
        super();
        this.setService("repaymentPlan");
        this.setOrderNo(orderNo);
        this.setSubUrl("https://repaymentapi.lianlianpay.com/repaymentplanchange.htm");
    }

    @Override
    public String[] signParamNames() {
        return new String[]{"oid_partner", "sign_type", "sign", "user_id",
                "repayment_plan", "repayment_state", "repayment_no",
                "sms_param"};
    }

    @Override
    public String[] reqParamNames() {
        return new String[]{"oid_partner", "sign_type", "sign", "user_id",
                "repayment_plan", "repayment_state", "repayment_no",
                "sms_param"};
    }


    @Override
    public String[] respParamNames() {
        return new String[]{"ret_code", "ret_msg", "sign_type", "sign", "correlationID"};
    }

    /**
     * 获取用户唯一编号
     *
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置用户唯一编号
     *
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取还款计划JSON格式
     *
     * @return repayment_plan
     */
    public String getRepayment_plan() {
        return repayment_plan;
    }

    /**
     * 设置还款计划JSON格式
     *
     * @param repayment_plan
     */
    public void setRepayment_plan(String repayment_plan) {
        this.repayment_plan = repayment_plan;
    }

    /**
     * 获取还款状态终止还款terminal
     *
     * @return repayment_state
     */
    public String getRepayment_state() {
        return repayment_state;
    }

    /**
     * 设置还款状态终止还款terminal
     *
     * @param repayment_state
     */
    public void setRepayment_state(String repayment_state) {
        this.repayment_state = repayment_state;
    }

    /**
     * 获取还款计划编号
     *
     * @return repayment_no
     */
    public String getRepayment_no() {
        return repayment_no;
    }

    /**
     * 设置还款计划编号
     *
     * @param repayment_no
     */
    public void setRepayment_no(String repayment_no) {
        this.repayment_no = repayment_no;
    }

    /**
     * 获取短信参数
     *
     * @return sms_param
     */
    public String getSms_param() {
        return sms_param;
    }

    /**
     * 设置短信参数
     *
     * @param sms_param
     */
    public void setSms_param(String sms_param) {
        this.sms_param = sms_param;
    }

    /**
     * 获取订单关联Id
     *
     * @return correlationID
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * 设置订单关联Id
     *
     * @param correlationID
     */
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }
}
