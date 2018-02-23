package com.hwc.framework.utils;

import java.security.*;  
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;   
public class DesUtils {  
    /** 加密、解密key. */  
    private static final String PASSWORD_CRYPT_KEY = "fjgI0uoF5nUPbesKwNYRrWsD";  
    /** 加密算法,可用 DES,DESede,Blowfish. */  
    private final static String ALGORITHM = "DES";  
    public static void main(String[] args) throws Exception {  
        String str = "ShfH3QbSviNuLUGMDNEkbJCVOLUVxbkct7xeKtpp5GLcDCetxN0i4OshlTaCnINwQf6l49wkqJEa92IChb8c14avVBbXxKw5ZWpxC9uYDDzkhvm4sTW8cwGGfsV3wyWshpDdfsyJ5Efj1AYummfmK4n+Zs5t/aB0LTlaiXkM63O5ZhwLToNNsOh/Ds5kXSbYzTDcUTpdaa21Nnah0waEd464KfZXqunUS2TMtSr0k2TSLwa5So2OVj3pxEc+gwZC/28sEPNM1XjVBz79jwlHoWIwgjeE3HPB6HeRsDV7UJ2zrbFwnyrEtAorWH60y3GtODMdRlVU5vIkLIBiELt8cZpUAfNyYGfLHMpKKFCs1VmxqCvOHe7lDxdgs4UcQnuLkNAzVNGiCKNr0VOODiMTj12Gvs0Huowj1x0qfwbTlIZK6s1OMgX84m+rRcguzoBB4J4U3BzvM5mf57ZraDCwr1OGKkdU7jor/TTXHPwlqKtia05fE2WmVwj0ykLFJOMHYR6pTW2ZayA6p0rCTr/LMX3VOtN5fLAEW7+0Y/H5Z7HsIRVyOiNIody4NTd0Go5goxDJ/W1QS/s+vHVI81rJ+W21I3lanUGfkGsGXco/SyPhbBXqXikkZdMF7hNmrGwbccjTqBAfCyCUEYxulZpmBOuRiy4zd0o//ZonNWoPb8DbegfvC59TmwXpvOnRoRQVtM8ooiJWmpfVyBKgLT9y6tbwV4kwh/SrZzKD5mTSzFzTmMT3KyAoTODSSrPEjsYPuPZDf7yll4V/C35S/poOC2PH3MwFvytg9ahN02CpaGKefwpsVXzh7FtlfDuCXNblkE4FNA69pnY/JTGVTf8rtbQeBUCXikNAt+FkP/xJfGQus+3pRM1DSNK6pnqy3CuGPFD9k6y7tFnH6ilf9AaJm0Qj+/Ei8LPYR9o6wpuGIcAevV78ctcnzOzMxFtkhmu3zm1Kg0o92H6ce2lqajjInnUBGuv1DGL+CQnR01Vov0L7pzVN1mrmuN+FJIWWeCzg6uttWGNPrD20V7LwnXvxRWGK7FoetGMluraZfy67R995EGWTaq6IhABDX83FtXKR4spegrIcpaV7sVqh8JJZGthVHN9dfLVFUs2Ug51b8BNdjLbfQP9PHP2mt++tiPGJ1HqcZiwQxfpu8sFLGqFYO8zD+iYEWvkOMsN5cg6P1+ufJDYdPvI5IWnHWVtkV85e4FqnLZRJFoK8lQYKDdqc/nC1+n8Eq3RqGVIgGLEXM2k7O0AzAYjXJZf0UDHo09AjIPf3YzismX3HJCQ8SnXyjd66RMA5yyaO+PRn1uhvNk0lI9cjmMBtnodGOVxCPHzlme0ms1iopufe9fn8YD7jsNHr38Kg6oMa28dWpc5tKvzABGlWniooi/8x1/expGEVDLtBXck0zX1q/48m51VqqZDIVX7KYzy5/KHERqfKFrwQ5t83INwRy6eLEgVFq/bUgmsqtKteC1yEKewxewownfK/BE20cNgO1Nz3rnTY2GQyhppGcAIi/Rs03Cddov/R6pOKK7KLeq4aBaBy4ap5GAE83y2+iHFvcYPgLwVbO4mUq2P3LtJ7l1XhLJMabdiCzsg7ccEiCQFxEFZfubfzPLsOOhgpZTsQH16yRfBZAExo0VPVIw4agDtJ5axNZ5rFOZly9uvJ7fZV8CE3rTLxwds+X2886yPG0co+WxeZaxxFe8BX++dAOkrRu77TbphP9IvraU+pgWMuhoMucWpZsv+LaE5XglgexS4iubszjBg/ETM+fkxe2JZ3/KmEYHYlgmCriaR7X7eg3kaZ312Yi6t7m0K7veAdX02qWWrf198F+GOgCRk91+NwJMBmZ8Ra";
        str = DesUtils.decrypt(str);  
        System.out.println("str: " + str);  
    }  
      
    /** 
     * 对数据进行DES加密. 
     * @param data 待进行DES加密的数据 
     * @return 返回经过DES加密后的数据 
     * @throws Exception   
     */  
    public final static String decrypt(String data) throws Exception {  
        return new String(decrypt(hex2byte(data.getBytes()),  
                PASSWORD_CRYPT_KEY.getBytes()));  
    }  
    /** 
     * 对用DES加密过的数据进行解密. 
     * @param data DES加密数据 
     * @return 返回解密后的数据 
     * @throws Exception  
     */  
    public final static String encrypt(String data) throws Exception  {  
        return byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY  
                .getBytes()));  
    }  
      
    /** 
     * 用指定的key对数据进行DES加密. 
     * @param data 待加密的数据 
     * @param key DES加密的key 
     * @return 返回DES加密后的数据 
     * @throws Exception  
     */  
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {  
        // DES算法要求有一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
        // 从原始密匙数据创建DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成  
        // 一个SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey securekey = keyFactory.generateSecret(dks);  
        // Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        // 用密匙初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        // 现在，获取数据并加密  
        // 正式执行加密操作  
        return cipher.doFinal(data);  
    }  
    /** 
     * 用指定的key对数据进行DES解密. 
     * @param data 待解密的数据 
     * @param key DES解密的key 
     * @return 返回DES解密后的数据 
     * @throws Exception  
     */  
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {  
        // DES算法要求有一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
        // 从原始密匙数据创建一个DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成  
        // 一个SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey securekey = keyFactory.generateSecret(dks);  
        // Cipher对象实际完成解密操作  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        // 用密匙初始化Cipher对象  
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
        // 现在，获取数据并解密  
        // 正式执行解密操作  
        return cipher.doFinal(data);  
    }  
    public static byte[] hex2byte(byte[] b) {  
        if ((b.length % 2) != 0)  
            throw new IllegalArgumentException("长度不是偶数");  
        byte[] b2 = new byte[b.length / 2];  
        for (int n = 0; n < b.length; n += 2) {  
            String item = new String(b, n, 2);  
            b2[n / 2] = (byte) Integer.parseInt(item, 16);  
        }  
        return b2;  
    }  
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else  
                hs = hs + stmp;  
        }  
        return hs.toUpperCase();  
    }  
}  