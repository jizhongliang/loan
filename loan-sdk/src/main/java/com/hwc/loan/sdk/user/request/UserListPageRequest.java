package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.response.UserListPageResponse;

import java.util.Date;


public class UserListPageRequest extends ItemsRequest<UserListPageResponse> {

    public static final String METHOD = "/mana/user/listPage";

    public UserListPageRequest() {
        super(METHOD);
    }

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户类型
     */
    private String cat;

    /**
     * 注册客户端
     */
    private String registerClient;

    /**
     * 渠道名称
     */
    private Long channelId;

    private Date beginTime;

    private Date endTime;

    /**
     * cl_user_base_info中用户状态
     */
    private String state;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getRegisterClient() {
        return registerClient;
    }

    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
