package com.wlf.utlis;

import com.wlf.web.base.annotation.Controller;
import com.wlf.annotation.Log;
import com.wlf.annotation.Table;
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
    private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();

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
        if (ano.equals(Controller.class)) initController(reflection);
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

    private static void initController(Reflections reflections) {
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        classes.forEach(item->{
            map.put(item.getAnnotation(Controller.class).value(),item);
        });
    }

    private static void scannerLog(Reflections reflections) {
    }

    public static Map<String,Class<?>> getClasses() {
        return map;
    }
}
