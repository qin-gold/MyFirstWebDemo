package com.wlf.core.utlis;

import java.util.Properties;

/**
 * 读取配置文件循环判断
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 14:32
 */
public class EqualsUtils {
    private static final  Properties load;
    static {
        load = PropertiesLoadUtils.load("config.properties");
    }
    public static boolean equalsAll(String str){
        boolean equals = false;
        String substring = str.substring(str.lastIndexOf(".") + 1);
        String property = load.getProperty("fileType");
        String[] split = property.split("\\|");
        for (String st : split) {
            if (substring.equals(st)) {
                equals = true;
                break;
            }
        }
        return equals;
    }

}
