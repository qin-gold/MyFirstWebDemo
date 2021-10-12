package com.wlf.utlis;

import com.wlf.annotation.*;
import com.wlf.web.base.filter.BaseFilter;
import com.wlf.web.base.listener.BaseListener;
import com.wlf.web.base.servlet.BaseServlet;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
    private static final Map<Class<?>, String> map = new HashMap<>();

    /**
     * 初始化
     *
     * @param packageName 传入的包名
     * @param ano         传入的注解
     * @return
     */
    public static Map<Class<?>, String> init(String packageName, Class<? extends Annotation> ano) {
        Reflections reflection = new Reflections(packageName);
        if (ano.equals(Servlet.class)) return initServlet(reflection);
        if (ano.equals(Filter.class)) return initFilter(reflection);
        if (ano.equals(Listener.class)) return initListener(reflection);
        if (ano.equals(Table.class)) {
            initDb(reflection);
            return null;
        }
        if (ano.equals(Log.class)) scannerLog(reflection);
        throw new RuntimeException("未找到对应的注解");
    }

    /**
     * 初始化Servlet
     *
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initServlet(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Servlet.class);
        annotated.forEach(data -> {
            try {
                Class<?> superclass = data.getSuperclass();
                if (superclass.equals(BaseServlet.class)) {
                    Servlet annotations = data.getAnnotation(Servlet.class);
                    map.put(data, annotations.mapping());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * 初始化Filter
     *
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initFilter(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Filter.class);
        annotated.forEach(data -> {
            try {
                Class<?> superclass = data.getSuperclass();
                if (superclass.equals(BaseFilter.class)) {
                    Filter annotations = data.getAnnotation(Filter.class);
                    map.put(data, annotations.urlPatton());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * 初始化Listener
     *
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initListener(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Listener.class);
        annotated.forEach(data -> {
            try {
                Class<?> superclass = data.getSuperclass();
                if (superclass.equals(BaseListener.class)) map.put(data, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /** 初始化数据库表
     * @param reflections 获取的反射对象
     */
    private static void initDb(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        annotated.forEach(DbGenerator::createTable);
    }

    private static void scannerLog(Reflections reflections){
//        reflections.
    }
}
