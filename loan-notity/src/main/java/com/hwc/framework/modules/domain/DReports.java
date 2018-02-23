package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DReports {

    /**
     * 短信编号
     */
    private String msgid;

    /**
     * 下行手机号码
     */
    private String phone;

    /**
     * 短信发送结果 0——成功；1——接口处理失败；2——运营商网关失败
     */
    private String status;

    /**
     * 短信发送结果 当status为1时，以desc的错误码为准。
     */
    private String desc;

    /**
     * 当status为2时，表示运营商网关返回的原始值；
     */
    private String wgcode;

    /**
     * 状态报告接收时间
     */
    private Date time;

    /**
     * 长短信条数
     */
    private String smsCount;

    /**
     * 长短信第几条标示
     */
    private String smsIndex;

}
