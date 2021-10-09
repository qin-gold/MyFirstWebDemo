package com.wlf.utlis;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/26 23:13
 */
public class MD5Utils {
    private static final char[] hexDigest = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(String str) {
        String clone = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes();
            md.update(bytes);
            byte[] newBytes = md.digest();
            char[] result = new char[newBytes.length * 2];
            int k = 0;
            for (int i = 0; i < newBytes.length; i++) {
                byte c = newBytes[i];
                result[k++] = hexDigest[c >>> 4 & 0xf];
                result[k++] = hexDigest[c & 0xf];
            }
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
