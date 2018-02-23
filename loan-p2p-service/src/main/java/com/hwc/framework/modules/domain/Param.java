package com.hwc.framework.modules.domain;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Param {

    private Map params;

    private String source;

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

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("thirdTransaction","CW88888888");
        Param obj = new Param();
        obj.setParams(map);
        System.out.println(JSON.toJSONString(obj));
    }
}
