package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by   on 2017/11/8.
 */
@Data
public class BindCardDomain extends DomianBase {
    @ApiModelProperty(value = "银行卡号", required = true)
    private String card_no;
    @ApiModelProperty(value = "银行名", required = true)
    private String bank_name;
    @ApiModelProperty(value = "银行代码 ，连连返回的 code", required = true)
    private String bank_code;
    @ApiModelProperty(value = "持卡人名字", required = true)
    private String id_holder;
    @ApiModelProperty(value = "身份证号码", required = true)
    private String id_no;
    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;
    @ApiModelProperty(value = "短信验证码 ,第二次提交，必填", required = false)
    private String sms_code;
    @ApiModelProperty(value = "信用/车位 10：信用，20：车位", required = true)
    private String cat;

    public String logString() {
        return "BindCardDomain=========={" + "card_no='" + card_no + ", bank_name='" + bank_name
               + ", bank_code='" + bank_code + ", id_holder='" + id_holder + ", id_no='" + id_no
               + ", mobile='" + mobile + ", sms_code='" + sms_code + '}';
    }
}
