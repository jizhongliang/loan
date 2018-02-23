package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "best_sign_contract")
public class BestSignContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单id
     */
    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 文件id
     */
    private String fid;

    /**
     * 合同id
     */
    @Column(name = "contractId")
    private String contractid;

    /**
     * 合同签署失效时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 合同描述
     */
    private String descript;

    /**
     * 类型  信用借款  抵押借款
     */
    private String cat;

    /**
     * 合同状态
     */
    private String state;

    @Column(name = "contrace_url")
    private String contraceUrl;

    /**
     * 创建时间
     */
    private Date created;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取订单id
     *
     * @return borrow_id - 订单id
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * 设置订单id
     *
     * @param borrowId 订单id
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * 获取文件id
     *
     * @return fid - 文件id
     */
    public String getFid() {
        return fid;
    }

    /**
     * 设置文件id
     *
     * @param fid 文件id
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * 获取合同id
     *
     * @return contractId - 合同id
     */
    public String getContractid() {
        return contractid;
    }

    /**
     * 设置合同id
     *
     * @param contractid 合同id
     */
    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    /**
     * 获取合同签署失效时间
     *
     * @return expire_time - 合同签署失效时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置合同签署失效时间
     *
     * @param expireTime 合同签署失效时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取合同描述
     *
     * @return descript - 合同描述
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 设置合同描述
     *
     * @param descript 合同描述
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * 获取类型  信用借款  抵押借款
     *
     * @return cat - 类型  信用借款  抵押借款
     */
    public String getCat() {
        return cat;
    }

    /**
     * 设置类型  信用借款  抵押借款
     *
     * @param cat 类型  信用借款  抵押借款
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * 获取合同状态
     *
     * @return state - 合同状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置合同状态
     *
     * @param state 合同状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return contrace_url
     */
    public String getContraceUrl() {
        return contraceUrl;
    }

    /**
     * @param contraceUrl
     */
    public void setContraceUrl(String contraceUrl) {
        this.contraceUrl = contraceUrl;
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
}