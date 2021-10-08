package com.wlf.utlis;

import com.wlf.annotation.Filter;
import com.wlf.annotation.Listener;
import com.wlf.annotation.Servlet;
import com.wlf.web.filter.BaseFilter;
import com.wlf.web.listener.BaseListener;
import com.wlf.web.servlet.BaseServlet;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Http注解扫描器
 * 通过init方法传入包名和注解名添加扫描
 * Reflections reflection = new Reflections(packageName) 传入一个包位置扫描包中的class
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 9:26
 */
public class Scanner {

    /**
     * 存储数据的map集合
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
        throw new RuntimeException("注解有误");
    }

    /** 初始化Servlet
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initServlet(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Servlet.class);
        annotated.forEach(math -> {
            try {
                Class<?> superclass = math.getSuperclass();
                if (superclass.equals(BaseServlet.class)) {
                    Servlet annotations = math.getAnnotation(Servlet.class);
                    map.put(math, annotations.mapping());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /** 初始化Filter
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initFilter(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Filter.class);
        annotated.forEach(math ->
        {
            try {
                Class<?> superclass = math.getSuperclass();
                if (superclass.equals(BaseFilter.class)) {
                    Filter annotations = math.getAnnotation(Filter.class);
                    map.put(math, annotations.urlPatton());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /** 初始化Listener
     * @param reflections 获取的反射对象
     * @return
     */
    private static Map<Class<?>, String> initListener(Reflections reflections) {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Listener.class);
        annotated.forEach(math ->
        {
            try {
                Class<?> superclass = math.getSuperclass();
                if (superclass.equals(BaseListener.class)) map.put(math, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }
}
