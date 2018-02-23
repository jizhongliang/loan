package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_user_base_info")
public class ClUserBaseInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户表 外键
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 年龄 
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 民族
     */
    private String national;

    /**
     * 证件号码
     */
    @Column(name = "id_no")
    private String idNo;

    /**
     * 身份证地址
     */
    @Column(name = "id_addr")
    private String idAddr;

    /**
     * 自拍(人脸识别照片)
     */
    @Column(name = "living_img")
    private String livingImg;

    /**
     * 身份证头像
     */
    @Column(name = "ocr_img")
    private String ocrImg;

    /**
     * 身份证正面
     */
    @Column(name = "front_img")
    private String frontImg;

    /**
     * 身份证反面
     */
    @Column(name = "back_img")
    private String backImg;

    /**
     * 用户签名图片
     */
    @Column(name = "signature_img")
    private String signatureImg;

    /**
     * 学历
     */
    private String education;

    /**
     * 婚姻状况
     */
    @Column(name = "marry_state")
    private String marryState;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司电话
     */
    @Column(name = "company_phone")
    private String companyPhone;

    /**
     * 公司地址
     */
    @Column(name = "company_addr")
    private String companyAddr;

    /**
     * 公司详细地址
     */
    @Column(name = "company_detail_addr")
    private String companyDetailAddr;

    /**
     * 公司坐标(经度,纬度)
     */
    @Column(name = "company_coordinate")
    private String companyCoordinate;

    /**
     * 月薪范围
     */
    private String salary;

    /**
     * 工作年限
     */
    @Column(name = "working_years")
    private String workingYears;

    /**
     * 工作照片
     */
    @Column(name = "working_img")
    private String workingImg;

    /**
     * 居住时长
     */
    @Column(name = "live_time")
    private String liveTime;

    /**
     * 居住地址
     */
    @Column(name = "live_addr")
    private String liveAddr;

    /**
     * 居住详细地址
     */
    @Column(name = "live_detail_addr")
    private String liveDetailAddr;

    /**
     * 居住地坐标，(经度,纬度)
     */
    @Column(name = "live_coordinate")
    private String liveCoordinate;

    /**
     * 运营商服务密码
     */
    @Column(name = "phone_server_pwd")
    private String phoneServerPwd;

    /**
     * 注册地址
     */
    @Column(name = "register_addr")
    private String registerAddr;

    /**
     * 注册地坐标，(经度,纬度)
     */
    @Column(name = "register_coordinate")
    private String registerCoordinate;

    /**
     * 是否黑名单 ，10是20不是 30 骗贷
     */
    private String state;

    /**
     * 拉黑原因
     */
    @Column(name = "black_reason")
    private String blackReason;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取客户表 外键
     *
     * @return user_id - 客户表 外键
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置客户表 外键
     *
     * @param userId 客户表 外键
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取年龄 
     *
     * @return age - 年龄 
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄 
     *
     * @param age 年龄 
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取民族
     *
     * @return national - 民族
     */
    public String getNational() {
        return national;
    }

    /**
     * 设置民族
     *
     * @param national 民族
     */
    public void setNational(String national) {
        this.national = national;
    }

    /**
     * 获取证件号码
     *
     * @return id_no - 证件号码
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * 设置证件号码
     *
     * @param idNo 证件号码
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * 获取身份证地址
     *
     * @return id_addr - 身份证地址
     */
    public String getIdAddr() {
        return idAddr;
    }

    /**
     * 设置身份证地址
     *
     * @param idAddr 身份证地址
     */
    public void setIdAddr(String idAddr) {
        this.idAddr = idAddr;
    }

    /**
     * 获取自拍(人脸识别照片)
     *
     * @return living_img - 自拍(人脸识别照片)
     */
    public String getLivingImg() {
        return livingImg;
    }

    /**
     * 设置自拍(人脸识别照片)
     *
     * @param livingImg 自拍(人脸识别照片)
     */
    public void setLivingImg(String livingImg) {
        this.livingImg = livingImg;
    }

    /**
     * 获取身份证头像
     *
     * @return ocr_img - 身份证头像
     */
    public String getOcrImg() {
        return ocrImg;
    }

    /**
     * 设置身份证头像
     *
     * @param ocrImg 身份证头像
     */
    public void setOcrImg(String ocrImg) {
        this.ocrImg = ocrImg;
    }

    /**
     * 获取身份证正面
     *
     * @return front_img - 身份证正面
     */
    public String getFrontImg() {
        return frontImg;
    }

    /**
     * 设置身份证正面
     *
     * @param frontImg 身份证正面
     */
    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    /**
     * 获取身份证反面
     *
     * @return back_img - 身份证反面
     */
    public String getBackImg() {
        return backImg;
    }

    /**
     * 设置身份证反面
     *
     * @param backImg 身份证反面
     */
    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取婚姻状况
     *
     * @return marry_state - 婚姻状况
     */
    public String getMarryState() {
        return marryState;
    }

    /**
     * 设置婚姻状况
     *
     * @param marryState 婚姻状况
     */
    public void setMarryState(String marryState) {
        this.marryState = marryState;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取公司电话
     *
     * @return company_phone - 公司电话
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * 设置公司电话
     *
     * @param companyPhone 公司电话
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * 获取公司地址
     *
     * @return company_addr - 公司地址
     */
    public String getCompanyAddr() {
        return companyAddr;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddr 公司地址
     */
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    /**
     * 获取公司详细地址
     *
     * @return company_detail_addr - 公司详细地址
     */
    public String getCompanyDetailAddr() {
        return companyDetailAddr;
    }

    /**
     * 设置公司详细地址
     *
     * @param companyDetailAddr 公司详细地址
     */
    public void setCompanyDetailAddr(String companyDetailAddr) {
        this.companyDetailAddr = companyDetailAddr;
    }

    /**
     * 获取公司坐标(经度,纬度)
     *
     * @return company_coordinate - 公司坐标(经度,纬度)
     */
    public String getCompanyCoordinate() {
        return companyCoordinate;
    }

    /**
     * 设置公司坐标(经度,纬度)
     *
     * @param companyCoordinate 公司坐标(经度,纬度)
     */
    public void setCompanyCoordinate(String companyCoordinate) {
        this.companyCoordinate = companyCoordinate;
    }

    /**
     * 获取月薪范围
     *
     * @return salary - 月薪范围
     */
    public String getSalary() {
        return salary;
    }

    /**
     * 设置月薪范围
     *
     * @param salary 月薪范围
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * 获取工作年限
     *
     * @return working_years - 工作年限
     */
    public String getWorkingYears() {
        return workingYears;
    }

    /**
     * 设置工作年限
     *
     * @param workingYears 工作年限
     */
    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    /**
     * 获取工作照片
     *
     * @return working_img - 工作照片
     */
    public String getWorkingImg() {
        return workingImg;
    }

    /**
     * 设置工作照片
     *
     * @param workingImg 工作照片
     */
    public void setWorkingImg(String workingImg) {
        this.workingImg = workingImg;
    }

    /**
     * 获取居住时长
     *
     * @return live_time - 居住时长
     */
    public String getLiveTime() {
        return liveTime;
    }

    /**
     * 设置居住时长
     *
     * @param liveTime 居住时长
     */
    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    /**
     * 获取居住地址
     *
     * @return live_addr - 居住地址
     */
    public String getLiveAddr() {
        return liveAddr;
    }

    /**
     * 设置居住地址
     *
     * @param liveAddr 居住地址
     */
    public void setLiveAddr(String liveAddr) {
        this.liveAddr = liveAddr;
    }

    /**
     * 获取居住详细地址
     *
     * @return live_detail_addr - 居住详细地址
     */
    public String getLiveDetailAddr() {
        return liveDetailAddr;
    }

    /**
     * 设置居住详细地址
     *
     * @param liveDetailAddr 居住详细地址
     */
    public void setLiveDetailAddr(String liveDetailAddr) {
        this.liveDetailAddr = liveDetailAddr;
    }

    /**
     * 获取居住地坐标，(经度,纬度)
     *
     * @return live_coordinate - 居住地坐标，(经度,纬度)
     */
    public String getLiveCoordinate() {
        return liveCoordinate;
    }

    /**
     * 设置居住地坐标，(经度,纬度)
     *
     * @param liveCoordinate 居住地坐标，(经度,纬度)
     */
    public void setLiveCoordinate(String liveCoordinate) {
        this.liveCoordinate = liveCoordinate;
    }

    /**
     * 获取运营商服务密码
     *
     * @return phone_server_pwd - 运营商服务密码
     */
    public String getPhoneServerPwd() {
        return phoneServerPwd;
    }

    /**
     * 设置运营商服务密码
     *
     * @param phoneServerPwd 运营商服务密码
     */
    public void setPhoneServerPwd(String phoneServerPwd) {
        this.phoneServerPwd = phoneServerPwd;
    }

    /**
     * 获取注册地址
     *
     * @return register_addr - 注册地址
     */
    public String getRegisterAddr() {
        return registerAddr;
    }

    /**
     * 设置注册地址
     *
     * @param registerAddr 注册地址
     */
    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    /**
     * 获取注册地坐标，(经度,纬度)
     *
     * @return register_coordinate - 注册地坐标，(经度,纬度)
     */
    public String getRegisterCoordinate() {
        return registerCoordinate;
    }

    /**
     * 设置注册地坐标，(经度,纬度)
     *
     * @param registerCoordinate 注册地坐标，(经度,纬度)
     */
    public void setRegisterCoordinate(String registerCoordinate) {
        this.registerCoordinate = registerCoordinate;
    }

    /**
     * 获取是否黑名单 ，10是20不是 30 骗贷
     *
     * @return state - 是否黑名单 ，10是20不是 30 骗贷
     */
    public String getState() {
        return state;
    }

    /**
     * 设置是否黑名单 ，10是20不是 30 骗贷
     *
     * @param state 是否黑名单 ，10是20不是 30 骗贷
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取拉黑原因
     *
     * @return black_reason - 拉黑原因
     */
    public String getBlackReason() {
        return blackReason;
    }

    /**
     * 设置拉黑原因
     *
     * @param blackReason 拉黑原因
     */
    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSignatureImg() {
        return signatureImg;
    }

    public void setSignatureImg(String signatureImg) {
        this.signatureImg = signatureImg;
    }
}