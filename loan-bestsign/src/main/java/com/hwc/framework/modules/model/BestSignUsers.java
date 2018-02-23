package com.hwc.framework.modules.model;

import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "best_sign_users")
public class BestSignUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 证书类型
     */
    @Column(name = "certType")
    private String certtype;

    /**
     * 签名图片
     */
    @Column(name = "sign_image")
    private String signImage;

    private String idno;
    private String name;

    /**
     * 状态
     */
    private String state;

    /**
     * 创建时间
     */
    private Date created;
    @Column(name = "certId")
    private String certId;

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取证书类型
     *
     * @return certType - 证书类型
     */
    public String getCerttype() {
        return certtype;
    }

    /**
     * 设置证书类型
     *
     * @param certtype 证书类型
     */
    public void setCerttype(String certtype) {
        this.certtype = certtype;
    }

    /**
     * 获取签名图片
     *
     * @return sign_image - 签名图片
     */
    public String getSignImage() {
        return signImage;
    }

    /**
     * 设置签名图片
     *
     * @param signImage 签名图片
     */
    public void setSignImage(String signImage) {
        this.signImage = signImage;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
}