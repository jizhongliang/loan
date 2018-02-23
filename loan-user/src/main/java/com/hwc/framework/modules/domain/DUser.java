package com.hwc.framework.modules.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by  on 2017/9/29.
 */
@Data
public class DUser {

    @ApiModelProperty("短信验证码")
    private String vcode;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("openid")
    private String openid;

    @ApiModelProperty("uuid")
    private String uuid;

    @ApiModelProperty("哪个类型的用户")
    private String cat;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("登录密码")
    private String loginPwd;

    @ApiModelProperty("上次登录密码修改时间")
    private Date loginpwdModifyTime;

    @ApiModelProperty("注册时间")
    private Date registTime;

    @ApiModelProperty("注册客户端")
    private String registerClient;

    @ApiModelProperty("交易密码")
    private String tradePwd;

    @ApiModelProperty("上次交易密码修改时间")
    private Date tradepwdModifyTime;

    @ApiModelProperty("邀请码")
    private String invitationCode;

    @ApiModelProperty("渠道")
    private Long channelId;

    @ApiModelProperty("市场码")
    private String mktId;

    @ApiModelProperty("登录时间")
    private Date loginTime;

    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("微信ID")
    private String unionid;

    @ApiModelProperty("来源")
    private String src;
    @ApiModelProperty("登录状态")
    private String isLogin;
}
