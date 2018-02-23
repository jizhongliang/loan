package com.hwc.framework.modules.third.impl;

import cn.freesoft.utils.Base64;
import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.hwc.framework.common.*;
import com.hwc.framework.modules.third.BestSignService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 2017/12/7.
 */
@Component
public class BestSignServiceImpl implements BestSignService {

    private static final Logger logger = LoggerFactory.getLogger(BestSignServiceImpl.class);
    @Value("${best.sign.developerId}")
    private String developerId;
    @Value("${best.sign.host}")
    private String host;
    @Value("${best.sign.pem}")
    private String pem;
    @Value("${oss.prefix}")
    private String ossPrefix;

    // private String prefix = "u_";

    @Override
    public JSONObject register(JSONObject jsonObject) {
        try {
            JSONObject userInfo = getUserInfo(jsonObject);
            if (userInfo.getInteger("errno").equals(0)) {
                return userInfo;
            }

            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("mobile", jsonObject.getString("phone"));
                    put("name", jsonObject.getString("realName"));
                    put("userType", 1);
                    put("mail", "");
                }
            };
            final String path = "/user/reg";

            return post(data, path);

        } catch (Exception e) {
            logger.error("注册bestsign失败", e);
        }
        return null;

    }

    @Override
    public JSONObject registerEnterprise(JSONObject jsonObject) {
        try {
            JSONObject userInfo = getUserInfo(jsonObject);
            if (userInfo.getInteger("errno").equals(0)) {
                return userInfo;
            }
             
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("mobile", jsonObject.getString("phone"));
                    put("name", jsonObject.getString("realName"));
                    put("userType", 2);
                    put("mail", "");
                }
            };
            final String path = "/user/reg";

            return post(data, path);

        } catch (Exception e) {
            logger.error("注册bestsign失败", e);
        }
        return null;

    }

    /**
     * 获取上上签用户信息
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getUserInfo(JSONObject jsonObject) throws Exception {
        String path = "/user/getPersonalCredential";
        String account = jsonObject.getString("userId");
        Map<String, Object> data = new HashMap<String, Object>() {
            {
                put("account", account);
            }
        };
        return post(data, path);

    }

    @Override
    public JSONObject setEnterpriseInfo(JSONObject jsonObject) {
        try {
            String path = "/user/setEnterpriseCredential/";
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("regCode", "91330106MA2AXMQ3XN");
                    put("taxCode", "91330106MA2AXMQ3XN");
                    put("name", jsonObject.getString("realName"));
                    put("legalPerson", "胡斌");
                    put("legalPersonIdentity", "330203198011181816");
                    put("legalPersonIdentityType", "0");
                    put("legalPersonMobile", "13958069989");
                    put("contactMobile", "13958069989");
                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("上传证件失败", ex);
        }
        return new JSONObject();
    }

    @Override
    public JSONObject setUserIdno(JSONObject jsonObject) {
        try {
            String path = "/user/setPersonalCredential/";
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("identity", jsonObject.getString("idNo"));
                    put("identityType", "0");
                    put("name", jsonObject.getString("realName"));

                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("上传证件失败", ex);
        }
        return new JSONObject();
    }

    /**
     * 申请数字证书
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject applycert(JSONObject jsonObject) throws Exception {
        String path = "/user/applyCert/";
        Map<String, Object> data = new HashMap<String, Object>() {
            {
                put("account", jsonObject.getString("userId"));
            }
        };
        return post(data, path);

    }

    @Override
    public JSONObject createSignatureImage(JSONObject jsonObject) {
        try {
            String path = "/signatureImage/user/create";
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("createSignatureImage", ex);
        }
        return null;

    }

    @Override
    public JSONObject upSignatureImage(JSONObject jsonObject) throws Exception {
        String path = "/signatureImage/user/upload/";
        String url = ossPrefix + jsonObject.getString("signatureImg");
        HttpURLConnection connection = HttpUtils.createHttpURLConnection("GET", url);
        byte[] imageByte = HttpUtils.getResponseBytes(connection);
        String imageData = Base64.encode(imageByte);
        Map<String, Object> data = new HashMap<String, Object>() {
            {
                put("account", jsonObject.getString("userId"));
                put("imageData", imageData);
            }
        };
        return post(data, path);

    }

    /**
     * 上传合同
     *
     * @return
     */
    @Override
    public JSONObject upContract(JSONObject jsonObject) {
        try {
            String path = "/storage/upload/";
            FileInputStream file = new FileInputStream(jsonObject.getString("file"));
            byte[] bdata = IOUtils.toByteArray(file);
            String fdata = Base64.encode(bdata);
            String fmd5 = DigestUtils.md5Hex(bdata);
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("fmd5", fmd5);
                    put("ftype", "PDF");
                    put("fname", "register.PDF");
                    put("fpages", 1);
                    put("fdata", fdata);

                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("上传合同失败", ex);
        }
        return null;
    }

    @Override
    public JSONObject getSignURL(JSONObject jsonObject) {
        String path = "/contract/getSignURL/";
        try {
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                    put("expireAt", 0);
                    put("quality", "90");
                    put("account", jsonObject.getString("userId"));
                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("getsignUrl", ex);
        }
        return null;

    }

    @Override
    public JSONObject addPDFElements(JSONObject jsonObject) {
        String path = "/storage/addPDFElements/";
        try {


            JSONArray array = jsonObject.getJSONArray("elements");


            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("fid", jsonObject.getString("fid"));
                    put("elements", array);
                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("add el error", ex);
        }
        return null;
    }

    public JSONObject getPdfDownLoad(JSONObject jsonObject) {
        String path = "/file/getDownloadURL/";
        try {


            Map<String, Object> data = new HashMap<String, Object>() {
                {

                    put("fid", jsonObject.getString("fid"));


                }
            };
            JSONObject post = post(data, path);
			return post;

        } catch (Exception ex) {
            logger.error("create ", ex);
        }
        return null;
    }

    @Override
    public JSONObject createContract(JSONObject jsonObject) {
        String path = "/contract/create/";
        try {

            Long expire = FsUtils.addDate(new Date(), 7).getTime() / 1000L;
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("account", jsonObject.getString("userId"));
                    put("fid", jsonObject.getString("fid"));
                    put("expireTime", expire);
                    put("title", jsonObject.getString("title"));
                    put("description", jsonObject.getString("description"));


                }
            };
            return post(data, path);

        } catch (Exception ex) {
            logger.error("create ", ex);
        }
        return null;
    }

    @Override
    public JSONObject addSigners(JSONObject jsonObject) {
        String path = "/contract/addSigners/";
        try {
//            JSONArray array = new JSONArray();
//
//            array.add("CAIWEI01");
//            array.add(jsonObject.getString("userId"));


            Long expire = FsUtils.addDate(new Date(), 7).getTime() / 1000L;
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                    put("signers", jsonObject.getJSONArray("signers"));
                }
            };
            return post(data, path);

        } catch (Exception ex) {
            logger.error("create ", ex);
        }
        return null;
    }

    @Override
    public JSONObject addSigner(JSONObject jsonObject) {
        String path = "/contract/addSigner/";
        try {
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                    put("signer", jsonObject.getString("signer"));
                }
            };
            return post(data, path);

        } catch (Exception ex) {
            logger.error("create ", ex);
        }
        return null;
    }

    @Override
    public JSONObject setSignerConfig(JSONObject jsonObject) {
        String path = "/contract/setSignerConfig/";
        try {
            JSONArray array = jsonObject.getJSONArray("position");// new JSONArray();

            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                    put("account", jsonObject.getString("userId"));
                    put("signaturePositions", array);

                    if (FsUtils.strsNotEmpty(jsonObject.getString("phone"))) {
                        put("isVerifySigner", "1");
                        put("vcodeMobile", jsonObject.getString("phone"));
                    }
                    if (FsUtils.strsNotEmpty(jsonObject.getString("returnUrl"))) {
                        put("returnUrl", jsonObject.getString("returnUrl"));
                    }
                    put("isDrawSignatureImage", "1");

                    put("certType", jsonObject.getString("certType"));

                }
            };
            return post(data, path);

        } catch (Exception ex) {
            logger.error("create ", ex);
        }
        return null;


    }

    @Override
    public JSONObject signCert(JSONObject jsonObject) {
        String path = "/contract/sign/cert/";
        try {


            // new JSONArray();
            JSONArray array = jsonObject.getJSONArray("position");

            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                    put("signer", jsonObject.getString("userId"));
                    put("signaturePositions", array);

                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("sign cert", ex);
        }
        return null;
    }

    @Override
    public JSONObject getDownload(JSONObject jsonObject) {
        try {
            String path = "/contract/getDownloadURLs/";

            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                }
            };
            return post(data, path);
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public JSONObject finish(JSONObject jsonObject) {
        String path = "/contract/finish/";
        try {

            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("contractId", jsonObject.getString("contractId"));
                }
            };
            return post(data, path);
        } catch (Exception ex) {
            logger.error("sign cert", ex);
        }
        return null;
    }

 

    @Override
    public JSONObject downUserImage(JSONObject jsonObject) {
        String path = "/signatureImage/user/download";
        //final String path = "/signatureImage/user/download";
        try {
            String dataMd5 = "";
            byte[] data = downloadSignatureImage(jsonObject.getString("userId"), jsonObject.getString("imageName"));
            String fileName = "d:/" + jsonObject.getString("userId") + "_" + FsUtils.randomNumeric(3) + ".png";
            createImageFromBytes(fileName, data);
            jsonObject.put("fileName", fileName);
            return jsonObject;
        } catch (Exception ex) {
            logger.error("下载image error", ex);
        }
        return jsonObject;

    }


    @Override
    public byte[] downloadSignatureImage(final String account, final String imageName) throws Exception {
        final String path = "/signatureImage/user/download";

        String url = host + getUrlByRsa(account, imageName, null, path); // rsa的话 pem为私钥
        url = url + "&account=" + account + "&imageName=" + URLEncoder.encode(imageName, "UTF-8");
        System.out.println("url:" + url);

        Map<String, String> headers = new HashMap<>();
        Map<String, Object> res = HttpSender.getResponseBytes("GET", url, "", headers);
        byte[] data = (byte[]) res.get("responseData");
        return data;
    }
    @Override
    /**
     * 仅限下载用户签名图片，其他接口请自行实现
     *
     * @param account
     * @param imageName
     * @param data
     * @param path
     * @return
     * @throws Exception
     */
    public String getUrlByRsa(String account, String imageName, Map<String, Object> data, String path) throws Exception {

        String randomStr = FsUtils.randomNumeric(4);
        String unix = Long.toString(System.currentTimeMillis());
        String rtick = unix + randomStr;

        String dataMd5 = "";
        if (data != null) {
            String jsonData = JSON.toJSONString(data);
            dataMd5 = EncodeUtils.md5(jsonData.getBytes("UTF-8"));
        }
        String sign = String.format("account=%sdeveloperId=%simageName=%srtick=%ssignType=rsa/openapi/v3%s/%s", account, developerId, imageName, rtick, path, dataMd5); // 生成签名字符串
        logger.info(sign);

        String signDataString = this.getSignData(sign);
        String signData = org.apache.commons.codec.binary.Base64.encodeBase64String(EncodeUtils.rsaSign(signDataString.getBytes("UTF-8"), pem));

        signData = URLEncoder.encode(signData, "UTF-8");
        path = path + "/?developerId=" + developerId + "&rtick=" + rtick + "&sign=" + signData + "&signType=rsa";
        logger.info(path);
        return path;
    }
//    private JSONObject get(String path, String account, String imagename, Map<String, Object> data) throws Exception {
//        String url = host + BestSignUtils.getUrlByRsa(developerId, pem, account, imagename, data, path);
//        Map<String, String> headers = new HashMap<>();
//
//        // String dataString = JSONObject.toJSONString(data);
//
//        url = url + "&account=" + account + "&imageName=" + URLEncoder.encode(imagename, "UTF-8");
//        System.out.println("url:" + url);
//
//        Map<String, Object> res = HttpSender.getResponseBytes("GET", url, "", headers);
//        byte[] data1 = (byte[]) res.get("responseData");
//        FileOutputStream fo = new FileOutputStream(new File("D:/image.png"));
//        fo.write(data1);
//        fo.flush();
//        fo.close();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("file", "d:\\xx.png");
//        return jsonObject;
//
//    }

    /// <summary>
    /// Convert Byte[] to a picture and Store it in file
    /// </summary>
    /// <param name="fileName"></param>
    /// <param name="buffer"></param>
    /// <returns></returns>
    @Override
    public String createImageFromBytes(String fileName, byte[] buffer) {
        try {
            // OSSClient ossClient=new OSSClient();

            FileImageOutputStream output = new FileImageOutputStream(new File(fileName));

            //将字节写入文件
            output.write(buffer);
            output.flush();
            output.close();
        } catch (Exception ex) {
            logger.error("createImageFromBytes", ex);
        }
        return fileName;

    }

    private JSONObject post(Map<String, Object> data, String path) throws Exception {
        String url = host + BestSignUtils.getPostUrlByRsa(developerId, pem, data, path);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String dataString = JSONObject.toJSONString(data);
        logger.info("请求ssq,request data:{},path:{}",dataString,path);
        Map<String, Object> res = HttpSender.getResponseString("POST", url, dataString, headers);
        String responeseString = (String) res.get("responseData");
        logger.info("ssq响应,response data:{},path:{}",responeseString,path);

        return parseExecutorResult(responeseString);
    }

    private JSONObject parseExecutorResult(String executorResult) {
        if (StringUtils.isBlank(executorResult)) {
            return null;
        }
        return JSON.parseObject(executorResult);
    }

    private String getSignData(final String... args) {
        StringBuilder builder = new StringBuilder();
        int len = args.length;
        for (int i = 0; i < args.length; i++) {
            builder.append(BestSignUtils.convertToUtf8(args[i]));
            if (i < len - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
