package com.hwc.framework.modules.domain;

import java.util.Date;

public class MjParkingInfoUser {
    private Long id;

    private Long userid;

    private Long mjparkinginfoid;

    private String type;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getMjparkinginfoid() {
        return mjparkinginfoid;
    }

    public void setMjparkinginfoid(Long mjparkinginfoid) {
        this.mjparkinginfoid = mjparkinginfoid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}