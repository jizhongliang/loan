package com.hwc.loan.sdk.quzrtz.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.quzrtz.response.QuartzAddOneResponse;

public class QuartzAddOneRequest extends RequestBase<QuartzAddOneResponse> {

    public static final String METHOD = "/api/p2p/manager/quartz/addition";

    public QuartzAddOneRequest() {
        super(METHOD);
    }

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
}
