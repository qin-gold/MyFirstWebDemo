package com.wlf.utlis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * 配置文件读取工具(只允许读取resources文件夹下的properties文件)
 *
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/27 0:19
 */
public class PropertiesLoadUtils {

    /**
     * 传入文件名,和需要读取的Key
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/27 0:26
     */
    public static String load(String fileName, String key) {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(PropertiesLoadUtils.class.getClassLoader().getResourceAsStream(fileName))));
            property = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    /**
     * 传入文件名,和需要读取的Key
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/27 0:26
     */
    public static Properties load(String fileName) {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(PropertiesLoadUtils.class.getClassLoader().getResourceAsStream(fileName))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 传入Key读取消息文件
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/27 0:27
     */
    public static String loadMsg(String key) {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(PropertiesLoadUtils.class.getClassLoader().getResourceAsStream("msgConfig.properties"))));
            property = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
