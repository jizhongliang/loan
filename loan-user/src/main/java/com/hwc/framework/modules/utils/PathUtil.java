package com.hwc.framework.modules.utils;


import cn.freesoft.utils.FsUtils;

/**
 * Created by  on 2017/6/20.
 */
public class PathUtil {

    public static String getFullResPath(String resPath) {
        String newpath = resPath != null ? resPath.trim() : "";
        if(!newpath.startsWith("/") && !newpath.startsWith("\\"))
            newpath = "/" + newpath;
        return getBaseResPath() + newpath;
    }

    public static String getBaseResPath() {
//        String serverHost = "server_host";
//        String oss_domain = "oss_domain";
//
//        if(FsUtils.strsEmpty(oss_domain)) {
//            return serverHost + "/readFile.htm?path=";
//        } else {
//            return oss_domain;
//        }
        return "http://caiwei.oss-cn-hangzhou.aliyuncs.com";
    }

}
