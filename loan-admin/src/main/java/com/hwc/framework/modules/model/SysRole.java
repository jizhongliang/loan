package com.hwc.framework.modules.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SysRole {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 序号
     */
    private Integer num;

    /**
     * 父角色id
     */
    private Integer pid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 部门名称
     */
    private Integer deptid;

    /**
     * 提示
     */
    private String tips;

    /**
     * 保留字段(暂时没用）
     */
    private Integer version;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取序号
     *
     * @return num - 序号
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置序号
     *
     * @param num 序号
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取父角色id
     *
     * @return pid - 父角色id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父角色id
     *
     * @param pid 父角色id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门名称
     *
     * @return deptid - 部门名称
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 设置部门名称
     *
     * @param deptid 部门名称
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 获取提示
     *
     * @return tips - 提示
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置提示
     *
     * @param tips 提示
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * 获取保留字段(暂时没用）
     *
     * @return version - 保留字段(暂时没用）
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置保留字段(暂时没用）
     *
     * @param version 保留字段(暂时没用）
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}