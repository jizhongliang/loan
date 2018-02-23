package com.hwc.framework.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by   on 2017/11/1.
 */
public class PayEncrypt {

    public static final Logger logger = LoggerFactory.getLogger(PayEncrypt.class);

    public static String rsaEncrypt(String source, String public_key)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(public_key);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(1, publicKey);
        byte[] sbt = source.getBytes("UTF-8");
        byte[] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }


    public static byte[] rsaDecrypt(String cryptograph, String private_key)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(private_key);
        PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(s8ek);

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(2, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);

        byte[] b = cipher.doFinal(b1);
        return b;
    }

    public static String aesEncrypt(byte[] msgbt, byte[] aesKey, byte[] nonce)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");

        IvParameterSpec ips = createCtrIv(nonce);

        cipher.init(1, secretKeySpec, ips);
        byte[] epByte = cipher.doFinal(msgbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    public static String aesDecrypt(String msgbt, byte[] aesKey, byte[] iv)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException,
            IOException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        IvParameterSpec ips = createCtrIv(iv);

        cipher.init(2, secretKeySpec, ips);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(msgbt);

        byte[] b = cipher.doFinal(b1);
        return new String(b, "UTF-8");
    }

    private static IvParameterSpec createCtrIv(byte[] nonce) {
        byte[] counter = { 0, 0, 0, 0, 0, 0, 0, 1 };
        byte[] output = new byte[nonce.length + counter.length];
        for (int i = 0; i < nonce.length; i++) {
            output[i] = nonce[i];
        }
        for (int i = 0; i < counter.length; i++) {
            output[(i + nonce.length)] = counter[i];
        }
        return new IvParameterSpec(output);
    }

    public static byte[] encodeHmacSHA256(byte[] data, byte[] key)
            throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data);
        return digest;
    }

    public static String lianlianpayDecrypt(String base64_ciphertext,
                                            String base64_encrypted_aes_key, String base64_nonce,
                                            String trader_pri_key) {
        try {
            byte[] aes_key = rsaDecrypt(base64_encrypted_aes_key,trader_pri_key);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] nonce = decoder.decodeBuffer(base64_nonce);
            return aesDecrypt(base64_ciphertext, aes_key, nonce);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String lianlianpayEncrypt(String req, String public_key,
                                            String hmack_key, String version, String aes_key, String nonce) {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String B64hmack_key = rsaEncrypt(hmack_key, public_key);
            String B64aes_key = rsaEncrypt(aes_key, public_key);
            String B64nonce = encoder.encode(nonce.getBytes());
            String encry = aesEncrypt(req.getBytes("UTF-8"),aes_key.getBytes(), nonce.getBytes());
            String message = B64nonce + "$" + encry;
            byte[] sign = encodeHmacSHA256(message.getBytes(),hmack_key.getBytes());
            String B64sign = encoder.encode(sign);

            return version + "$" + B64hmack_key + "$" + B64aes_key + "$"
                    + B64nonce + "$" + encry + "$" + B64sign;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
