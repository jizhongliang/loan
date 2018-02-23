package com.hwc.framework.modules.domain;

import lombok.Data;

import java.util.Date;

@Data
 public class DUserMessage{

	/**
     * 用户标识
     */
	private Long userId;

    /**
    * 短信收发人
    */
    private String name;

    /**
    * 手机号码
    */
    private String phone;
    /**
     * 短信内容
     */
     private String ctx;
    /**
    * 收发时间
    */
    private Date time;

    /**
    * 收发标识，10发20收
    */
    private String type;


}
