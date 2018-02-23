package com.hwc.framework.utils;

/**
 * @author jzl
 */
public class Code {
    
    public enum codeData{

        REQUESTSUCCESS("1"),
        REQUESTFAILED("0"),
        SYSTEMERROR("9998"),
        USER_DEFINED("9999"),
        DECRYPTERROR("1001"),
        NOTHISUSER("1002"),
        NONEPARAM("1003"),
        PARAMERROR("1004"),
        NOMERCHANT("9000");
        public String code;
        private codeData(String code){
            this.code = code;
        }

    }
    public enum msg{
        REQUESTSUCCESS("成功"),
        REQUESTFAILED("失败"),
        SYSTEMERROR("系统异常，请联系管理员"),
        USER_DEFINED("%s"),
        DECRYPTERROR("解密异常"),
        NOTHISUSER("无此用户"),
        NONEPARAM("参数为空"),
        PARAMERROR("参数错误"),
        NOMERCHAT("无此商户");

        public String desc;
        private msg(String desc){
            this.desc = desc;
        }
    }
}
