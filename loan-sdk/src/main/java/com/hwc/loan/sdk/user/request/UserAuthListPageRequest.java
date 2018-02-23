package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.response.UserAuthListPageResponse;

import java.util.Date;


public class UserAuthListPageRequest extends ItemsRequest<UserAuthListPageResponse> {

    public static final String METHOD = "/mana/user/authListPage";

    public UserAuthListPageRequest() {
        super(METHOD);
    }

    /**
     * 手机
     */
    private String phone;

    /**
     * 身份证
     */
    private String idNo;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 类型
     */
    private String cat;

    /**
     * 身份认证状态
     */
    private String idState;

    /**
     * 紧急联系人状态
     */
    private String contactState;

    /**
     * 银行卡状态
     */
    private String bankCardState;

    /**
     * 工作信息状态
     */
    private String workInfoState;

    /**
     * 人行认证状态
     */
    private String creditState;

    public String getCreditState() {
        return creditState;
    }

    public void setCreditState(String creditState) {
        this.creditState = creditState;
    }

    public String getIdState() {
        return idState;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    public String getContactState() {
        return contactState;
    }

    public void setContactState(String contactState) {
        this.contactState = contactState;
    }

    public String getBankCardState() {
        return bankCardState;
    }

    public void setBankCardState(String bankCardState) {
        this.bankCardState = bankCardState;
    }

    public String getWorkInfoState() {
        return workInfoState;
    }

    public void setWorkInfoState(String workInfoState) {
        this.workInfoState = workInfoState;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
