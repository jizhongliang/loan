package com.hwc.framework.modules.utils;

import cn.freesoft.FsParameters;
import cn.freesoft.utils.FsUtils;
import com.hwc.aliyun.OSSHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by   on 2017/7/18.
 */

@Component
public class OSSUtils {
    @Value("${oss.accesskeyid}")
    private String accesskeyid ;

    @Value("${oss.accesskeysecret}")
    private String accesskeysecret ;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucketname}")
    private String bucketname;

    public  String upFile(String message, String path, String userId)   {
        try {
            byte[] bytes = message.getBytes("utf-8");
            // 上传到OSS
            FsParameters params = new FsParameters();
            params.put(OSSHelper.KEY_ACCESSKEYID, accesskeyid);
            params.put(OSSHelper.KEY_ACCESSKEYSECRET, accesskeysecret);
            params.put(OSSHelper.KEY_BUCKETNAME, bucketname);
            params.put(OSSHelper.KEY_ENDPOINT, endpoint);
            params.put(OSSHelper.KEY_FOLDER,  path);
            params.put(OSSHelper.PIC_PATH,userId + ".json");
            params.put("bytes", bytes);

            String newpath = OSSHelper.upload_simple2(params);
            return newpath;
        }catch (Exception ex){
            FsUtils.log_error(ex);
        }
        return "";
    }

    public String upJason(String message, String path, String userId)   {
        try {
            String fileName = userId+".json";
            byte[] bytes = message.getBytes("utf-8");
            // 上传到OSS
            FsParameters params = new FsParameters();
            params.put(OSSHelper.KEY_ACCESSKEYID, accesskeyid);
            params.put(OSSHelper.KEY_ACCESSKEYSECRET, accesskeysecret);
            params.put(OSSHelper.KEY_BUCKETNAME, bucketname);
            params.put(OSSHelper.KEY_ENDPOINT, endpoint);
            params.put(OSSHelper.KEY_FOLDER, path);
            params.put(OSSHelper.PIC_PATH,fileName);
            params.put("bytes", bytes);
            OSSHelper.upload_simple2(params);

            String saveDBPath = "/"+path+fileName;
            return saveDBPath;
        }catch (Exception ex){
            FsUtils.log_error(ex);
        }
        return "";
    }

    public String upHtml(String message, String path, String userId)   {
        try {
            String fileName = userId+".html";
            byte[] bytes = message.getBytes("utf-8");
            // 上传到OSS
            FsParameters params = new FsParameters();
            params.put(OSSHelper.KEY_ACCESSKEYID, accesskeyid);
            params.put(OSSHelper.KEY_ACCESSKEYSECRET, accesskeysecret);
            params.put(OSSHelper.KEY_BUCKETNAME, bucketname);
            params.put(OSSHelper.KEY_ENDPOINT, endpoint);
            params.put(OSSHelper.KEY_FOLDER, path);
            params.put(OSSHelper.PIC_PATH,fileName);
            params.put("bytes", bytes);
            OSSHelper.upload_simple2(params);
            String saveDBPath = "/"+path+fileName;
            return saveDBPath;
        }catch (Exception ex){
            FsUtils.log_error(ex);
        }
        return "";
    }
}
