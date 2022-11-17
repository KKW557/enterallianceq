package icu.kevin557.eq.utils;

import java.security.MessageDigest;

/**
 * @author 557
 */
public class CryptUtils {

    /**
     * 将String转换为字节MD5
     * @param string 字符串
     * @return MD5
     * @author 557
     */
    public static byte[] string2Md5(String string) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }

        char[] chars = string.toCharArray();
        byte[] bytes = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }

        return messageDigest.digest(bytes);
    }
}
