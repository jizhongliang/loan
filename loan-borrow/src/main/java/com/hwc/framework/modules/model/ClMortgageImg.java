package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_mortgage_img")
public class ClMortgageImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 抵押物贷款
     */
    private Long mid;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 10 身份类，20 资产类 30 其他证明材料
     */
    @Column(name = "img_cat")
    private String imgCat;

    @Column(name = "img_url")
    private String imgUrl;

    private Integer seq;

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
     * 获取抵押物贷款
     *
     * @return mid - 抵押物贷款
     */
    public Long getMid() {
        return mid;
    }

    /**
     * 设置抵押物贷款
     *
     * @param mid 抵押物贷款
     */
    public void setMid(Long mid) {
        this.mid = mid;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取10 身份类，20 资产类 30 其他证明材料
     *
     * @return img_cat - 10 身份类，20 资产类 30 其他证明材料
     */
    public String getImgCat() {
        return imgCat;
    }

    /**
     * 设置10 身份类，20 资产类 30 其他证明材料
     *
     * @param imgCat 10 身份类，20 资产类 30 其他证明材料
     */
    public void setImgCat(String imgCat) {
        this.imgCat = imgCat;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}