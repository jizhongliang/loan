/**
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.hwc.loan.sdk.quzrtz.domain;

import java.util.Date;

/**
 * 
 * @author jinlilong
 * @version $Id: QuartzLog.java, v 0.1 2018年1月2日 上午10:04:47 jinlilong Exp $
 */
public class QuartzLog {

    /**  */
    private Long    id;

    /**  */
    private Long    quartzId;

    /**  */
    private String  name;

    /**  */
    private Date    startTime;

    /**  */
    private Integer time;

    /**  */
    private String  result;

    /**  */
    private String  remark;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>quartzId</tt>.
     * 
     * @return property value of quartzId
     */
    public Long getQuartzId() {
        return quartzId;
    }

    /**
     * Setter method for property <tt>quartzId</tt>.
     * 
     * @param quartzId value to be assigned to property quartzId
     */
    public void setQuartzId(Long quartzId) {
        this.quartzId = quartzId;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     * 
     * @return property value of startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     * 
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>time</tt>.
     * 
     * @return property value of time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Setter method for property <tt>time</tt>.
     * 
     * @param time value to be assigned to property time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Getter method for property <tt>result</tt>.
     * 
     * @return property value of result
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     * 
     * @param result value to be assigned to property result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     * 
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     * 
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

}
