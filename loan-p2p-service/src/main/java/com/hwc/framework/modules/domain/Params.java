package com.hwc.framework.modules.domain;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Params {

    private String source;

    private List params;

    private String sign;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public static void main(String[] args) {
        BorrowerUserinfo obj = new BorrowerUserinfo();
        List list = new ArrayList();
        list.add(obj);
        Params vo = new Params();
        System.out.println(JSON.toJSONString(vo));
    }
}
