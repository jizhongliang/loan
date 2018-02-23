package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.quzrtz.response.QuartzUpdateResponse;

public class QuartzUpdateRequest extends RequestBase<QuartzUpdateResponse> {

    public static final String METHOD = "/api/p2p/manager/quartz/update";

    public QuartzUpdateRequest() {
        super(METHOD);
    }

    /**  */
    private Long   id;

    /**
     * 任务编号
     */
    private String code;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 定时表达式
     */
    private String cycle;

    /**
     * 任务类名
     */
    private String className;

    /**  状态*/
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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
     * Getter method for property <tt>state</tt>.
     * 
     * @return property value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for property <tt>state</tt>.
     * 
     * @param state value to be assigned to property state
     */
    public void setState(String state) {
        this.state = state;
    }

}
