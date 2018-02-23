package com.hwc.framework.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Des {

    private final static String iv        = "85031429";

    private final static String encoding  = "utf-8";

    public final static String  secretKey = "fjgI0uoF5nUPbesKwNYRrWsD";

    /**
     * DES加密
     * 
     * @param secretKey 密匙
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String secretKey, String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64s.encode(encryptData);
    }

    /**
     * DES解密
     * 
     * @param secretKey 密匙
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String secretKey, String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(Base64s.decode(encryptText));
        return new String(decryptData, encoding);
    }

    public static void main(String[] args) {
        //String str = "wRk0YauMI+5k1NNBLy/Znw==";
        String str = "pt1Sl33k7mOxWFO60B5O2/0wmrAb/xD3UGI30yYaZDwZ5HRwYsHE2zBNqBR/45GD2jQXZosvoOXIFMpQKTpbORh/7EIR5eeqE3gVZSxn6rE413xdJNYzL/+ToGVq+BhF2pKP2qdi9aoRpXm53u6qjEKfOoJjhNfZflc4VdFyWWxwZWLOwQ5yyA3cS3zcH9QFmJ28GMdxxpdBkh/VlksY+58aoEs4rw0w4r7MJqpwo9ggnEu+FkPqZmPz+hQmAvPg2Z//APAf7Wy1Bj0MBM4AxQ==";
        try {
            str = decode(secretKey,str);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
