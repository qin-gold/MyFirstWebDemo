package com.wlf.utlis;

import cn.hutool.core.util.StrUtil;

/**
 * 一个对字符操作的数据类
 *
 * @author Qin
 * @createTime 2021/4/29 0:42
 */
public class DataKitUtils extends StrUtil {
    /**
     * 将字符串首字符大写
     *
     * @param str
     * @return
     */
    public static String firstCharToLowerCase(String str) {
        char[] chars = str.toCharArray();
        char first = str.toCharArray()[0];
        //ASCII中65是大写A,90是大写Z
        if (first >= 97 && first <= 122) {
            first -= 32;
            chars[0] = first;
            return String.valueOf(chars);
        }
        return null;
    }

    /**
     * 判断字符是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return "".equals(str) | str == null;
    }
}
