package com.hwc.framework.modules.model;

import javax.persistence.*;

@Table(name = "cl_user_auth")
public class ClUserAuth {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "id_state")
    private String idState;

    /**
     * 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "contact_state")
    private String contactState;

    /**
     * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "bank_card_state")
    private String bankCardState;

    /**
     * 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "work_info_state")
    private String workInfoState;

    /**
     * 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "other_info_state")
    private String otherInfoState;

    /**
     * 人行征信认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "credit_state")
    private String creditState;


    /**
     * 运营商 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    @Column(name = "phone_state")
    private String phoneState;

    public String getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(String phoneState) {
        this.phoneState = phoneState;
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户标识
     *
     * @return user_id - 用户标识
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @return id_state - 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public String getIdState() {
        return idState;
    }

    /**
     * 设置身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @param idState 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public void setIdState(String idState) {
        this.idState = idState;
    }

    /**
     * 获取紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @return contact_state - 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public String getContactState() {
        return contactState;
    }

    /**
     * 设置紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @param contactState 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public void setContactState(String contactState) {
        this.contactState = contactState;
    }

    /**
     * 获取银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @return bank_card_state - 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public String getBankCardState() {
        return bankCardState;
    }

    /**
     * 设置银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @param bankCardState 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public void setBankCardState(String bankCardState) {
        this.bankCardState = bankCardState;
    }


    /**
     * 获取工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @return work_info_state - 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public String getWorkInfoState() {
        return workInfoState;
    }

    /**
     * 设置工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @param workInfoState 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public void setWorkInfoState(String workInfoState) {
        this.workInfoState = workInfoState;
    }

    /**
     * 获取更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @return other_info_state - 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public String getOtherInfoState() {
        return otherInfoState;
    }

    /**
     * 设置更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     *
     * @param otherInfoState 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    public void setOtherInfoState(String otherInfoState) {
        this.otherInfoState = otherInfoState;
    }

    /**
     * 获取人行征信认证状态 ，10
     *
     * @return credit_state - 人行征信认证状态 ，10
     */
    public String getCreditState() {
        return creditState;
    }

    /**
     * 设置人行征信认证状态 ，10
     *
     * @param creditState 人行征信认证状态 ，10
     */
    public void setCreditState(String creditState) {
        this.creditState = creditState;
    }
}