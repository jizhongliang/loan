package com.hwc.framework.common;

import cn.freesoft.utils.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by   on 2017/11/1.
 */
public class SignUtil {

    private static Logger logger = Logger.getLogger(SignUtil.class);

    private static SignUtil instance;

    public static SignUtil getInstance() {
        if (instance == null)
            return new SignUtil();
        return instance;
    }

    public static String sign(String priKey, String signStr) {
        byte[] signed = null;

        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(priKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(signStr.getBytes("UTF-8"));
            signed = signet.sign();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException
                | UnsupportedEncodingException e) {
            logger.error("签名失败," + e.getMessage(), e);
        }

        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节数组为高位字符串
     *
     * @param b
     *            字节数组
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    public static String md5Digest(byte[] src) {
        MessageDigest alg;
        try {
            // MD5 is 32 bit message digest
            alg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return byteArrayToHexString(alg.digest(src));
    }

    public static boolean checkMD5sign(String oidStr, String signedStr) {

        try {
            oidStr = oidStr + "&key=201608101001022519_test_20160810";
            return md5Digest(oidStr.getBytes("UTF-8")).equals(signedStr);

        } catch (Exception e) {
            logger.error("签名失败," + e.getMessage(), e);
        }

        return false;
    }

    public static boolean checksign(String pubKeyStr, String oidStr, String signedStr) {

        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decode(pubKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.decode(signedStr);
            Signature signetcheck = Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oidStr.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException
                | UnsupportedEncodingException e) {
            logger.error("签名失败," + e.getMessage(), e);
        }

        return false;
    }

    public static String genRSASign(String jsonStr, String privateKey) {
        // 生成待签名串
        String sign_src = genSignData(jsonStr);

        JSONObject reqObj = JSON.parseObject(jsonStr);
        logger.info("商户[" + reqObj.getString("oid_partner") + "]待签名原串：" + sign_src);

        return SignUtil.sign(privateKey, sign_src);
    }

    /**
     * 生成待签名串
     *
     * @param jsonStr
     * @return
     */
    public static String genSignData(String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        StringBuilder content = new StringBuilder();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            // sign 和ip_client 不参与签名
            if ("sign".equals(key)) {
                continue;
            }
            String value = (String) jsonObject.getString(key);
            // 空串不参与签名
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

}