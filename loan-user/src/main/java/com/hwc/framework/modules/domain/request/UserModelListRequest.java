package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

import java.util.Date;

public class UserModelListRequest extends ItemsRequest {

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
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 注册客户端
     */
    private String registerClient;

    /**
     * 注册渠道
     */
    private Long channelId;

    /**
     * cl_user_base_info中用户状态
     */
    private String state;

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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date benginTime) {
        this.beginTime = benginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
