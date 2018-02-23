package com.hwc.framework.modules.domain;

import com.alibaba.fastjson.JSON;
import com.hwc.framework.utils.Des;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private String data;
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static void main(String[] args) throws Exception {
        String str = "{\n" +
                "    \"code\": 0,\n" +
                "    \"data\": \"wRk0YauMI+5k1NNBLy/Znw==\",\n" +
                "    \"message\": \"成功\"\n" +
                "}";
        Response res = JSON.parseObject(str,Response.class);
        String data = Des.decode(Des.secretKey,res.getData());
        ResDatas datas = JSON.parseObject(data,ResDatas.class);
        System.out.println(datas.getResult().isEmpty());
    }
}
