package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_user")
public class ClUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    /**
     * 哪个类型的用户  10 信用类，20 抵押类
     */
    private String cat;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 上次登录密码修改时间
     */
    @Column(name = "loginpwd_modify_time")
    private Date loginpwdModifyTime;

    /**
     * 注册时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * 注册客户端
     */
    @Column(name = "register_client")
    private String registerClient;

    /**
     * 交易密码
     */
    @Column(name = "trade_pwd")
    private String tradePwd;

    /**
     * 上次交易密码修改时间
     */
    @Column(name = "tradepwd_modify_time")
    private Date tradepwdModifyTime;

    /**
     * 邀请码
     */
    @Column(name = "invitation_code")
    private String invitationCode;

    /**
     * 渠道
     */
    @Column(name = "channel_id")
    private Long channelId;

    /**
     * 市场码
     */
    @Column(name = "mkt_id")
    private String mktId;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;
    /**
     * 登录状态
     */
    @Column(name = "is_login")
    private String isLogin;

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

    public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	/**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取哪个类型的用户  10 信用类，20 抵押类
     *
     * @return cat - 哪个类型的用户  10 信用类，20 抵押类
     */
    public String getCat() {
        return cat;
    }

    /**
     * 设置哪个类型的用户  10 信用类，20 抵押类
     *
     * @param cat 哪个类型的用户  10 信用类，20 抵押类
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取登录密码
     *
     * @return login_pwd - 登录密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置登录密码
     *
     * @param loginPwd 登录密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 获取上次登录密码修改时间
     *
     * @return loginpwd_modify_time - 上次登录密码修改时间
     */
    public Date getLoginpwdModifyTime() {
        return loginpwdModifyTime;
    }

    /**
     * 设置上次登录密码修改时间
     *
     * @param loginpwdModifyTime 上次登录密码修改时间
     */
    public void setLoginpwdModifyTime(Date loginpwdModifyTime) {
        this.loginpwdModifyTime = loginpwdModifyTime;
    }

    /**
     * 获取注册时间
     *
     * @return regist_time - 注册时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册时间
     *
     * @param registTime 注册时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * 获取注册客户端
     *
     * @return register_client - 注册客户端
     */
    public String getRegisterClient() {
        return registerClient;
    }

    /**
     * 设置注册客户端
     *
     * @param registerClient 注册客户端
     */
    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient;
    }

    /**
     * 获取交易密码
     *
     * @return trade_pwd - 交易密码
     */
    public String getTradePwd() {
        return tradePwd;
    }

    /**
     * 设置交易密码
     *
     * @param tradePwd 交易密码
     */
    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    /**
     * 获取上次交易密码修改时间
     *
     * @return tradepwd_modify_time - 上次交易密码修改时间
     */
    public Date getTradepwdModifyTime() {
        return tradepwdModifyTime;
    }

    /**
     * 设置上次交易密码修改时间
     *
     * @param tradepwdModifyTime 上次交易密码修改时间
     */
    public void setTradepwdModifyTime(Date tradepwdModifyTime) {
        this.tradepwdModifyTime = tradepwdModifyTime;
    }

    /**
     * 获取邀请码
     *
     * @return invitation_code - 邀请码
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * 设置邀请码
     *
     * @param invitationCode 邀请码
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    /**
     * 获取渠道
     *
     * @return channel_id - 渠道
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * 设置渠道
     *
     * @param channelId 渠道
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取市场码
     *
     * @return mkt_id - 市场码
     */
    public String getMktId() {
        return mktId;
    }

    /**
     * 设置市场码
     *
     * @param mktId 市场码
     */
    public void setMktId(String mktId) {
        this.mktId = mktId;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}