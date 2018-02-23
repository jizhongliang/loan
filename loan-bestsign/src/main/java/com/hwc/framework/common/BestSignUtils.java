package com.hwc.framework.common;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 2017/12/7.
 */
public class BestSignUtils {
//    public static byte[] downloadSignatureImage(final String host, final String developerId, final String pem, final String account, final String imageName) throws Exception {
//        final String path = "/signatureImage/user/download";
//
//        String url = host + getUrlByRsa(developerId, pem, account, imageName, null, path); // rsa的话 pem为私钥
//        url = url + "&account=" + account + "&imageName=" + URLEncoder.encode(imageName, "UTF-8");
//        System.out.println("url:" + url);
//
//        Map<String, String> headers = new HashMap<>();
//        Map<String, Object> res = HttpSender.getResponseBytes("GET", url, "", headers);
//        byte[] data = (byte[]) res.get("responseData");
//        return data;
//    }

    /**
     * 仅限下载用户签名图片，其他接口请自行实现
     *
     * @param developerId
     * @param pem
     * @param sign
     * @param path
     * @return
     * @throws Exception
     */
    public static String getUrlByRsa(String developerId, String pem, String account, String imageName, Map<String, Object> data, String path) throws Exception {

        String randomStr = FsUtils.randomNumeric(4);
        String unix = Long.toString(System.currentTimeMillis());
        String rtick = unix + randomStr;

        String dataMd5 = "";
        if (data != null) {
            String jsonData = JSON.toJSONString(data);
            dataMd5 = EncodeUtils.md5(jsonData.getBytes("UTF-8"));
        }
        String sign = String.format("account=%sdeveloperId=%simageName=%srtick=%ssignType=rsa/openapi/v3%s/%s", account, developerId, imageName, rtick, path, dataMd5); // 生成签名字符串
        System.out.println(sign);

        String signDataString = getSignData(sign);
        String signData = Base64.encodeBase64String(EncodeUtils.rsaSign(signDataString.getBytes("UTF-8"), pem));

        signData = URLEncoder.encode(signData, "UTF-8");
        path = path + "/?developerId=" + developerId + "&rtick=" + rtick + "&sign=" + signData + "&signType=rsa";
        System.out.println(path);
        return path;


    }

    public static String getPostUrlByRsa(String developerId, String pem, Map<String, Object> data, String path) throws Exception {

        String randomStr = FsUtils.randomNumeric(4);
        String unix = Long.toString(System.currentTimeMillis());
        String rtick = unix + randomStr;

        String jsonData = JSON.toJSONString(data);
        String dataMd5 = EncodeUtils.md5(jsonData.getBytes("UTF-8"));
        String sign = String.format("developerId=%srtick=%ssignType=rsa/openapi/v3%s/%s", developerId, rtick, path, dataMd5); // 生成签名字符串
        System.out.println(sign);

        String signDataString = getSignData(sign);
        String signData = Base64.encodeBase64String(EncodeUtils.rsaSign(signDataString.getBytes("UTF-8"), pem));

        signData = URLEncoder.encode(signData, "UTF-8");
        path = path + "/?developerId=" + developerId + "&rtick=" + rtick + "&sign=" + signData + "&signType=rsa";
        System.out.println(path);
        return path;
    }

    private static JSONObject parseExecutorResult(String executorResult) {
        if (StringUtils.isBlank(executorResult)) {
            return null;
        }
        return JSON.parseObject(executorResult);
    }

    private static String getSignData(final String... args) {
        StringBuilder builder = new StringBuilder();
        int len = args.length;
        for (int i = 0; i < args.length; i++) {
            builder.append(convertToUtf8(args[i]));
            if (i < len - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    /**
     * 转换字符集到utf8
     */
    public static String convertToUtf8(String src) {
        if (src == null || src.length() == 0) {
            return src;
        }
        byte[] srcData = src.getBytes();

        try {
            return new String(srcData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从utf8转换字符集
     */
    public static String convertFromUtf8(String src) {
        if (src == null || src.length() == 0) {
            return src;
        }
        byte[] srcData = new byte[0];
        try {
            srcData = src.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(srcData);
    }

    public static String urlEncode(String data) throws UnsupportedEncodingException {
        String newData = convertToUtf8(data);
        return URLEncoder.encode(newData, "UTF-8");
    }

    public static String join(String[] items, String split) {
        if (items.length == 0) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        int i;
        for (i = 0; i < items.length - 1; i++) {
            s.append(items[i]);
            s.append(split);
        }
        s.append(items[i]);
        return s.toString();
    }

}
