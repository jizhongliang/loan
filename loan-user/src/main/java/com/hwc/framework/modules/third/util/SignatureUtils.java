package com.hwc.framework.modules.third.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureUtils {

    public static String base64Hmac256(String secret, String message) {
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);
            return Base64.encodeBase64String(sha256Hmac.doFinal(message.getBytes()));
        } catch (Exception ignored) {
            return "";
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(base64Hmac256("27c7e4bc518c48d095d9caf544771876","{\"mobile\":\"18011805040\",\"timestamp\":1487664699606,\"name\":\"梁丹黎\",\"idcard\":\"411381198703045917\",\"task_id\":\"5f206280-f80d-11e6-b1f3-00163e0d2629\",\"user_id\":\"1111\"}"));
	}
}
