package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DSmsReports {

    /**
     * 接口调用结果 0- 提交成功 其他失败
     */
    private String result;

    /**
     * 描述
     */
    private String desc;

    /**
     * 反馈详情
     */
    private DReports reports;
}
