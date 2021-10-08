package com.wlf.utlis;


import com.wlf.annotation.Filter;
import com.wlf.annotation.Servlet;
import com.wlf.web.filter.BaseFilter;
import com.wlf.web.servlet.BaseServlet;
import org.reflections.Reflections;

import javax.servlet.http.HttpServlet;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/8 9:26
 */
public class Scanner {
    private static final Map<Class<?>,String> map = new HashMap<>();
    private static final Set<Class<? extends Annotation>> set = new HashSet<>();

    public static Map<Class<?>,String> init(String packageName,Class<? extends Annotation> ano){
      set.add(ano);
      return init(packageName,set);
    }

    private static Map<Class<?>,String> init(String packageName,Set<Class<? extends Annotation>> set){
        Reflections reflection = new Reflections(packageName);
        if (set.contains(Servlet.class)) return initServlet(reflection);
        if (set.contains(Filter.class)) return initFilter(reflection);
        return null;
    }

    private static Map<Class<?>,String> initServlet(Reflections reflections){
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Servlet.class);
        annotated.forEach(math-> {
            try {
                Class<?> superclass = math.getSuperclass();
                if (superclass.equals(BaseServlet.class)){
                    Servlet annotations = math.getAnnotation(Servlet.class);
                    map.put(math, annotations.mapping());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    private static Map<Class<?>,String> initFilter(Reflections reflections){

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Filter.class);
        annotated.forEach(math->
        {
            try {
                Class<?> superclass = math.getSuperclass();
                if (superclass.equals(BaseFilter.class)){
                    Filter annotations = math.getAnnotation(Filter.class);
                    map.put(math, annotations.urlPatton());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return map;
    }

//    private static Map<Class<?>,String> initListener(Reflections reflections){
//        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Servlet.class);
//        annotated.forEach(math->
//        {
//            try {
//                Class<?> superclass = math.getSuperclass();
//                if (superclass.equals(HttpServlet.class)){
//                    Servlet annotations = math.getAnnotation(Servlet.class);
//                    map.put(math, annotations.mapping());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//}



}
