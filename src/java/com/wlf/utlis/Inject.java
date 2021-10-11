package com.wlf.utlis;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 通过传入request对象和类对象将根据name将数据封装到类中
 * 通过暴力注入getBeanForce(慎用）推荐使用第二个getBean方法
 * 当前方法同一个name存在多个值存在只会读取第一个值
 * 功能类似于SpringMvc的自动装配
 * 类似于JFinal的getBean方法
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-28 15:57
 * @updateDate 2021-10-07
 * 抽出公共代码 优化JDK9以后实例化过时问题
 */
public class Inject<T> {
    private static Map<String, String[]> parameterMap;
    private static String simpleName;
    private static Set<String> set;
    private static Object ts;

    /**
     * 暴力注入
     *
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBeanForce(HttpServletRequest request, Class<T> t) {
        try {
            forBean(request, (Class<Object>) t);
            set.stream().filter(item -> parameterMap.containsKey(simpleName + '.' + item)).forEach(item -> {
                try {
                    Field field = t.getDeclaredField(item);
                    //暴力注入
                    field.setAccessible(true);
                    field.set(ts, parameterMap.get(simpleName + '.' + item)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return (T) ts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 非暴力注入
     *
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(HttpServletRequest request, Class<T> t) {
        try {
            forBean(request, (Class<Object>) t);
            set.stream().filter(item -> parameterMap.containsKey(simpleName + '.' + item)).forEach(item -> {
                try {
                    Method method = t.getMethod("set" + DataKitUtils.firstCharToLowerCase(item), String.class);
                    method.invoke(ts, parameterMap.get(simpleName + '.' + item)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return (T) ts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void forBean(HttpServletRequest request, Class<Object> t) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        set = new HashSet<>();
        Field[] fields = t.getDeclaredFields();
        simpleName = t.getSimpleName();
        ts = t.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            set.add(field.getName());
        }
        setParameterMap(request.getParameterMap());
    }

    private static void setParameterMap(Map<String, String[]> parameterMap) {
        Inject.parameterMap = parameterMap;
    }
}
