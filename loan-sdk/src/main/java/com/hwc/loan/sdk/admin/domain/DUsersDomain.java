package com.hwc.loan.sdk.admin.domain;

import java.util.Date;

/**
 * Created by  on 2017/10/18.
 */
public class DUsersDomain {
    /**
     * 用户数字ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 名字
     */
    private String name;

    /**
     * 会员的来源  安卓注册，IOS 注册 微信等
     */
    private String src;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 生日
     */
    private Date birthday;

    private String sex;

    private String client;

    private String imei;

    /**
     * 状态。可选值:N(正常),I(未激活),D(删除),R(冻结),S(监管)
     */
    private String status;

    /**
     * 用户头像地址
     */
    private String avatar;

    private String country;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 区/县
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 邀请代码
     */
    private String inviteCode;

    private Long recommend;

    private String latitude;

    private String longitude;

    private String cby;

    /**
     * 修改时间
     */
    private Date changed;

    /**
     * 用户注册时间。格式:yyyy-MM-dd HH:mm:ss
     */
    private Date created;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getRecommend() {
        return recommend;
    }

    public void setRecommend(Long recommend) {
        this.recommend = recommend;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCby() {
        return cby;
    }

    public void setCby(String cby) {
        this.cby = cby;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
