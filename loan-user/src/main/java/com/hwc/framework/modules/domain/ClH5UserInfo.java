package com.hwc.framework.modules.domain;

import java.io.Serializable;
import java.util.Date;




public class ClH5UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//
	private String username;
	//
	private String userphone;
	//期望额度
	private String credit;
	//资金用途
	private String moneyuse;
	//期望借款时间
	private Date borrowtime;
	//url
	private String url;
	//ip
	private String ip;
	//城市
	private String city;
	//设备
	private String equipment;
	//邀请人（保留字段）
	private String inviter;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	/**
	 * 获取：
	 */
	public String getUserphone() {
		return userphone;
	}
	/**
	 * 设置：期望额度
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}
	/**
	 * 获取：期望额度
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * 设置：资金用途
	 */
	public void setMoneyuse(String moneyuse) {
		this.moneyuse = moneyuse;
	}
	/**
	 * 获取：资金用途
	 */
	public String getMoneyuse() {
		return moneyuse;
	}
	/**
	 * 设置：期望借款时间
	 */
	public void setBorrowtime(Date borrowtime) {
		this.borrowtime = borrowtime;
	}
	/**
	 * 获取：期望借款时间
	 */
	public Date getBorrowtime() {
		return borrowtime;
	}
	/**
	 * 设置：url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：设备
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	/**
	 * 获取：设备
	 */
	public String getEquipment() {
		return equipment;
	}
	/**
	 * 设置：邀请人（保留字段）
	 */
	public void setInviter(String inviter) {
		this.inviter = inviter;
	}
	/**
	 * 获取：邀请人（保留字段）
	 */
	public String getInviter() {
		return inviter;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
