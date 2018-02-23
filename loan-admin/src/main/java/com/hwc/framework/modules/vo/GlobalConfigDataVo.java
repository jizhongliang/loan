package com.hwc.framework.modules.vo;

/**
 * Created by jzl on 2018/1/10.
 */
public class GlobalConfigDataVo {
    private String payChannel;      //放还款渠道，10:宝付 20:新华金典

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
}
