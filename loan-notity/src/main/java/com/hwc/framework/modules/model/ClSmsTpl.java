package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_sms_tpl")
public class ClSmsTpl {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * tpl模板
     */
    private String tpl;

    /**
     * 模板编号
     */
    private String number;

    /**
     * 短信模板状态 10 -启用 20 - 禁用'
     */
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}