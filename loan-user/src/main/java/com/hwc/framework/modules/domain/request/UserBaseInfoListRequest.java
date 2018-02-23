package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

import javax.xml.crypto.Data;
import java.util.Date;

public class UserBaseInfoListRequest extends ItemsRequest {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 证件号码
     */
    private String cat;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Data endTime;

    /**
     * 渠道码
     */
    private Long channelId;

}
