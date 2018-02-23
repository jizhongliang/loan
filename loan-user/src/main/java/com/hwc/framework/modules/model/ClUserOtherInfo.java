package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_user_other_info")
public class ClUserOtherInfo {
    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    private String accnt;

    /**
     * 账号1
     */
    private String unionid;

    /**
     * 用户标识(关联客户主键)
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * WX 微信,QQ qq TAOBAO 淘宝
     */
    private String cat;

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
     * 获取主键Id
     *
     * @return id - 主键Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键Id
     *
     * @param id 主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return accnt - 账号
     */
    public String getAccnt() {
        return accnt;
    }

    /**
     * 设置账号
     *
     * @param accnt 账号
     */
    public void setAccnt(String accnt) {
        this.accnt = accnt;
    }

    /**
     * 获取账号1
     *
     * @return unionid - 账号1
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置账号1
     *
     * @param unionid 账号1
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取用户标识(关联客户主键)
     *
     * @return user_id - 用户标识(关联客户主键)
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识(关联客户主键)
     *
     * @param userId 用户标识(关联客户主键)
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取WX 微信,QQ qq TAOBAO 淘宝
     *
     * @return cat - WX 微信,QQ qq TAOBAO 淘宝
     */
    public String getCat() {
        return cat;
    }

    /**
     * 设置WX 微信,QQ qq TAOBAO 淘宝
     *
     * @param cat WX 微信,QQ qq TAOBAO 淘宝
     */
    public void setCat(String cat) {
        this.cat = cat;
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
}