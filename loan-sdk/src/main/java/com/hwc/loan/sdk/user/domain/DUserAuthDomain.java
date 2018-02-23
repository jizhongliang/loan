package com.hwc.loan.sdk.user.domain;

import lombok.Data;

@Data
public class DUserAuthDomain {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户标识
     */
    private Long userId;

    /**
     * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String idState;

    /**
     * 紧急联系人状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String contactState;

    /**
     * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String bankCardState;

    /**
     * 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String workInfoState;

    /**
     * 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String otherInfoState;

    /**
     * 人行征信，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String creditState;

    /**
     * 运营商，10未认证/未完善，20认证中/完善中，30已认证/已完善
     */
    private String phoneState;

}