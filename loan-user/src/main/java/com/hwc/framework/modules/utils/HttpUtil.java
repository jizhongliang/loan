package com.hwc.framework.modules.utils;

import cn.freesoft.utils.FsUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Created by lsk on 2015/12/16.
 */
public class HttpUtil {

    private static final Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 默认的编码格式
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 默认的超时时间 60 S
     */
    private static final int TIMEOUT = 60000;

    private static Scanner scanner;

    public static String doGet(String url) {
        return send(url, null, false, "utf-8");
    }

    /**
     * 根据请求参数生成List<BasicNameValuePair>
     *
     * @param params
     * @return
     */
    private static List<BasicNameValuePair> wrapParam(Map<String, String> params) {
        List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            data.add(new BasicNameValuePair(key, value));
        }
        return data;
    }


    public static String doGet1(String url) {
        try {


            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet get = new HttpGet(url);

            HttpResponse response = httpClient.execute(get);
        /* 发送请求并等待响应 */
        /* 若状态码为200 ok */
            if (response.getStatusLine().getStatusCode() == 200) {
            /* 读返回数据 */
                String strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
                return strResult;
            } else {
                return "";
            }
        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }
        return "";
        // return returnStr;
    }

    public static String send(String url, Map<String, String> params, boolean post, String readEncode) {
        InputStream input = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36 SE 2.X MetaSr 1.0");
            /*connection.setRequestProperty("Cookie","JSESSIONID=32754410A9908881317F32FE3FA84CB3; j_username=; j_password=");
            connection.setRequestProperty("Cache-Control","max-age=0");*/

            if (post) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                ((HttpURLConnection) connection).setRequestMethod("POST");

                if (params != null && !params.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Entry<String, String> entry : params.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        sb.append(key + "=" + URLEncoder.encode(value, "UTF-8") + "&");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    OutputStream out = connection.getOutputStream();
                    out.write(sb.toString().getBytes("UTF-8"));
                }

            }
            input = connection.getInputStream();
            scanner = new Scanner(input, readEncode);
            scanner.useDelimiter("$");
            return scanner.next();
        }catch (java.io.FileNotFoundException e){
            logger.info("该用户未上传通讯录");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

}
