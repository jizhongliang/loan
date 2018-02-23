package com.hwc.framework.common;

/**
 * 参数校验帮助类
 * Created by jzl on 17/2/28.
 */
public class ParamUtil {

    /**  */
    private static final String EMPTY_STRING = "";

    /**  */
    private static final String NULL_STRING  = "null";

    /**
     * 是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || EMPTY_STRING.equals(obj) || NULL_STRING.equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 是否不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj != null && !EMPTY_STRING.equals(obj) && !NULL_STRING.equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        return false;
    }

}
