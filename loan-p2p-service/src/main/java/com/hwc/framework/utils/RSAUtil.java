package com.hwc.framework.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author jzl
 */
public class RSAUtil {

    public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param content 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String content, String publicKey)
            throws Exception {
        byte[] data = content.getBytes("UTF-8");
        byte[] keyBytes = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return URLEncoder.encode(Base64.encodeBase64String(encryptedData), "UTF-8");
    }
    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decodeBase64(privateKey) );
            KeyFactory keyf 				= KeyFactory.getInstance("RSA");
            PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();

            return Base64.encodeBase64String(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     * @param content 待签名数据
     * @param sign 签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update( content.getBytes(input_charset) );

            boolean bverify = signature.verify( Base64.decodeBase64(sign) );
            return bverify;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 解密
     * @param content 密文
     * @param private_key 商户私钥
     * @param input_charset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }


    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.decodeBase64(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    public static String encoderByMd5(String str){
        str = "UQ2CcQ7Ncs1yZBGoq4rjeAz2"+str;
        //确定计算方法
        MessageDigest md5= null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    public static void main(String[] args) throws Exception{
        /*String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPSb5vXyI/WhSV53DCC8MT+lIehKTxN1tD+7mPqlQBrgTgbHBHHQZ9q62A2ZUTn/rgNxYM6vWs6by7kyFo+RtHbEcTdbGy1XNtJpSTY/jWhREgPUwhTaiWvMu+AvGWN5BOcYOcDRgAPfDltp5utRlolbrO/kwuOvAawWH2kbNrJBAgMBAAECgYEArW3ECWkO+d7K6EE7xhHoURnBurLE8iUoEW/u/PchRmz2pXOBwThkCf8jpDCqGJchR6uhWamWlpp7jOjoeg5yhcfPvgL2DTi52MHMlvHhcj9P4ES/I7vJW30rytSWQvhweu7rX2cWgV05u8JgDJZL/0IwySGlDUQOqhYiz6tqoGECQQD8+UWQnTaWQGLR8VJBIM4Pum0r7k+92fKu73H6PlWSADR6p7QjHsz9O8WKsKFusL5D5UEycuiNyLg8usW11neNAkEA94kDe7qwAjtwQ+mZZ6moNj7ahVmUajJemiv8UF/pGIHA7qf/SnCM0b13zQ3a9UNEsgg2WML1DWOVYWpWCBxuhQJAd6MjxRq0wCY/Fe686dQr38BY9RfFWpICdb0D+EcWjO/P4doCFUHxzClCaS7TidfIg6+eP8+cL8GncAEck7yT2QJACtcPNf3o81ATDIZQV3/Qg/+gpjJUE4p9JhdDB2oL1SzpfAoTXfkr0YuQkYRH0HHPslQ1vRFcleeEhS2Y8uX72QJAWOKEpfmkc0IqXIZSEIr1kdvqYKDoZhqlQh/8yWS+UBrT961RXt5UOhzRkDDGXEham6prQ8kK9JwlmzbqeLw7/g==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD0m+b18iP1oUledwwgvDE/pSHoSk8TdbQ/u5j6pUAa4E4GxwRx0GfautgNmVE5/64DcWDOr1rOm8u5MhaPkbR2xHE3WxstVzbSaUk2P41oURID1MIU2olrzLvgLxljeQTnGDnA0YAD3w5baebrUZaJW6zv5MLjrwGsFh9pGzayQQIDAQAB";
        //String enStr = encryptByPublicKey("i am jzl", publicKey);
        //String deStr = decrypt(enStr, privateKey, "UTF-8");
        String str = "joZtoWzAxgCfv32kdwsb1TBGLBzISkuCJK5+SMu32Taql5+A3IGAiaUY9KRiyHjNM2jyzWYoYzzp823NZHsIg90EHaolv2oz3RK9t/t3MLVVEcMgflQF4ZMWp2qwWrMJfFmMCZOHLJMw8yUKEslVPXofkAviw2+ZTumuRN8TfZ4=";
        //System.out.println("加密：" + enStr);
        //System.out.println("解密：" + deStr);
        String deyStr2 = decrypt(str, privateKey, "utf-8");
        System.out.println("解密12：" + deyStr2);*/
        String str = "[{\"payMentTransaction\":\"DK01201712299090303523500001\",\"totalAmount\":\"1139.50\",\"overdueFine\":\"0.00\",\"repayAmount\":\"1000.00\",\"stages\":\"137\",\"isLastStages\":0,\"interest\":\"139.50\"}]";
        System.out.println("md5加密后:"+ encoderByMd5(str));
    }
}

