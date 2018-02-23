package com.hwc.framework.common;

import java.util.Random;

/**
 * Created by   on 2017/11/1.
 */
public class PaySecurity {

    public static String encrypt(String plaintext, String public_key){
        String hmack_key = genLetterDigitRandom(32);
        String version = "lianpay1_0_1";
        String aes_key = genLetterDigitRandom(32);
        String nonce = genLetterDigitRandom(8);
        return PayEncrypt.lianlianpayEncrypt(plaintext, public_key,
                hmack_key, version, aes_key, nonce);
    }

    public static String decrypt(String ciphertext, String private_key)
    {
        if (isNull(ciphertext)) {
            return "";
        }
        String[] ciphertextArry = ciphertext.split("\\$");
        String base64_encrypted_aes_key = ciphertextArry.length > 2 ? ciphertextArry[2]
                : "";
        String base64_nonce = ciphertextArry.length > 3 ? ciphertextArry[3]: "";
        String base64_ciphertext = ciphertextArry.length > 4 ? ciphertextArry[4]: "";
        return PayEncrypt.lianlianpayDecrypt(base64_ciphertext,base64_encrypted_aes_key, base64_nonce, private_key);
    }

    public static String genLetterDigitRandom(int size) {
        StringBuilder allLetterDigit = new StringBuilder(
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Random random = new Random();

        StringBuilder randomSb = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            randomSb.append(allLetterDigit.charAt(random.nextInt(allLetterDigit.length())));
        }
        return randomSb.toString();
    }

    public static boolean isNull(String str) {
        if ((str == null) || (str.equalsIgnoreCase("NULL")) || (str.equals(""))) {
            return true;
        }
        return false;
    }
}