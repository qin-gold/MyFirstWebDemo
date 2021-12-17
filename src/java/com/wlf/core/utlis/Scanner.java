package com.wlf.core.utlis;

import com.wlf.core.web.base.annotation.Controller;
import com.wlf.core.annotation.Log;
import com.wlf.core.annotation.Table;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注解扫描器
 * 通过init方法传入包名和注解名添加扫描
 * Reflections reflection = new Reflections(packageName) 传入一个包位置扫描包中的class
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 9:26
 */
public class Scanner {

    /**
     * 初始化使用的Map集合
     */
    private static final Properties load;

    static {
        load = PropertiesLoadUtils.load("config.properties");
    }

    /**
     * 初始化
     *
     * @param packageName 传入的包名
     * @param ano         传入的注解
     * @return
     */
    public static void init(String packageName, Class<? extends Annotation> ano) {
        Reflections reflection = new Reflections(packageName);
        if (ano.equals(Table.class)) initDb(reflection);
        if (ano.equals(Log.class)) scannerLog(reflection);
//        throw new RuntimeException("未找到对应的注解");
    }

    /**
     * 初始化数据库表
     *
     * @param reflections 获取的反射对象
     */
    private static void initDb(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        if (!Boolean.parseBoolean(load.getProperty("dev"))){
            return;
        }
        String generaPackage = (String) load.get("generaPackage");
        String[] split = generaPackage.split(",");
        if (!split[0].equals("")) {
            annotated.forEach(item -> DbGenerator.initDb(item, split));
        } else {
            annotated.forEach(DbGenerator::initDb);
        }
    }

    private static void scannerLog(Reflections reflections) {
    }

}
