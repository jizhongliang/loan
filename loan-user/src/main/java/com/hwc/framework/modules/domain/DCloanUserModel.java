package com.hwc.framework.modules.domain;

import java.util.Date;

public class DCloanUserModel {

    private Long id;
    /**
     * 客户表 外键
     */
    private Long userId;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户类型
     */
    private String cat;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 注册客户端
     */
    private String registerClient;

    /**
     * 渠道名称
     */
    private long channelId;

    /**
     * 渠道名称
     */
    private String channelName;


    private String mktName;

    /**
     *
     */
    private String uuid;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 身份证地址
     */
    private String idAddr;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddr;

    /**
     * 性别
     */
    private String sex;

    /**
     * 居住地址
     */
    private String liveAddr;

    /**
     * 居住时长
     */
    private String liveTime;

    /**
     * 婚姻状况
     */
    private String marryState;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 民族
     */
    private String national;

    /**
     * 学历
     */
    private String education;

    /**
     * 工作年限
     */
    private String workingYears;

    /**
     * 月薪范围
     */
    private String salary;

    private String state;

    /**
     * create_time
     */
    private String createTime;

    /**
     * update_time
     */
    private String updateTime;

    /**
     * 获取主键Id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键Id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户表外键
     *
     * @return userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置客户表外键
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取登录名
     *
     * @return loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取注册时间
     *
     * @return registTime
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册时间
     *
     * @param registTime
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * 获取注册客户端
     *
     * @return registerClient
     */
    public String getRegisterClient() {
        return registerClient;
    }

    /**
     * 设置注册客户端
     *
     * @param registerClient
     */
    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient;
    }

    /**
     * 获取渠道名称
     *
     * @return channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     *
     * @param channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取
     *
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置
     *
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取邀请码
     *
     * @return invitationCode
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * 设置邀请码
     *
     * @param invitationCode
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    /**
     * 获取真实姓名
     *
     * @return realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取证件号码
     *
     * @return idNo
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置证件号码
     *
     * @param idNo
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取身份证地址
     *
     * @return idAddr
     */
    public String getIdAddr() {
        return idAddr;
    }

    /**
     * 设置身份证地址
     *
     * @param idAddr
     */
    public void setIdAddr(String idAddr) {
        this.idAddr = idAddr;
    }

    /**
     * 获取公司名称
     *
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取公司地址
     *
     * @return companyAddr
     */
    public String getCompanyAddr() {
        return companyAddr;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddr
     */
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    /**
     * 获取性别
     *
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取居住地址
     *
     * @return liveAddr
     */
    public String getLiveAddr() {
        return liveAddr;
    }

    /**
     * 设置居住地址
     *
     * @param liveAddr
     */
    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    /**
     * 获取居住时长
     *
     * @return liveTime
     */
    public String getLiveTime() {
        return liveTime;
    }

    /**
     * 设置居住时长
     *
     * @param liveTime
     */
    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    /**
     * 获取婚姻状况
     *
     * @return marryState
     */
    public String getMarryState() {
        return marryState;
    }

    /**
     * 设置婚姻状况
     *
     * @param marryState
     */
    public void setMarryState(String marryState) {
        this.marryState = marryState;
    }

    /**
     * 获取手机号码
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * 获取民族
     *
     * @return national
     */
    public String getNational() {
        return national;
    }

    /**
     * 设置民族
     *
     * @param national
     */
    public void setNational(String national) {
        this.national = national;
    }

    /**
     * 获取学历
     *
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取工作年限
     *
     * @return workingYears
     */
    public String getWorkingYears() {
        return workingYears;
    }

    /**
     * 设置工作年限
     *
     * @param workingYears
     */
    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    /**
     * 获取月薪范围
     *
     * @return salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * 设置月薪范围
     *
     * @param salary
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * 获取state
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 设置state
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取create_time
     *
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置create_time
     *
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取update_time
     *
     * @return updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置update_time
     *
     * @param updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the channelId
     */
    public long getChannelId() {
        return channelId;
    }

    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }


    public String getMktName() {
        return mktName;
    }

    public void setMktName(String mktName) {
        this.mktName = mktName;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
