package com.hwc.framework.modules.model;

import javax.persistence.*;

@Table(name = "cl_user_emer_contacts")
public class ClUserEmerContacts {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系号码
     */
    private String phone;

    /**
     * 客户表 外键
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 与本人关系
     */
    private String relation;

    /**
     * 是否直系
     */
    private String type;

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
     * 获取联系人
     *
     * @return name - 联系人
     */
    public String getName() {
        return name;
    }

    /**
     * 设置联系人
     *
     * @param name 联系人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系号码
     *
     * @return phone - 联系号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系号码
     *
     * @param phone 联系号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取与本人关系
     *
     * @return relation - 与本人关系
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 设置与本人关系
     *
     * @param relation 与本人关系
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * 获取是否直系
     *
     * @return type - 是否直系
     */
    public String getType() {
        return type;
    }

    /**
     * 设置是否直系
     *
     * @param type 是否直系
     */
    public void setType(String type) {
        this.type = type;
    }
}